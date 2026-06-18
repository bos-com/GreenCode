package com.greencode.repository;

import com.greencode.entity.Project;
import com.greencode.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByIdAndIsActiveTrue(Long id);

    Page<Project> findByIsActiveTrue(Pageable pageable);

    List<Project> findByCategoryAndIsActiveTrue(Project.ProjectCategory category);

    List<Project> findByStatusAndIsActiveTrue(Project.ProjectStatus status);

    List<Project> findByManagerAndIsActiveTrue(User manager);

    List<Project> findByIsPublicTrueAndIsActiveTrue();

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.location) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Project> searchProjects(@Param("keyword") String keyword);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.startDate BETWEEN :startDate AND :endDate")
    List<Project> findProjectsStartingBetween(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate
    );

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.startDate <= :today AND p.endDate >= :today AND p.status = 'IN_PROGRESS'")
    List<Project> findActiveProjects(@Param("today") LocalDate today);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.startDate > :today AND p.startDate <= :cutoffDate")
    List<Project> findUpcomingProjects(@Param("cutoffDate") LocalDate cutoffDate);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.endDate < :today AND p.status != 'COMPLETED'")
    List<Project> findOverdueProjects(@Param("today") LocalDate today);

    @Query("SELECT SUM(p.budget) FROM Project p WHERE p.isActive = true AND " +
           "p.category = :category AND p.budget IS NOT NULL")
    Optional<BigDecimal> getTotalBudgetByCategory(@Param("category") Project.ProjectCategory category);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.impactScore >= :minImpactScore ORDER BY p.impactScore DESC")
    List<Project> findHighImpactProjects(@Param("minImpactScore") int minImpactScore);

    @Query("SELECT COUNT(p) FROM Project p WHERE p.isActive = true AND p.category = :category")
    long countByCategory(@Param("category") Project.ProjectCategory category);

    @Query("SELECT COUNT(p) FROM Project p WHERE p.isActive = true AND p.status = :status")
    long countByStatus(@Param("status") Project.ProjectStatus status);

    @Query("SELECT AVG(p.impactScore) FROM Project p WHERE p.isActive = true AND p.impactScore IS NOT NULL")
    Double getAverageImpactScore();

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.manager.id = :managerId ORDER BY p.createdAt DESC")
    List<Project> findByManagerIdOrderByCreatedAtDesc(@Param("managerId") Long managerId);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.sustainabilityRating >= :minRating ORDER BY p.sustainabilityRating DESC")
    List<Project> findHighSustainabilityProjects(@Param("minRating") int minRating);

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "(:categoryId IS NULL OR p.category = :categoryId) AND " +
           "(:statusId IS NULL OR p.status = :statusId) AND " +
           "(:managerId IS NULL OR p.manager.id = :managerId)")
    Page<Project> findProjectsWithFilters(
        @Param("categoryId") Project.ProjectCategory categoryId,
        @Param("statusId") Project.ProjectStatus statusId,
        @Param("managerId") Long managerId,
        Pageable pageable
    );

    @Query("SELECT DISTINCT p.category FROM Project p WHERE p.isActive = true")
    List<Project.ProjectCategory> findAllActiveCategories();

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.budget IS NOT NULL AND p.actualCost IS NOT NULL AND " +
           "p.actualCost > p.budget")
    List<Project> findOverBudgetProjects();

    @Query("SELECT p FROM Project p WHERE p.isActive = true AND " +
           "p.teamSize IS NOT NULL ORDER BY p.teamSize DESC")
    List<Project> findLargestTeamProjects(Pageable pageable);
}
