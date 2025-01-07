package DAO;

import Entity.Equipment;
import Entity.GymHall;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymHallDAO {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";

    // Method to add a new GymHall
    public void addGymHall(GymHall gymHall) {
        String sql = "INSERT INTO gymhall (name, capacity) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, gymHall.getName());
            pstmt.setInt(2, gymHall.getCapacity());

            pstmt.executeUpdate();
            System.out.println("GymHall added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding GymHall: " + e.getMessage());
        }
    }

    // Method to get a GymHall by ID
    public GymHall getGymHall(int id) {
        String sql = "SELECT * FROM gymhall WHERE id = ?";
        GymHall gymHall = null;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                gymHall = new GymHall(rs.getString("name"), rs.getInt("capacity"));
                gymHall.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving GymHall: " + e.getMessage());
        }
        return gymHall;
    }

    // Method to update GymHall capacity
    public void updateGymHallCapacity(int id, int newCapacity) {
        String sql = "UPDATE gymhall SET capacity = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newCapacity);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("GymHall capacity updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating GymHall capacity: " + e.getMessage());
        }
    }

    // Method to delete GymHall by ID
    public void deleteGymHall(int id) {
        String sql = "DELETE FROM gymhall WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("GymHall deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting GymHall: " + e.getMessage());
        }
    }

    // Method to get all GymHalls
    public List<GymHall> getAllGymHalls() {
        List<GymHall> gymHallList = new ArrayList<>();
        String sql = "SELECT * FROM gymhall";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                GymHall gymHall = new GymHall(rs.getString("name"), rs.getInt("capacity"));
                gymHall.setId(rs.getInt("id"));
                gymHallList.add(gymHall);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all GymHalls: " + e.getMessage());
        }
        return gymHallList;
    }

    // Methods for managing Equipment

    // Add equipment to a gym hall
    public void addEquipmentToGymHall(int gymHallId, Equipment equipment) {
        String sql = "INSERT INTO equipment (GymHallID, Name, Type, state) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gymHallId);
            pstmt.setString(2, equipment.getName());
            pstmt.setString(3, equipment.getType());
            pstmt.setString(4, equipment.getCondition());

            pstmt.executeUpdate();
            System.out.println("Equipment added to GymHall.");
        } catch (SQLException e) {
            System.out.println("Error adding equipment: " + e.getMessage());
        }
    }

    // Remove equipment from a gym hall
    public void removeEquipmentFromGymHall(int gymHallId, int equipmentId) {
        String sql = "DELETE FROM equipment WHERE GymHallID = ? AND ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gymHallId);
            pstmt.setInt(2, equipmentId);

            pstmt.executeUpdate();
            System.out.println("Equipment removed from GymHall.");
        } catch (SQLException e) {
            System.out.println("Error removing equipment: " + e.getMessage());
        }
    }

    // Find equipment in a gym hall by name
    public Equipment findEquipmentInGymHallByName(int gymHallId, String name) {
        String sql = "SELECT * FROM equipment WHERE GymHallID = ? AND Name = ?";
        Equipment equipment = null;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gymHallId);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                equipment = new Equipment(
                    rs.getInt("GymHallID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getString("state")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding equipment: " + e.getMessage());
        }
        return equipment;
    }

    // Get total number of equipment in a gym hall
    public int getTotalEquipmentInGymHall(int gymHallId) {
        String sql = "SELECT COUNT(*) FROM equipment WHERE GymHallID = ?";
        int totalEquipment = 0;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gymHallId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalEquipment = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getting total equipment: " + e.getMessage());
        }
        return totalEquipment;
    }
}
