package com.sherbrookeuniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {

    private String speciality;
    private String birthday;
    private double grade;
    private String department;
    private String adress;
    private String phone;       // ✅ ajouté
    private String firstName;   // ✅ ajouté
    private String lastName;    // ✅ ajouté

    @Column(updatable = false)
    private LocalDateTime createdAt; // ✅ ajouté

    private LocalDateTime updatedAt; // ✅ ajouté

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "enseignant_cours",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "enseignant_classes",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
    private Set<Classe> classes;
}
