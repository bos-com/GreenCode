package com.greencode.controller;

import com.greencode.entity.Project;
import com.greencode.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<Project> projects = projectService.getAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Project>> getProjectsByCategory(@PathVariable String category) {
        try {
            Project.ProjectCategory projectCategory = Project.ProjectCategory.valueOf(category.toUpperCase());
            List<Project> projects = projectService.getProjectsByCategory(projectCategory);
            return ResponseEntity.ok(projects);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Project>> getProjectsByStatus(@PathVariable String status) {
        try {
            Project.ProjectStatus projectStatus = Project.ProjectStatus.valueOf(status.toUpperCase());
            List<Project> projects = projectService.getProjectsByStatus(projectStatus);
            return ResponseEntity.ok(projects);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/manager/{managerId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MODERATOR')")
    public ResponseEntity<List<Project>> getProjectsByManager(@PathVariable Long managerId) {
        List<Project> projects = projectService.getProjectsByManager(managerId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Project>> getPublicProjects() {
        List<Project> projects = projectService.getPublicProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(@RequestParam String keyword) {
        List<Project> projects = projectService.searchProjects(keyword);
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MODERATOR')")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
        try {
            Project createdProject = projectService.createProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MODERATOR')")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @Valid @RequestBody Project projectDetails) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDetails);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MODERATOR')")
    public ResponseEntity<Project> updateProjectStatus(
            @PathVariable Long id, 
            @RequestParam Project.ProjectStatus status) {
        try {
            Project updatedProject = projectService.updateProjectStatus(id, status);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/impact")
    public ResponseEntity<ProjectImpactDTO> getProjectImpact(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            ProjectImpactDTO impact = projectService.calculateProjectImpact(project.get());
            return ResponseEntity.ok(impact);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stats/summary")
    public ResponseEntity<ProjectStatsDTO> getProjectStats() {
        ProjectStatsDTO stats = projectService.getProjectStats();
        return ResponseEntity.ok(stats);
    }

    // Inner classes for DTOs
    public static class ProjectImpactDTO {
        private double co2Reduction;
        private double energySavings;
        private double costSavings;
        private int treesEquivalent;

        // Constructors, getters, setters
        public ProjectImpactDTO() {}

        public ProjectImpactDTO(double co2Reduction, double energySavings, double costSavings, int treesEquivalent) {
            this.co2Reduction = co2Reduction;
            this.energySavings = energySavings;
            this.costSavings = costSavings;
            this.treesEquivalent = treesEquivalent;
        }

        // Getters and setters
        public double getCo2Reduction() { return co2Reduction; }
        public void setCo2Reduction(double co2Reduction) { this.co2Reduction = co2Reduction; }
        public double getEnergySavings() { return energySavings; }
        public void setEnergySavings(double energySavings) { this.energySavings = energySavings; }
        public double getCostSavings() { return costSavings; }
        public void setCostSavings(double costSavings) { this.costSavings = costSavings; }
        public int getTreesEquivalent() { return treesEquivalent; }
        public void setTreesEquivalent(int treesEquivalent) { this.treesEquivalent = treesEquivalent; }
    }

    public static class ProjectStatsDTO {
        private long totalProjects;
        private long activeProjects;
        private long completedProjects;
        private double totalBudget;
        private double averageImpactScore;

        // Constructors, getters, setters
        public ProjectStatsDTO() {}

        public ProjectStatsDTO(long totalProjects, long activeProjects, long completedProjects, 
                             double totalBudget, double averageImpactScore) {
            this.totalProjects = totalProjects;
            this.activeProjects = activeProjects;
            this.completedProjects = completedProjects;
            this.totalBudget = totalBudget;
            this.averageImpactScore = averageImpactScore;
        }

        // Getters and setters
        public long getTotalProjects() { return totalProjects; }
        public void setTotalProjects(long totalProjects) { this.totalProjects = totalProjects; }
        public long getActiveProjects() { return activeProjects; }
        public void setActiveProjects(long activeProjects) { this.activeProjects = activeProjects; }
        public long getCompletedProjects() { return completedProjects; }
        public void setCompletedProjects(long completedProjects) { this.completedProjects = completedProjects; }
        public double getTotalBudget() { return totalBudget; }
        public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }
        public double getAverageImpactScore() { return averageImpactScore; }
        public void setAverageImpactScore(double averageImpactScore) { this.averageImpactScore = averageImpactScore; }
    }
}
