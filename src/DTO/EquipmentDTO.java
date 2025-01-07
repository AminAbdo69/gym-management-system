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
public class EquipmentDTO {
    private int id;
    private int GymHallID;
    private String name;
    private String type;
    private String condition;


    // Getters and Setters
    public int getId() { return id; }

    public int getGymHallID() {
        return GymHallID;
    }

    public void setGymHallID(int GymHallID) {
        this.GymHallID = GymHallID;
    }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCondition() { return condition; }

}

