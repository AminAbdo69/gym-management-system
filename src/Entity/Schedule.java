package Entity;

import DAO.ScheduleDAO;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private int id; // Unique identifier
    private String day; // The day of the week
    private List<Integer> exerciseIds; // List of exercise IDs for the schedule
    private ScheduleDAO scheduleDAO;

    // Constructor
    public Schedule(String day) {
        this.day = day;
        this.scheduleDAO = new ScheduleDAO();
        exerciseIds = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Integer> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Integer> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }

    // Method to add an exercise to the schedule
    public boolean addExerciseToDay(int DayId ,int exerciseId) {
        if (scheduleDAO.addExerciseToDay(DayId, exerciseId)) {
            exerciseIds.add(exerciseId);
            return true;
        }
        return false;
    }

    // Method to view all exercises for the day
    public List<String> viewExercisesForDay(String day) {
        return scheduleDAO.viewExercisesForDay(day);
    }

    // Method to remove an exercise from the schedule
    public boolean removeExerciseFromDay(int id,int exerciseId) {
        if (scheduleDAO.removeExerciseFromDay(id, exerciseId)) {
            exerciseIds.remove(Integer.valueOf(exerciseId));
            return true;
        }
        return false;
    }

    // Method to clear all exercises for the day
    public boolean clearDay() {
        if (scheduleDAO.clearDay(this.id)) {
            exerciseIds.clear();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", exerciseIds=" + exerciseIds +
                '}';
    }
}
