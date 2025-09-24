package com.greencode.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @NotBlank(message = "Project name is required")
    @Size(max = 200, message = "Project name must be less than 200 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProjectCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProjectStatus status = ProjectStatus.PLANNED;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "actual_cost", precision = 15, scale = 2)
    private BigDecimal actualCost;

    @Column(name = "location", length = 500)
    private String location;

    @Column(name = "coordinates")
    private String coordinates; // Latitude,Longitude format

    @Column(name = "impact_score")
    private Integer impactScore; // 1-10 scale

    @Column(name = "sustainability_rating")
    private Integer sustainabilityRating; // 1-5 scale

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    @Column(name = "team_size")
    private Integer teamSize;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    // Constructors
    public Project() {}

    public Project(String name, ProjectCategory category) {
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getActualCost() {
        return actualCost;
    }

    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getImpactScore() {
        return impactScore;
    }

    public void setImpactScore(Integer impactScore) {
        this.impactScore = impactScore;
    }

    public Integer getSustainabilityRating() {
        return sustainabilityRating;
    }

    public void setSustainabilityRating(Integer sustainabilityRating) {
        this.sustainabilityRating = sustainabilityRating;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    // Enums
    public enum ProjectCategory {
        RENEWABLE_ENERGY,
        WASTE_MANAGEMENT,
        WATER_CONSERVATION,
        FORESTRY,
        AGRICULTURE,
        TRANSPORTATION,
        BUILDING_EFFICIENCY,
        EDUCATION,
        RESEARCH,
        OTHER
    }
    /**
     * represents the current status of the program
     */

    public enum ProjectStatus {
        /** 
         * project is planned but the work has not yet started
         */
        PLANNED,
        /**
         * project is currently in progress
         */
        IN_PROGRESS,
        /**
         * project is temporarily on hold
         */
        ON_HOLD,
        /**
         * project has been completed successful
         */
        COMPLETED,
        /**
         * project has been cancelled and will not run
         */
        CANCELLED
    }
}
