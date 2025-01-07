package DAO;

import Entity.Schedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";
    
    // Method to add an exercise to a specific schedule (in the Schedule_Exercise table)
    public boolean addExerciseToDay(int scheduleId, int exerciseId) {
        String sql = "INSERT INTO Schedule_Exercise (ScheduleID, ExerciseID) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, exerciseId);
            pstmt.executeUpdate();
            System.out.println("Exercise added to schedule successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding exercise to schedule: " + e.getMessage());
            return false;
        }
    }
    
    // Method to retrieve all exercises for a specific schedule day
    /*public List<Integer> viewExercisesForDay(String day) {
        List<Integer> exercises = new ArrayList<>();
        String sql = "SELECT ExerciseID FROM Schedule s " +
                     "JOIN Schedule_Exercise se ON s.ID = se.ScheduleID " +
                     "WHERE s.Day = ?";
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, day);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                exercises.add(rs.getInt("ExerciseID"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving exercises for day: " + e.getMessage());
        }
        return exercises;
    }*/
    public List<String> viewExercisesForDay(String day) {
    List<String> exerciseNames = new ArrayList<>();
    String query = "SELECT e.name FROM schedule_exercise se " +
                   "JOIN schedule s ON se.ScheduleID = s.ID " +
                   "JOIN exercise e ON se.ExerciseID = e.ID " +
                   "WHERE s.day = ?";
    try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, day);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            exerciseNames.add(rs.getString("name"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return exerciseNames;
}
    

    // Method to remove an exercise from a specific schedule
    public boolean removeExerciseFromDay(int scheduleId, int exerciseId) {
        String sql = "DELETE FROM Schedule_Exercise WHERE ScheduleID = ? AND ExerciseID = ?";
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, exerciseId);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Exercise removed from schedule successfully.");
                return true;
            } else {
                System.out.println("No matching exercise found for removal.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error removing exercise from schedule: " + e.getMessage());
            return false;
        }
    }

    // Method to clear all exercises for a specific schedule
    public boolean clearDay(int scheduleId) {
        String sql = "DELETE FROM Schedule_Exercise WHERE ScheduleID = ?";
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, scheduleId);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("All exercises cleared from schedule successfully.");
                return true;
            } else {
                System.out.println("No exercises found to clear for this schedule.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error clearing exercises from schedule: " + e.getMessage());
            return false;
        }
    }

    // Method to get a Schedule by its ID
    public Schedule getScheduleById(int id) {
        String sql = "SELECT * FROM Schedule WHERE ID = ?";
        Schedule schedule = null;
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                schedule = new Schedule(rs.getString("Day"));
                schedule.setId(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving schedule by ID: " + e.getMessage());
        }
        return schedule;
    }
    
    
    // Method to get All Schedules 
    public List<Schedule> getAllSchedules() {
        String sql = "SELECT * FROM Schedule";
        List<Schedule> schedules = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Schedule schedule = new Schedule(rs.getString("Day"));
                schedule.setId(rs.getInt("ID"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving schedule by ID: " + e.getMessage());
        }
        return schedules;
    }

    // Method to insert a new schedule into the Schedule table
    public int insertSchedule(Schedule schedule) {
    String sql = "INSERT INTO Schedule (Day) VALUES (?)";
    
    try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
         
        pstmt.setString(1, schedule.getDay());
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            // Get the generated ID and set it on the Schedule object
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    schedule.setId(generatedKeys.getInt(1)); // Set the generated ID for the schedule
                    System.out.println("New schedule added with ID: " + schedule.getId());
                }
            }
            return schedule.getId();
        } else {
            System.out.println("Failed to add schedule.");
            return -1;
        }
    } catch (SQLException e) {
        System.out.println("Error inserting schedule: " + e.getMessage());
        return -1;
    }
}

}
