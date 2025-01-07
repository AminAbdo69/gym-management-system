package DAO;

import Entity.Exercise;
import Entity.ExercisePlan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";

    // Add exercise to the database
    public void addExercise(Exercise exercise) {
        String sql = "INSERT INTO Exercise (name, sets, reps) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exercise.getName());
            pstmt.setInt(2, exercise.getSets());
            pstmt.setInt(3, exercise.getReps());

            pstmt.executeUpdate();
            System.out.println("Exercise added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding exercise: " + e.getMessage());
        }
    }

    // Get exercise by ID
    public Exercise getExercise(int id) {
        String sql = "SELECT * FROM Exercise WHERE id = ?";
        Exercise exercise = null;
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exercise = new Exercise(rs.getString("name"), rs.getInt("sets"), rs.getInt("reps"));
                exercise.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving exercise: " + e.getMessage());
        }
        return exercise;
    }

    // Get all exercises
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM Exercise";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Exercise exercise = new Exercise(rs.getString("name"), rs.getInt("sets"), rs.getInt("reps"));
                exercise.setId(rs.getInt("id"));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all exercises: " + e.getMessage());
        }
        return exercises;
    }
    

    // Update exercise
    public void updateExercise(Exercise exercise) {
        String sql = "UPDATE Exercise SET name = ?, sets = ?, reps = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exercise.getName());
            pstmt.setInt(2, exercise.getSets());
            pstmt.setInt(3, exercise.getReps());
            pstmt.setInt(4, exercise.getId());

            pstmt.executeUpdate();
            System.out.println("Exercise updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating exercise: " + e.getMessage());
        }
    }

    // Delete exercise
    public void deleteExercise(int id) {
        String sql = "DELETE FROM Exercise WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exercise deleted successfully.");
            } else {
                System.out.println("Exercise not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting exercise: " + e.getMessage());
        }
    }
    

    // Fetch all ExercisePlans associated with an Exercise
    public List<ExercisePlan> getExercisePlansForExercise(int exerciseId) {
        List<ExercisePlan> exercisePlans = new ArrayList<>();
        String sql = "SELECT ep.* FROM ExercisePlan ep " +
                     "JOIN ExercisePlan_Exercise epe ON ep.ID = epe.ExercisePlanID " +
                     "WHERE epe.ExerciseID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exerciseId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ExercisePlan exercisePlan = new ExercisePlan(rs.getString("PlanName"), rs.getString("Type"));
                exercisePlan.setId(rs.getInt("ID"));
                exercisePlans.add(exercisePlan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercisePlans;
    }
    
    
    // Method to add an exercise to an exercise plan (many-to-many relationship)
    public void addExerciseToPlan(int exerciseId, int exercisePlanId) {
        String sql = "INSERT INTO ExercisePlan_Exercise (ExerciseID, ExercisePlanID) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exerciseId);
            pstmt.setInt(2, exercisePlanId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to remove an exercise from an exercise plan
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

    // Fetch all exercises in a specific exercise plan
    public List<Exercise> getExercisesForPlan(int exercisePlanId) {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT e.* FROM Exercise e " +
                     "JOIN ExercisePlan_Exercise ep ON e.ID = ep.ExerciseID " +
                     "WHERE ep.ExercisePlanID = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exercisePlanId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Exercise exercise = new Exercise(rs.getString("Name"), rs.getInt("Sets"), rs.getInt("Reps"));
                exercise.setId(rs.getInt("ID"));
                exercises.add(exercise);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }
}
