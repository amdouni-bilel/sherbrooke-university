package com.sherbrookeuniversity.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private String speciality;
    private String birthday;
    private String adress;
    private double score;
    private boolean isValidated = false;
}
