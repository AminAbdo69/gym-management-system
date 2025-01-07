package Entity;

import DAO.EquipmentDAO;

public class Equipment {
    private int id;            // Unique identifier
    private int GymHallID;
    private String name;       // Name of the equipment
    private String type;       // Type of equipment
    private String state;      // Current condition

    // Constructor
    public Equipment(int GymHallID, String name, String type, String condition) {
        this.GymHallID = GymHallID;
        this.name = name;
        this.type = type;
        this.state = condition;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    
    public int getGymHallID() { return GymHallID; }
    public void setGymHallID(int GymHallID) { this.GymHallID = GymHallID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCondition() { return state; }
    public void setCondition(String condition) { this.state = condition; }

    // Update condition of the equipment
    public void updateCondition(String newCondition) {
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        equipmentDAO.updateCondition(this.id, newCondition);
    }

    // Check if the equipment is available
    public boolean isAvailable() {
        return state.equalsIgnoreCase("Good");
    }

    // Schedule maintenance
    public void scheduleMaintenance() {
        setCondition("Scheduled for Maintenance");
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        equipmentDAO.updateCondition(this.id, "Scheduled for Maintenance");
        System.out.println(name + " is scheduled for maintenance.");
    }

    // Add equipment to the database
    public void addToDatabase() {
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        equipmentDAO.addEquipment(this);
    }

    // Delete equipment from the database
    public void removeFromDatabase() {
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        equipmentDAO.removeEquipment(this.id);
    }

    @Override
    public String toString() {
        return "Equipment{" + "id=" + id + ", GymHallID=" + GymHallID + ", name=" + name + ", type=" + type + ", state=" + state + '}';
    }
    
    
}
