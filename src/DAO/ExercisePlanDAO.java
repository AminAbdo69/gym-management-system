package DAO;

import Entity.Exercise;
import Entity.ExercisePlan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExercisePlanDAO {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";

    // Add exercise plan to the database
    public void addExercisePlan(ExercisePlan exercisePlan) {
        String sql = "INSERT INTO exerciseplan (planName, type) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, exercisePlan.getPlanName());
            pstmt.setString(2, exercisePlan.getType());

            pstmt.executeUpdate();

            // Get the generated ID of the new exercise plan
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercisePlan.setId(generatedKeys.getInt(1));
            }
            System.out.println("Exercise plan added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding exercise plan: " + e.getMessage());
        }
    }

    // Get exercise plan by ID
    public ExercisePlan getExercisePlan(int id) {
        String sql = "SELECT * FROM exerciseplan WHERE id = ?";
        ExercisePlan exercisePlan = null;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exercisePlan = new ExercisePlan(rs.getString("planName"), rs.getString("type"));
                exercisePlan.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving exercise plan: " + e.getMessage());
        }
        return exercisePlan;
    }

    // Get all exercise plans
    public List<ExercisePlan> getAllExercisePlans() {
        List<ExercisePlan> exercisePlans = new ArrayList<>();
        String sql = "SELECT * FROM exerciseplan";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ExercisePlan exercisePlan = new ExercisePlan(rs.getString("planName"), rs.getString("type"));
                exercisePlan.setId(rs.getInt("id"));
                exercisePlans.add(exercisePlan);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all exercise plans: " + e.getMessage());
        }
        return exercisePlans;
    }

    // Update exercise plan in the database
    public void updateExercisePlan(ExercisePlan exercisePlan) {
        String sql = "UPDATE exerciseplan SET planName = ?, type = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exercisePlan.getPlanName());
            pstmt.setString(2, exercisePlan.getType());
            pstmt.setInt(3, exercisePlan.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exercise plan updated successfully.");
            } else {
                System.out.println("No exercise plan found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating exercise plan: " + e.getMessage());
        }
    }

    // Delete exercise plan from the database
    public void deleteExercisePlan(int id) {
        String sql = "DELETE FROM exerciseplan WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exercise plan deleted successfully.");
            } else {
                System.out.println("No exercise plan found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting exercise plan: " + e.getMessage());
        }
    }
    
     public void removeExerciseFromPlan(int exerciseId, int exercisePlanId) {
        String sql = "DELETE FROM ExercisePlan_Exercise WHERE ExerciseID = ? AND ExercisePlanID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exerciseId);
            pstmt.setInt(2, exercisePlanId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void addExerciseToPlan(int exerciseId, int exercisePlanId) {
        String sql = "INSERT INTO ExercisePlan_Exercise (ExerciseID, ExercisePlanID) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exerciseId);
            pstmt.setInt(2, exercisePlanId);
            pstmt.executeUpdate();

            System.out.println("Exercise added to the exercise plan successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Exercise> getAllExercisesInPlans(int exercisePlanId) {
    String sql = "SELECT e.* " +
                 "FROM Exercise e " +
                 "JOIN ExercisePlan_Exercise ep ON e.ID = ep.ExerciseID " +
                 "WHERE ep.ExercisePlanID = ?";  // Add condition to filter by exercisePlanId
    List<Exercise> exercises = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Set the exercise plan ID as a parameter for the query
        pstmt.setInt(1, exercisePlanId);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Exercise exercise = new Exercise(
                    rs.getString("Name"),
                    rs.getInt("Sets"),
                    rs.getInt("Reps")
                );
                exercise.setId(rs.getInt("ID"));
                exercises.add(exercise);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return exercises;
}



}
