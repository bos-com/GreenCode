package com.greencode.service;

import com.greencode.controller.ProjectController.ProjectImpactDTO;
import com.greencode.controller.ProjectController.ProjectStatsDTO;
import com.greencode.entity.Project;
import com.greencode.entity.User;
import com.greencode.repository.ProjectRepository;
import com.greencode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findByIsActiveTrue(pageable);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findByIdAndIsActiveTrue(id);
    }

    public List<Project> getProjectsByCategory(Project.ProjectCategory category) {
        return projectRepository.findByCategoryAndIsActiveTrue(category);
    }

    public List<Project> getProjectsByStatus(Project.ProjectStatus status) {
        return projectRepository.findByStatusAndIsActiveTrue(status);
    }

    public List<Project> getProjectsByManager(Long managerId) {
        Optional<User> manager = userRepository.findById(managerId);
        if (manager.isPresent()) {
            return projectRepository.findByManagerAndIsActiveTrue(manager.get());
        }
        throw new RuntimeException("Manager not found");
    }

    public List<Project> getPublicProjects() {
        return projectRepository.findByIsPublicTrueAndIsActiveTrue();
    }

    public List<Project> searchProjects(String keyword) {
        return projectRepository.searchProjects(keyword);
    }

    public Project createProject(Project project) {
        // Validate dates
        if (project.getStartDate() != null && project.getEndDate() != null) {
            if (project.getStartDate().isAfter(project.getEndDate())) {
                throw new RuntimeException("Start date cannot be after end date");
            }
        }

        // Set default values if not provided
        if (project.getStatus() == null) {
            project.setStatus(Project.ProjectStatus.PLANNED);
        }
        if (project.getIsPublic() == null) {
            project.setIsPublic(true);
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findByIdAndIsActiveTrue(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        // Validate dates if provided
        if (projectDetails.getStartDate() != null && projectDetails.getEndDate() != null) {
            if (projectDetails.getStartDate().isAfter(projectDetails.getEndDate())) {
                throw new RuntimeException("Start date cannot be after end date");
            }
        }

        // Update project details
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setCategory(projectDetails.getCategory());
        project.setStatus(projectDetails.getStatus());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setBudget(projectDetails.getBudget());
        project.setActualCost(projectDetails.getActualCost());
        project.setLocation(projectDetails.getLocation());
        project.setCoordinates(projectDetails.getCoordinates());
        project.setImpactScore(projectDetails.getImpactScore());
        project.setSustainabilityRating(projectDetails.getSustainabilityRating());
        project.setManager(projectDetails.getManager());
        project.setTeamSize(projectDetails.getTeamSize());
        project.setIsPublic(projectDetails.getIsPublic());

        return projectRepository.save(project);
    }

    public Project updateProjectStatus(Long id, Project.ProjectStatus status) {
        Project project = projectRepository.findByIdAndIsActiveTrue(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setStatus(status);
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));
        
        // Soft delete
        project.setIsActive(false);
        projectRepository.save(project);
    }

    public ProjectImpactDTO calculateProjectImpact(Project project) {
        // Calculate environmental impact based on project attributes
        double co2Reduction = 0.0;
        double energySavings = 0.0;
        double costSavings = 0.0;
        int treesEquivalent = 0;

        if (project.getImpactScore() != null) {
            // Base calculations on impact score and category
            double multiplier = getImpactMultiplier(project.getCategory());
            co2Reduction = project.getImpactScore() * multiplier * 10; // tons per year
            energySavings = project.getImpactScore() * multiplier * 1000; // kWh per year
            costSavings = project.getImpactScore() * multiplier * 100; // dollars per year
            treesEquivalent = (int) (co2Reduction * 21.77); // 1 tree absorbs ~46 lbs CO2/year
        }

        // Adjust for project size
        if (project.getBudget() != null && project.getBudget().doubleValue() > 0) {
            double budgetFactor = project.getBudget().doubleValue() / 100000; // per $100k
            co2Reduction *= budgetFactor;
            energySavings *= budgetFactor;
            costSavings *= budgetFactor;
            treesEquivalent = (int) (treesEquivalent * budgetFactor);
        }

        return new ProjectImpactDTO(
            Math.round(co2Reduction * 100.0) / 100.0,
            Math.round(energySavings * 100.0) / 100.0,
            Math.round(costSavings * 100.0) / 100.0,
            Math.max(0, treesEquivalent)
        );
    }

    private double getImpactMultiplier(Project.ProjectCategory category) {
        switch (category) {
            case RENEWABLE_ENERGY:
                return 2.0;
            case WASTE_MANAGEMENT:
                return 1.5;
            case WATER_CONSERVATION:
                return 1.8;
            case FORESTRY:
                return 3.0;
            case AGRICULTURE:
                return 1.2;
            case TRANSPORTATION:
                return 1.6;
            case BUILDING_EFFICIENCY:
                return 1.4;
            case EDUCATION:
                return 0.8;
            case RESEARCH:
                return 0.6;
            case OTHER:
            default:
                return 1.0;
        }
    }

    public ProjectStatsDTO getProjectStats() {
        List<Project> allProjects = projectRepository.findByIsActiveTrue();
        
        long totalProjects = allProjects.size();
        long activeProjects = allProjects.stream()
            .filter(p -> p.getStatus() == Project.ProjectStatus.IN_PROGRESS)
            .count();
        long completedProjects = allProjects.stream()
            .filter(p -> p.getStatus() == Project.ProjectStatus.COMPLETED)
            .count();
        
        double totalBudget = allProjects.stream()
            .filter(p -> p.getBudget() != null)
            .mapToDouble(p -> p.getBudget().doubleValue())
            .sum();
        
        double averageImpactScore = allProjects.stream()
            .filter(p -> p.getImpactScore() != null)
            .mapToInt(p -> p.getImpactScore())
            .average()
            .orElse(0.0);

        return new ProjectStatsDTO(
            totalProjects,
            activeProjects,
            completedProjects,
            Math.round(totalBudget * 100.0) / 100.0,
            Math.round(averageImpactScore * 100.0) / 100.0
        );
    }

    public List<Project> getUpcomingProjects(int days) {
        LocalDate cutoffDate = LocalDate.now().plusDays(days);
        return projectRepository.findUpcomingProjects(cutoffDate);
    }

    public List<Project> getOverdueProjects() {
        LocalDate today = LocalDate.now();
        return projectRepository.findOverdueProjects(today);
    }

    public BigDecimal getTotalBudgetByCategory(Project.ProjectCategory category) {
        return projectRepository.getTotalBudgetByCategory(category)
            .orElse(BigDecimal.ZERO);
    }

    public List<Project> getHighImpactProjects(int minImpactScore) {
        return projectRepository.findHighImpactProjects(minImpactScore);
    }
}
