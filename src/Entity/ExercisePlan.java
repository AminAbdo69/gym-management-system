package Entity;

import DAO.ExerciseDAO;
import DAO.ExercisePlanDAO;
import java.util.ArrayList;
import java.util.List;

public class ExercisePlan {
    // Attributes
    private int id;                  // Unique identifier
    private String planName;         // Name of the exercise plan
    private String type;             // Type of plan
    private List<Exercise> exercises; // List of exercises in the plan
    private ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();

    // Constructor
    public ExercisePlan(String planName, String type) {
        this.planName = planName;
        this.type = type;
        this.exercises = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // Methods to update the database
    public void addExercise(int exerciseid) {
        ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
        ExerciseDAO exercisedao = new ExerciseDAO();
        Exercise exercise = exercisedao.getExercise(exerciseid);
        exercisePlanDAO.addExerciseToPlan(exerciseid, this.id);
        exercises.add(exercise);
        System.out.println("Exercise added to the plan: " + exercise.getName());
        // Update the database
        updateExercisePlan();
    }

    public void removeExercise(int id) {
        exercises.removeIf(exercise -> exercise.getId() == id);
        System.out.println("Exercise with ID " + id + " removed from the plan.");
        // Update the database
        updateExercisePlan();
    }

    public void updateExercise(int id, String name, int sets, int reps) {
        for (Exercise exercise : exercises) {
            if (exercise.getId() == id) {
                exercise.setName(name);
                exercise.setSets(sets);
                exercise.setReps(reps);
                System.out.println("Exercise updated: " + name);
                break;
            }
        }
        // Update the database
        updateExercisePlan();
    }

    // Add the exercise plan to the database
    public void addExercisePlan() {
        exercisePlanDAO.addExercisePlan(this);
    }

    // Fetch exercise plan by ID from the database
    public static ExercisePlan getExercisePlan(int id) {
        ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
        return exercisePlanDAO.getExercisePlan(id);
    }

    // Fetch all exercise plans from the database
    public static List<ExercisePlan> getAllExercisePlans() {
        ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
        return exercisePlanDAO.getAllExercisePlans();
    }
    public static List<Exercise> getAllExerciseinPlan(int id) {
        ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
        return exercisePlanDAO.getAllExercisesInPlans(id);
    }

    // Update exercise plan in the database
    public void updateExercisePlan() {
        exercisePlanDAO.updateExercisePlan(this);
    }

    // Delete exercise plan from the database
    public void deleteExercisePlan() {
        exercisePlanDAO.deleteExercisePlan(this.id);
    }

    @Override
    public String toString() {
        return "ExercisePlan{" + "id=" + id + ", planName=" + planName + ", type=" + type + '}';
    } 
    
    
}


//+ ", exercises=" + exercises 