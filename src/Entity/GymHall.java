package Entity;

import DAO.GymHallDAO;
import java.util.List;

public class GymHall {
    // Attributes
    private int id;                   // Unique identifier
    private String name;              // Name of the gym hall
    private int capacity;             // Maximum capacity of the gym hall

    // Constructor
    public GymHall(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Methods that interact with the DAO

    public void addEquipment(Equipment equipment) {
        GymHallDAO gymHallDAO = new GymHallDAO();
        gymHallDAO.addEquipmentToGymHall(this.id, equipment);
    }

    public void removeEquipment(int equipmentId) {
        GymHallDAO gymHallDAO = new GymHallDAO();
        gymHallDAO.removeEquipmentFromGymHall(this.id, equipmentId);
    }

    public Equipment findEquipmentByName(String name) {
        GymHallDAO gymHallDAO = new GymHallDAO();
        return gymHallDAO.findEquipmentInGymHallByName(this.id, name);
    }

    public int getTotalEquipment() {
        GymHallDAO gymHallDAO = new GymHallDAO();
        return gymHallDAO.getTotalEquipmentInGymHall(this.id);
    }

    @Override
    public String toString() {
        return "GymHall{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + '}';
    }
    
}
