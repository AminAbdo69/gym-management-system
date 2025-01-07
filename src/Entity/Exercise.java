//package Entity;
//
//import DAO.ExerciseDAO;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Exercise {
//    // Attributes
//    private int id;                         // Unique identifier
//    private String name;                    // Name of the exercise
//    private int sets;                       // Number of sets
//    private int reps;                       // Number of repetitions
//    private List<String> exerciseEquipments; // List of equipment used for the exercise
//    private ExerciseDAO exerciseDAO = new ExerciseDAO();
//
//    // Constructor
//    public Exercise(String name, int sets, int reps) {
//        this.name = name;
//        this.sets = sets;
//        this.reps = reps;
//        this.exerciseEquipments = new ArrayList<>();
//    }
//
//    // Getters and Setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getSets() {
//        return sets;
//    }
//
//    public void setSets(int sets) {
//        this.sets = sets;
//    }
//
//    public int getReps() {
//        return reps;
//    }
//
//    public void setReps(int reps) {
//        this.reps = reps;
//    }
//
//    public List<String> getExerciseEquipments() {
//        return exerciseEquipments;
//    }
//
//    // Methods to update database
//
//    // Add exercise to the database
//    public void addExercise() {
//        exerciseDAO.addExercise(this);
//    }
//
//    // Fetch exercise by ID from the database
//    public static Exercise getExercise(int id) {
//        ExerciseDAO exerciseDAO = new ExerciseDAO();
//        return exerciseDAO.getExercise(id);
//    }
//
//    // Fetch all exercises from the database
//    public static List<Exercise> getAllExercises() {
//        ExerciseDAO exerciseDAO = new ExerciseDAO();
//        return exerciseDAO.getAllExercises();
//    }
//
//    // Delete exercise from the database
//    public void deleteExercise() {
//        exerciseDAO.deleteExercise(this.id);
//    }
//
//    // Update exercise in the database
//    public void updateExercise() {
//        exerciseDAO.updateExercise(this);
//    }
//
//    // Methods to modify exercise details and update in database
//
//    public void updateReps(int reps) {
//        this.reps = reps;
//        System.out.println("Reps updated to: " + reps);
//        // Update the database
//        updateExercise();
//    }
//
//    public void increaseReps(int amount) {
//        this.reps += amount;
//        System.out.println("Reps increased by: " + amount + ", new total: " + this.reps);
//        // Update the database
//        updateExercise();
//    }
//
//    public void decreaseReps(int amount) {
//        if (this.reps - amount >= 0) {
//            this.reps -= amount;
//            System.out.println("Reps decreased by: " + amount + ", new total: " + this.reps);
//            // Update the database
//            updateExercise();
//        } else {
//            System.out.println("Cannot decrease reps below 0.");
//        }
//    }
//
//    public void addEquipment(String equipment) {
//        exerciseEquipments.add(equipment);
//        System.out.println("Equipment added: " + equipment);
//        // You can add database logic to update equipment info if needed
//    }
//
//    @Override
//    public String toString() {
//        return "Exercise{" + "id=" + id + ", name=" + name + ", sets=" + sets + ", reps=" + reps + ", exerciseEquipments=" + exerciseEquipments + '}';
//    }
//    
//}


package Entity;

import DAO.ExerciseDAO;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    // Attributes
    private int id;                         // Unique identifier
    private String name;                    // Name of the exercise
    private int sets;                       // Number of sets
    private int reps;                       // Number of repetitions
    private List<String> exerciseEquipments; // List of equipment used for the exercise
    private List<ExercisePlan> exercisePlans; // List of exercise plans this exercise belongs to
    private ExerciseDAO exerciseDAO = new ExerciseDAO();

    // Constructor
    public Exercise(String name, int sets, int reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.exerciseEquipments = new ArrayList<>();
        this.exercisePlans = new ArrayList<>();
    }

    public Exercise(int exerciseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public List<String> getExerciseEquipments() {
        return exerciseEquipments;
    }

    public void setExerciseEquipments(List<String> exerciseEquipments) {
        this.exerciseEquipments = exerciseEquipments;
    }

    public List<ExercisePlan> getExercisePlans() {
        return exercisePlans;
    }

    public void setExercisePlans(List<ExercisePlan> exercisePlans) {
        this.exercisePlans = exercisePlans;
    }

    // Methods to update database

    // Add exercise to the database
    public void addExercise() {
        exerciseDAO.addExercise(this);
    }

    // Fetch exercise by ID from the database
    public static Exercise getExercise(int id) {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        return exerciseDAO.getExercise(id);
    }

    // Fetch all exercises from the database
    public static List<Exercise> getAllExercises() {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        return exerciseDAO.getAllExercises();
    }

    // Delete exercise from the database
    public void deleteExercise() {
        exerciseDAO.deleteExercise(this.id);
    }

    // Update exercise in the database
    public void updateExercise() {
        exerciseDAO.updateExercise(this);
    }

    // Methods to modify exercise details and update in database

    public void updateReps(int reps) {
        this.reps = reps;
        System.out.println("Reps updated to: " + reps);
        // Update the database
        updateExercise();
    }

    public void increaseReps(int amount) {
        this.reps += amount;
        System.out.println("Reps increased by: " + amount + ", new total: " + this.reps);
        // Update the database
        updateExercise();
    }

    public void decreaseReps(int amount) {
        if (this.reps - amount >= 0) {
            this.reps -= amount;
            System.out.println("Reps decreased by: " + amount + ", new total: " + this.reps);
            // Update the database
            updateExercise();
        } else {
            System.out.println("Cannot decrease reps below 0.");
        }
    }

    public void addEquipment(String equipment) {
        exerciseEquipments.add(equipment);
        System.out.println("Equipment added: " + equipment);
        // You can add database logic to update equipment info if needed
    }

    // Add exercise to an exercise plan
    public void addToExercisePlan(ExercisePlan exercisePlan) {
        if (!this.exercisePlans.contains(exercisePlan)) {
            this.exercisePlans.add(exercisePlan);
            System.out.println("Exercise added to Exercise Plan: " + exercisePlan.getPlanName());
            // You may want to add a method in DAO to update the ExercisePlan_Exercise table
        } else {
            System.out.println("Exercise already in this plan.");
        }
    }
    
        // Method to get all ExercisePlans associated with this Exercise
    public void loadExercisePlans() {
        this.exercisePlans = exerciseDAO.getExercisePlansForExercise(this.id);
    }
    
        // You can also provide a method to directly fetch the exercise plans when needed:
    public List<ExercisePlan> fetchExercisePlans() {
        return exerciseDAO.getExercisePlansForExercise(this.id);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                
                '}';
    }
}






//", exerciseEquipments=" + exerciseEquipments +
//                ", exercisePlans=" + exercisePlans +