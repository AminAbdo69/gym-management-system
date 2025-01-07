package DAO;

import DTO.EquipmentDTO;
import Entity.Equipment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    // Add equipment to the database
    public int addEquipment(Equipment equipment) {
        String query = "INSERT INTO Equipment (GymHallID, name, type, state) VALUES (?, ?, ?, ?)";
        int generatedId = -1; // Default value for ID if the insertion fails
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, equipment.getGymHallID());
            stmt.setString(2, equipment.getName());
            stmt.setString(3, equipment.getType());
            stmt.setString(4, equipment.getCondition());

            int rowsAffected = stmt.executeUpdate();  // Execute the update

            // If insertion is successful, retrieve the generated ID
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);  // Get the generated ID (first column)
                    }
                }
            }
            System.out.println("Equipment added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;  // Return the generated ID or -1 if insertion failed
    }


    // Update equipment condition in the database
    public void updateCondition(int equipmentId, String newCondition) {
        String query = "UPDATE Equipment SET state = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newCondition);
            stmt.setInt(2, equipmentId);
            stmt.executeUpdate();
            System.out.println("Equipment condition updated successfully. t");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find equipment by ID
    public Equipment findEquipmentById(int equipmentId) {
        String query = "SELECT * FROM Equipment WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, equipmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Equipment(
//                    rs.getInt("ID"),
                    rs.getInt("GymHallID"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("state")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete equipment from the database by ID
    public void removeEquipment(int equipmentId) {
        String query = "DELETE FROM Equipment WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, equipmentId);
            stmt.executeUpdate();
            System.out.println("Equipment removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all equipment
    public List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        String query = "SELECT * FROM Equipment";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Equipment equipment = new Equipment(
                    rs.getInt("GymHallID"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("state")
                );
                equipment.setId(rs.getInt("ID"));
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }
}
