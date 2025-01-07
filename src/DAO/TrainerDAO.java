

package DAO;

import Entity.Member;
import Entity.Trainer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";

    // Method to add a trainer to the database
    public void addTrainer(Trainer trainer, int personId) {
        String sql = "INSERT INTO Trainer (ID, Specialist) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personId);
            pstmt.setString(2, trainer.getSpecialist());
            pstmt.executeUpdate();
            System.out.println("Trainer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding trainer: " + e.getMessage());
        }
    }

    // Method to get a trainer by ID
    public Trainer getTrainer(int id) {
        String sql = "SELECT * FROM Trainer JOIN Person ON Trainer.ID = Person.ID WHERE Trainer.ID = ?";
        Trainer trainer = null;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                trainer = new Trainer(
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getInt("Age"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Gender"),
                        rs.getString("Specialist")
                );
                trainer.setId(rs.getInt("ID"));
                trainer.loadCustomers(); // Load the trainer's customers
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trainer: " + e.getMessage());
        }
        return trainer;
    }
    
    
    
    
    public List<Trainer> getAllTrainers() {
        String sql = "SELECT * FROM Trainer JOIN Person ON Trainer.ID = Person.ID";
        List<Trainer> trainers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               Trainer trainer = new Trainer(
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getInt("Age"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Gender"),
                        rs.getString("Specialist")
                );
                trainer.setId(rs.getInt("ID"));
                trainers.add(trainer);
                
                //trainer.loadCustomers(); // Load the trainer's customers
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trainer: " + e.getMessage());
        }
        return trainers;
    }

    // Method to update a trainer's information
    public void updateTrainer(Trainer trainer) {
        String sql = "UPDATE Trainer SET Specialist = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainer.getSpecialist());
            pstmt.setInt(2, trainer.getId());
            pstmt.executeUpdate();
            System.out.println("Trainer updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating trainer: " + e.getMessage());
        }
    }

    // Method to delete a trainer by ID
    public void deleteTrainer(int trainerId) {
        String sql = "DELETE FROM Trainer WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            pstmt.executeUpdate();
            System.out.println("Trainer deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting trainer: " + e.getMessage());
        }
    }

    // Method to add a customer (Member) to a trainer
    public void addCustomerToTrainer(int trainerId, int customerId) {
        // Check if trainerId exists in the Trainer table
        if (!isTrainerExists(trainerId)) {
            System.out.println("Error: Trainer with ID " + trainerId + " does not exist.");
            return;
        }

        // Check if customerId exists in the Member table
        if (!isCustomerExists(customerId)) {
            System.out.println("Error: Customer with ID " + customerId + " does not exist.");
            return;
        }

        String sql = "INSERT INTO Trainer_Customer (trainerId, customerId) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer added to trainer successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding customer to trainer: " + e.getMessage());
        }
    }

    // Helper method to check if a trainer exists
    private boolean isTrainerExists(int trainerId) {
        String sql = "SELECT COUNT(*) FROM Trainer WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error checking trainer existence: " + e.getMessage());
        }
        return false;
    }

    // Helper method to check if a customer exists
    private boolean isCustomerExists(int customerId) {
        String sql = "SELECT COUNT(*) FROM Member WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error checking customer existence: " + e.getMessage());
        }
        return false;
    }


    // Method to remove a customer (Member) from a trainer
    public void removeCustomerFromTrainer(int trainerId, int customerId) {
        String sql = "DELETE FROM Trainer_Customer WHERE trainerId = ? AND customerId = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer removed from trainer successfully.");
        } catch (SQLException e) {
            System.out.println("Error removing customer from trainer: " + e.getMessage());
        }
    }

    // Method to get all customers (Members) assigned to a specific trainer
    public List<Member> getCustomersByTrainer(int trainerId) {
        String sql = "SELECT p.ID, p.FName, p.LName, p.Age, p.Phone, p.Address, p.Gender, " +
                 "m.Weight, m.ExercisePlanID, m.ScheduleID, m.AssignedTrainerID " +
                 "FROM Person p " +
                 "JOIN Member m ON p.ID = m.ID " +  // Join Person with Member using ID
                 "JOIN Trainer_Customer tc ON m.ID = tc.customerId " +
                 "WHERE tc.trainerId = ?";
        List<Member> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Create Member object using Person attributes and Member-specific fields
                Member member = new Member(
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getInt("Age"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Gender"),
                        rs.getInt("AssignedTrainerID"), // Member-specific field
                        rs.getDouble("Weight"),
                        rs.getInt("ExercisePlanID"),
                        rs.getInt("ScheduleID")
                );
                member.setId(rs.getInt("ID"));  // Set the ID from the Person table
                customers.add(member);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers by trainer: " + e.getMessage());
        }
    return customers;
}


}
