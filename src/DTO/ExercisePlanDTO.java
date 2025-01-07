/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author hp
 */

import java.util.List;

public class ExercisePlanDTO {
    private int id;                  // Unique identifier
    private String planName;         // Name of the exercise plan
    private String type;             // Type of plan
    private List<ExerciseDTO> exercises; // List of exercises in the plan

    // Constructor
    public ExercisePlanDTO( String planName, String type, List<ExerciseDTO> exercises) {
        this.planName = planName;
        this.type = type;
        this.exercises = exercises;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}

