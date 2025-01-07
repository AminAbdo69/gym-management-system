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

public class ExerciseDTO {
    private int id;                       // Unique identifier
    private String name;                  // Name of the exercise
    private int sets;                     // Number of sets
    private int reps;                     // Number of repetitions
    private List<String> exerciseEquipments; // List of equipment used for the exercise

    // Constructor
    public ExerciseDTO( String name, int sets, int reps, List<String> exerciseEquipments) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.exerciseEquipments = exerciseEquipments;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public List<String> getExerciseEquipments() {
        return exerciseEquipments;
    }

    public void setExerciseEquipments(List<String> exerciseEquipments) {
        this.exerciseEquipments = exerciseEquipments;
    }
}

