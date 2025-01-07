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
public class MemberDTO extends PersonDTO {
    private int AssignedTrainerID; // Trainer assigned to the member
    private double weight;           // Weight of the member
    private int exercisePlan;     // Exercise plan assigned to the member
    private int schedule;         // Member's exercise schedule

    // Constructor
    public MemberDTO(String fName, String lName, int age, String phone, String address, String gender, int AssignedTrainerID, double weight, int exercisePlan, int schedule) {
        super(fName, lName, age, phone, address, gender);
        this.AssignedTrainerID = AssignedTrainerID;
        this.weight = weight;
        this.exercisePlan = exercisePlan;
        this.schedule = schedule;
    }

    // Getters and Setters
    public int getAssignedTrainer() {
        return AssignedTrainerID;
    }

    public void setAssignedTrainer(int assignedTrainer) {
        this.AssignedTrainerID = assignedTrainer;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getExercisePlan() {
        return exercisePlan;
    }

    public void setExercisePlan(int exercisePlan) {
        this.exercisePlan = exercisePlan;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return super.toString() + ", MemberDTO{" +
                "assignedTrainer='" + AssignedTrainerID + '\'' +
                ", weight=" + weight +
                ", exercisePlan='" + exercisePlan + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}

