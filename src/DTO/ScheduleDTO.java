package DTO;

public class ScheduleDTO {
    private String day;            // The day of the week
    private int exerciseId;        // Single Exercise ID for the day

    // Constructor
    public ScheduleDTO(String day, int exerciseId) {
        this.day = day;
        this.exerciseId = exerciseId;
    }

    // Getters and Setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "day='" + day + '\'' +
                ", exerciseId=" + exerciseId +
                '}';
    }
}
