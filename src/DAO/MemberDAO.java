
package DAO;

import Entity.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private static final String USERNAME = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project"; // Your database name

    // Method to add a member
    public void addMember(Member member, int personId) {
        String sql = "INSERT INTO Member (ID, AssignedTrainerID, Weight, ExercisePlanID, ScheduleID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, personId);  // Person's ID
            pstmt.setInt(2, member.getAssignedTrainer());
            pstmt.setDouble(3, member.getWeight());
            pstmt.setInt(4, member.getExercisePlan());
            pstmt.setInt(5, member.getSchedule());

            pstmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding Member: " + e.getMessage());
        }
    }

    // Method to retrieve a member by ID
    public Member getMember(int memberId) {
        String sql = "SELECT P.FName, P.LName, P.Age, P.Phone, P.Address, P.Gender, M.AssignedTrainerID, M.Weight, M.ExercisePlanID, M.ScheduleID " +
                     "FROM Person P JOIN Member M ON P.ID = M.ID WHERE M.ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve Person details
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                int age = rs.getInt("Age");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String gender = rs.getString("Gender");

                // Retrieve Member details
                int assignedTrainerID = rs.getInt("AssignedTrainerID");
                double weight = rs.getDouble("Weight");
                int exercisePlanID = rs.getInt("ExercisePlanID");
                int scheduleID = rs.getInt("ScheduleID");

                // Construct and return a Member object
                Member member = new Member(fName, lName, age, phone, address, gender, assignedTrainerID, weight, exercisePlanID, scheduleID);
                member.setId(memberId); // Set the ID manually since it's not in the constructor
                return member;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Member: " + e.getMessage());
        }
        return null; // Return null if no member is found
    }

    // Method to update a member's details
    public void updateMember(Member member, int memberId) {
        String sql = "UPDATE Member SET AssignedTrainerID = ?, Weight = ?, ExercisePlanID = ?, ScheduleID = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, member.getAssignedTrainer());
            pstmt.setDouble(2, member.getWeight());
            pstmt.setInt(3, member.getExercisePlan());
            pstmt.setInt(4, member.getSchedule());
            pstmt.setInt(5, memberId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("No member found with ID: " + memberId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating Member: " + e.getMessage());
        }
    }

    // Method to delete a member by ID
    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM Member WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member deleted successfully.");
                return true;
            } else {
                System.out.println("No member found with ID: " + memberId);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting Member: " + e.getMessage());
            return false;
        }
    }
    
    

    public List<Member> getAllMembers() {
        String sql = "SELECT P.ID, P.FName, P.LName, P.Age, P.Phone, P.Address, P.Gender, " +
                     "M.AssignedTrainerID, M.Weight, M.ExercisePlanID, M.ScheduleID " +
                     "FROM Person P JOIN Member M ON P.ID = M.ID";
        List<Member> members = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Retrieve Person details
                int id = rs.getInt("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                int age = rs.getInt("Age");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String gender = rs.getString("Gender");

                // Retrieve Member details
                int assignedTrainerID = rs.getInt("AssignedTrainerID");
                double weight = rs.getDouble("Weight");
                int exercisePlanID = rs.getInt("ExercisePlanID");
                int scheduleID = rs.getInt("ScheduleID");

                // Construct and add a Member object to the list
                Member member = new Member(fName, lName, age, phone, address, gender, assignedTrainerID, weight, exercisePlanID, scheduleID);
                member.setId(id); // Set the ID manually since it's not in the constructor
                members.add(member);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving members: " + e.getMessage());
        }

        return members; // Return the list of members (it will be empty if no members are found)
    }


    // Utility methods to update specific fields
    public void updateAssignedTrainer(int memberId, int assignedTrainerID) {
        updateField("AssignedTrainerID", memberId, assignedTrainerID);
    }

    public void updateWeight(int memberId, double weight) {
        updateField("Weight", memberId, weight);
    }

    public void updateExercisePlan(int memberId, int exercisePlanID) {
        updateField("ExercisePlanID", memberId, exercisePlanID);
    }

    public void updateSchedule(int memberId, int scheduleID) {
        updateField("ScheduleID", memberId, scheduleID);
    }

    // Utility method for updating fields
    private void updateField(String fieldName, int memberId, Object value) {
        String sql = "UPDATE Member SET " + fieldName + " = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, value);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating " + fieldName + ": " + e.getMessage());
        }
    }
}
