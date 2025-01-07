/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.sql.*;
import Entity.*;
import DTO.*;
import DAO.*;
import GUI.MainGUI;
import GUI.TrainerGUI;
import GUI.MemberGUI;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author hp
 */
public class JavaProject {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project";


   
    
    
    
    public static void main(String[] args){
        // Test Person
//        System.out.println("Testing Person Class:");
//        Person person = new Person("Trainer464", "Do5",33 , "1234567890", "123 Elm Street", "Female");
//        PersonDAO personDao = new PersonDAO();
//        personDao.addPerson(person);
//        System.out.println(person);

            // Test Member With Person 
            // in this class I make Person First then i take the id of this person and assign it to the new member 
            
//            Member member = new Member("ashraf" , "osama" ,20 , "01564010101" , "54fdas5sd" , "Male" , 3 ,40.0  ,1 ,1);
//            
//            // Step 1: Create the Person record and get the ID
//            PersonDAO persondao = new PersonDAO();
//            int personId = persondao.addPerson(member);
//            
//            MemberDAO memberdao = new MemberDAO();
//
//            if (personId != -1) {
//                // Step 2: Use the Person ID to create the Member record
//                memberdao.addMember(member, personId);
//                System.out.println("Member created with Person ID: " + personId);
//            } else {
//                System.out.println("Failed to create Person.");
//            }


            // Test Trainer With Person
            // in this class I make Person First then i take the id of this person and assign it to the new Trainer 

//            Trainer trainer = new Trainer("Trainer" , "Abdo" ,33 , "010101001" , "5sfddf" , "Male" , "Strength Training");
//            
//             PersonDAO persondao = new PersonDAO();
//            int trainerID = persondao.addPerson(trainer);
//            
//            TrainerDAO trainerdao = new TrainerDAO();
//
//            if (trainerID != -1) {
//                // Step 2: Use the Person ID to create the Member record
//                trainerdao.addTrainer(trainer, trainerID);
//                System.out.println("Trainer created with Person ID: " + trainerID);
//            } else {
//                System.out.println("Failed to create Person.");
//            }


//        // Test GymHall
//        System.out.println("\nTesting GymHall Class:");
//        GymHall gymHall = new GymHall("Cardio Room3", 30);
//        GymHallDAO gymHallDao = new GymHallDAO();
//        gymHallDao.addGymHall(gymHall);


//        // Test Equipment
//        System.out.println("\nTesting Equipment Class:");
//        Equipment equipment = new Equipment(2, "Bar2", "Strength", "New");
//        EquipmentDAO equipmentDao = new EquipmentDAO();
//        int thisID = equipmentDao.addEquipment(equipment);
//        equipmentDao.updateCondition(thisID , "Used");
////        System.out.println("Equipment Condition: " + equipment.getCondition());
//        System.out.println("Equipment ID: " + thisID);

        
//        
//        // Test ExercisePlan
  // Create a new exercise plan
//        ExercisePlanDAO exercisePlandao = new ExercisePlanDAO();
//        ExercisePlan exercisePlan = new ExercisePlan("Full Body Workout", "Strength");
//        
//        exercisePlandao.addExercisePlan(exercisePlan);
//        // Add the exercise plan to the database
//        exercisePlan.addExercisePlan();
//        System.out.println("Added Exercise Plan: " + exercisePlan);
//
//        // Add exercises to the plan
//        Exercise exercise1 = new Exercise("Push Up", 3, 15);
//        Exercise exercise2 = new Exercise("Squat", 3, 20);
//        exercisePlan.addExercise(exercise1);
//        exercisePlan.addExercise(exercise2);
//        System.out.println("Exercises added to the plan.");
//        
//        System.out.println("Retrieve All Exercises for this exercisePlan");
//        System.out.println(exercisePlan.getExercises());
        


        // Retrieve the exercise plan by ID
//        ExercisePlan retrievedPlan = ExercisePlan.getExercisePlan(exercisePlan.getId());
//        System.out.println("Retrieved Exercise Plan: " + retrievedPlan);
//
//        // Retrieve all exercise plans
//        List<ExercisePlan> allPlans = ExercisePlan.getAllExercisePlans();
//        System.out.println("All Exercise Plans:");
//        for (ExercisePlan plan : allPlans) {
//            System.out.println(plan);
//        }
//
//        // Update an exercise in the plan
//        exercisePlan.updateExercise(exercise1.getId(), "Push Up", 4, 20);
//        System.out.println("Updated Exercise: " + exercise1);
//
//        // Remove an exercise from the plan
//        exercisePlan.removeExercise(exercise2.getId());
//        System.out.println("Removed Exercise: " + exercise2);
//
//        // Delete the exercise plan from the database
//        exercisePlan.deleteExercisePlan();
//        System.out.println("Deleted Exercise Plan: " + exercisePlan);
//
//        // Test Exercise
//        System.out.println("\nTesting Exercise Class:");
//        Exercise exercise = new Exercise( "Squat", 4, 12);
//        exercise.increaseReps(2);
//        System.out.println("Exercise Reps after increase: " + exercise.getReps());
//



//        // Test Schedule
        //System.out.println("\nTesting Schedule Class:");
             // Example: Creating a ScheduleDTO
        // Example: Creating a ScheduleDTO for Monday with ExerciseID 1
// Create a ScheduleDAO instance for database operations
//        ScheduleDAO scheduleDAO = new ScheduleDAO();
//
//        // Create a Schedule object for a specific day (e.g., Monday)
//        Schedule FridaySchedule = new Schedule("Friday");
//        int addedSchedule = scheduleDAO.insertSchedule(FridaySchedule);
//        
//        
//        
//        // 1. Add exercises to the Friday schedule
//        System.out.println("Adding exercises to Friday  schedule...");
//        boolean added = FridaySchedule.addExerciseToDay( addedSchedule ,3);  // Assuming Exercise ID 1
//        if (added) {
//            System.out.println("Exercise 3 added to Friday's schedule.");
//        } else {
//            System.out.println("Failed to add Exercise 3 to Friday's schedule.");
//        }
//
//        added = FridaySchedule.addExerciseToDay( addedSchedule, 2);  // Assuming Exercise ID 2
//        if (added) {
//            System.out.println("Exercise 2 added to Friday's schedule.");
//        } else {
//            System.out.println("Failed to add Exercise 2 to Friday's schedule.");
//        }
//
//        // 2. View all exercises for the Monday schedule
//        System.out.println("\nViewing all exercises for Friday...");
//        List<Integer> exercises = FridaySchedule.viewExercisesForDay();
//        System.out.println("Exercises for Friday:");
//        for (int exerciseId : exercises) {
//            System.out.println("Exercise ID: " + exerciseId);
//        }
////
////        // 3. Remove an exercise from Monday schedule
//        System.out.println("\nRemoving Exercise 1 from Friday's schedule...");
//        boolean removed = FridaySchedule.removeExerciseFromDay(1);
//        if (removed) {
//            System.out.println("Exercise 1 removed from Friday's schedule.");
//        } else {
//            System.out.println("Failed to remove Exercise 1 from Friday's schedule.");
//        }
////
////        // 4. View all exercises for the Monday schedule after removal
//        System.out.println("\nViewing all exercises for Friday after removal...");
//        exercises = FridaySchedule.viewExercisesForDay();
//        System.out.println("Exercises for Friday after removal:");
//        for (int exerciseId : exercises) {
//            System.out.println("Exercise ID: " + exerciseId);
//        }
////
////        // 5. Clear all exercises from the Monday schedule
//        System.out.println("\nClearing all exercises from Friday's schedule...");
//        boolean cleared = FridaySchedule.clearDay();
//        if (cleared) {
//            System.out.println("All exercises cleared from Friday's schedule.");
//        } else {
//            System.out.println("Failed to clear all exercises from Friday's schedule.");
//        }
////
////        // 6. View all exercises for the Monday schedule after clearing
//        System.out.println("\nViewing all exercises for Friday after clearing...");
//        exercises = FridaySchedule.viewExercisesForDay();
//        if (exercises.isEmpty()) {
//            System.out.println("No exercises found for Friday.");
//        } else {
//            System.out.println("Exercises for Friday:");
//            for (int exerciseId : exercises) {
//                System.out.println("Exercise ID: " + exerciseId);
//            }
//        }
//



//        // Test Payment
//        System.out.println("\nTesting Payment Class:");
        // Create a new payment
        
//        PaymentDAO paymentdao = new PaymentDAO();
//        Payment payment = new Payment(2, 100.0, LocalDate.now(), LocalDate.now().plusDays(30), 10.0);
//        paymentdao.savePayment(payment);
//        // Save the payment
//        System.out.println("Saved Payment: " + payment);
//
//        //Update the payment
//        payment.setAmount(120.0);
//        payment.update();
//        System.out.println("Updated Payment: " + payment);
//
//        // Get payment by ID
//        Payment retrievedPayment = Payment.getById(payment.getId());
//        System.out.println("Retrieved Payment: " + retrievedPayment);
//
//        // Get all payments
//        List<Payment> payments = Payment.getAll();
//        System.out.println("All Payments: " + payments);
//
//        // Calculate discount
//        double discount = payment.calculateDiscount();
//        System.out.println("Discount: " + discount);
//
//        // Calculate days remaining
//        int daysRemaining = payment.calculateDaysRemaining();
//        System.out.println("Days Remaining: " + daysRemaining);
//
//        // Delete the payment
//        payment.delete();
//        System.out.println("Deleted Payment: " + payment);



// -------------------------------------------------------------------------------------------------------------
        
        
        // Start Last Test Member class
        // Step 1: Create a Member object
//        Member member = new Member("mohamed", "Ashraf", 22, "015020101", "elmaerg", "Male", 3, 40.0, 2, 2);
//
//        // Step 2: Create the Person record and get the ID
//        PersonDAO personDAO = new PersonDAO();
//        int personId = personDAO.addPerson(member);
//
//        if (personId != -1) {
//            // Step 3: Use the Person ID to create the Member record
//            MemberDAO memberDAO = new MemberDAO();
//            memberDAO.addMember(member, personId);
//
//            // Step 4: Retrieve the Member from the database and verify
//            Member retrievedMember = memberDAO.getMember(personId);
//            if (retrievedMember != null) {
//                System.out.println("Retrieved Member: " + retrievedMember +retrievedMember.getId() );
//
//                // Step 5: Update Member's details
//                retrievedMember.setWeight(42.5);
//                retrievedMember.setAssignedTrainer(11);
//                retrievedMember.setExercisePlan(2);
//                retrievedMember.setSchedule(3);
//
//                // Step 6: Verify updates in the database
//                Member updatedMember = memberDAO.getMember(personId);
//                System.out.println("Updated Member: " + updatedMember);
//
//                // Check the updates
//                if (updatedMember.getWeight() == 42.5 &&
//                    updatedMember.getAssignedTrainer() == 11 &&
//                    updatedMember.getExercisePlan() == 2 &&
//                    updatedMember.getSchedule() == 3) {
//                    System.out.println("Updates verified successfully.");
//                } else {
//                    System.out.println("Updates verification failed.");
//                }
//
//                // Step 7: Delete the Member
//                memberDAO.deleteMember(personId);
//                Member deletedMember = memberDAO.getMember(personId);
//                if (deletedMember == null) {
//                    System.out.println("Member deleted successfully.");
//                } else {
//                    System.out.println("Failed to delete Member.");
//                }
//            } else {
//                System.out.println("Failed to retrieve Member.");
//            }
//        } else {
//            System.out.println("Failed to create Person.");
//        }




        // End Last Test Member class         
        // Start Last Test Trainer class 
        
        
        
        
        
        // Step 1: Create a Trainer object
        //public Trainer(String fName, String lName, int age, String phone, String address, String gender, String specialist)
//        Trainer trainer = new Trainer("Mohamed", "Hassan", 35, "0123456789", "Cairo", "Male", "Fitness");
//
//       // Step 2: Add the Trainer to the database
//        PersonDAO personDAO = new PersonDAO();
//        int trainerId = personDAO.addPerson(trainer);
//
//        if (trainerId != -1) {
//            TrainerDAO trainerDAO = new TrainerDAO();
//            trainer.setId(trainerId); // Set the ID retrieved from Person table
//            trainerDAO.addTrainer(trainer, trainerId);
////
//            // Step 3: Retrieve the Trainer by ID
//            Trainer retrievedTrainer = trainerDAO.getTrainer(trainerId);
//            System.out.println(retrievedTrainer);
//            if (retrievedTrainer != null) {
//                System.out.println("Retrieved Trainer: " + retrievedTrainer +" With ID : " +retrievedTrainer.getId() );
//                
//
////                // Step 4: Add Customers (Members) to the Trainer
//                Member member1 = new Member("Ahmed", "Ali", 25, "01122334455", "Giza", "Male", retrievedTrainer.getId(), 75.0, 2, 1);
//               int personId = personDAO.addPerson(member1);
//               
//               MemberDAO memberDAO = new MemberDAO();
//               memberDAO.addMember(member1, personId);
//               Member retrievedMember = memberDAO.getMember(personId);
//
//                trainer.addCustomer(retrievedMember.getId(), trainerId);
////                    trainer.addCustomer(MemberId, trainerID);
//
//                    // Step 5: Verify that customers were added
//                    trainer.loadCustomers(); // Reload customers from the database
//                    List<Member> customers = trainer.getCustomers();
//                    System.out.println("Trainer's Customers: ");
//                    for (Member customer : customers) {
//                        System.out.println(customer);
//                    }
//
//                    // Step 6: Update Trainer's specialization
//                    trainer.setSpecialist("Bodybuilding");
//                    System.out.println("Updated Trainer: " + trainer);
//
//                    // Step 7: Remove a customer from the Trainer
//                    trainer.removeCustomer(retrievedTrainer.getId());
//
//                    // Verify removal
//                    trainer.loadCustomers();
//                    System.out.println("Customers after removal:");
//                    for (Member customer : trainer.getCustomers()) {
//                        System.out.println(customer);
//                    }
////
//                    // Step 8: Delete the Trainer and clean up
//                    trainerDAO.deleteTrainer(retrievedTrainer.getId());
//
//                    // Check if trainer still exists
//                    Trainer deletedTrainer = trainerDAO.getTrainer(retrievedTrainer.getId());
//                    if (deletedTrainer == null) {
//                        System.out.println("Trainer deleted successfully.");
//                    } else {
//                        System.out.println("Failed to delete trainer.");
//                    }
//
//            } else {
//                System.out.println("Failed to retrieve trainer.");
//            }
//        } else {
//            System.out.println("Failed to add trainer.");
//        }
//        



        // End Last Test Trainer class
        // Start Last Test GymHall class
        
        
        
        
        
//         GymHallDAO gymHallDAO = new GymHallDAO();
//
//        // Test adding a new gym hall
//        System.out.println("Adding a new gym hall...");
//        GymHall newGymHall = new GymHall("Fitness Center6", 50);
//        gymHallDAO.addGymHall(newGymHall);
//
//        // Test retrieving a gym hall by ID
//        System.out.println("Retrieving gym hall with ID 1...");
//        GymHall gymHall = gymHallDAO.getGymHall(11);
//        if (gymHall != null) {
//            System.out.println("GymHall found: " + gymHall);
//        } else {
//            System.out.println("GymHall not found.");
//        }
//
//        // Test updating gym hall capacity
//        System.out.println("Updating gym hall capacity...");
//        gymHallDAO.updateGymHallCapacity(11, 60);
//
//        // Test adding equipment to gym hall
//        System.out.println("Adding equipment to gym hall...");
//        Equipment equipment = new Equipment(11, "Treadmill", "Cardio", "Good");
//        gymHallDAO.addEquipmentToGymHall(11, equipment);
//
//        // Test finding equipment by name
//        System.out.println("Finding equipment by name 'Treadmill'...");
//        Equipment foundEquipment = gymHallDAO.findEquipmentInGymHallByName(11, "Treadmill");
//        if (foundEquipment != null) {
//            System.out.println("Equipment found: " + foundEquipment);
//        } else {
//            System.out.println("Equipment not found.");
//        }
//
//        // Test getting total number of equipment in gym hall
//        System.out.println("Getting total number of equipment in gym hall...");
//        int totalEquipment = gymHallDAO.getTotalEquipmentInGymHall(11);
//        System.out.println("Total equipment in gym hall: " + totalEquipment);
//
//        // Test removing equipment from gym hall
////        System.out.println("Removing equipment with ID 1 from gym hall...");
////        gymHallDAO.removeEquipmentFromGymHall(1, 1);
//
//        // Test getting all gym halls
//        System.out.println("Retrieving all gym halls...");
//        List<GymHall> gymHalls = gymHallDAO.getAllGymHalls();
//        if (!gymHalls.isEmpty()) {
//            System.out.println("All gym halls:");
//            for (GymHall g : gymHalls) {
//                System.out.println(g);
//            }
//        } else {
//            System.out.println("No gym halls found.");
//        }

        // Test deleting a gym hall
//        System.out.println("Deleting gym hall with ID 1...");
//        gymHallDAO.deleteGymHall(1);



        // End Last Test GymHall class
        // Start Last Test Equipment class
        
        
        
//        EquipmentDAO equipmentDAO = new EquipmentDAO();
//
//        // 1. Add new equipment
//        System.out.println("Adding new equipment...");
//        Equipment treadmill = new Equipment(2, "Treadmill2", "Cardio", "Good");
//        int treadmillId = equipmentDAO.addEquipment(treadmill);
//        System.out.println("Treadmill added with ID: " + treadmillId);
//
//        Equipment benchPress = new Equipment(2, "Bench Press2", "Strength", "Needs Maintenance");
//        int benchPressId = equipmentDAO.addEquipment(benchPress);
//        System.out.println("Bench Press added with ID: " + benchPressId);
//
//        // 2. Retrieve all equipment
//        System.out.println("\nRetrieving all equipment...");
//        List<Equipment> allEquipment = equipmentDAO.getAllEquipment();
//        for (Equipment equipment : allEquipment) {
//            System.out.println(equipment);
//        }
//
//        // 3. Update equipment condition
//        System.out.println("\nUpdating equipment condition...");
//        equipmentDAO.updateCondition(treadmillId, "Scheduled for Maintenance");
//        Equipment updatedTreadmill = equipmentDAO.findEquipmentById(treadmillId);
//        System.out.println("Updated equipment: " + updatedTreadmill);
//
//        // 4. Find equipment by ID
//        System.out.println("\nFinding equipment by ID...");
//        Equipment foundEquipment = equipmentDAO.findEquipmentById(benchPressId);
//        System.out.println("Found equipment: " + foundEquipment);
//
//        // 5. Delete equipment
//        System.out.println("\nDeleting equipment...");
//        equipmentDAO.removeEquipment(benchPressId);
//        System.out.println("Bench Press removed.");
//
//        // 6. Verify deletion
//        System.out.println("\nVerifying deletion...");
//        allEquipment = equipmentDAO.getAllEquipment();
//        for (Equipment equipment : allEquipment) {
//            System.out.println(equipment);
//        }
        
        
        // End Last Test Equipment class  
        // Start Last Test Payment Class
        
//        PaymentDAO paymentDAO = new PaymentDAO();
//
//        // 1. Add a new payment
//        System.out.println("Adding a new payment...");
//        Payment newPayment = new Payment(
//                1, // Member ID
//                100.0, // Amount
//                LocalDate.now(), // Payment Date
//                LocalDate.now().plusDays(30), // Due Date
//                10.0 // Discount Rate
//        );
//        newPayment.save(); // Save to database
//        System.out.println("Saved payment: " + newPayment);
//
//        // 2. Retrieve a payment by ID
//        System.out.println("\nRetrieving payment by ID...");
//        Payment retrievedPayment = paymentDAO.getPaymentById(newPayment.getId());
//        if (retrievedPayment != null) {
//            System.out.println("Retrieved payment: " + retrievedPayment);
//        } else {
//            System.out.println("Payment not found.");
//        }
//
//        // 3. Update an existing payment
//        System.out.println("\nUpdating the payment...");
//        if (retrievedPayment != null) {
//            retrievedPayment.setAmount(120.0); // Update amount
//            retrievedPayment.setStatus("Completed"); // Update status
//            retrievedPayment.update(); // Save updates
//            System.out.println("Updated payment: " + retrievedPayment);
//        }
//
//        // 4. Retrieve all payments
//        System.out.println("\nRetrieving all payments...");
//        for (Payment payment : paymentDAO.getAllPayments()) {
//            System.out.println(payment);
//        }
//
//        // 5. Delete a payment
//        System.out.println("\nDeleting the payment...");
//        if (retrievedPayment != null) {
//            retrievedPayment.delete();
//            System.out.println("Payment deleted: " + retrievedPayment.getId());
//        }
//
//        // Final Check: Retrieve all payments to ensure deletion
//        System.out.println("\nFinal list of all payments:");
//        for (Payment payment : paymentDAO.getAllPayments()) {
//            System.out.println(payment);
//        }
        



        // End Last Test Payment Class
        // Start Last Exercise Plan test class
        
        
        
        
//        // Create an ExercisePlan instance
//        ExercisePlan exercisePlan = new ExercisePlan("Strength Training Plan", "Strength");
//
//        // Add ExercisePlan to the database
//        System.out.println("Adding Exercise Plan...");
//        exercisePlan.addExercisePlan();
//
//        // Add Exercises to the ExercisePlan
//        Exercise exercise1 = new Exercise("Push Ups", 3, 15);
//        Exercise exercise2 = new Exercise("Squats", 3, 20);
//
//        exercisePlan.addExercise(exercise1);
//        exercisePlan.addExercise(exercise2);
//
//        // Retrieve ExercisePlan by ID
//        System.out.println("\nRetrieving Exercise Plan by ID...");
//        ExercisePlan retrievedPlan = ExercisePlan.getExercisePlan(exercisePlan.getId());
//        if (retrievedPlan != null) {
//            System.out.println(retrievedPlan);
//        } else {
//            System.out.println("Exercise Plan not found.");
//        }
//
//        // Update ExercisePlan
//        System.out.println("\nUpdating Exercise Plan...");
//        exercisePlan.setPlanName("Updated Plan Name");
//        exercisePlan.setType("Updated Type");
//        exercisePlan.updateExercisePlan();
//
//        // Retrieve all ExercisePlans
//        System.out.println("\nRetrieving all Exercise Plans...");
//        for (ExercisePlan plan : ExercisePlan.getAllExercisePlans()) {
//            System.out.println(plan);
//        }
//
//        // Remove an exercise
//        System.out.println("\nRemoving an exercise...");
//        exercisePlan.removeExercise(exercise1.getId());
//
//        // Delete ExercisePlan
//        System.out.println("\nDeleting Exercise Plan...");
//        exercisePlan.deleteExercisePlan();
        



        // End Last Exercise Plan test class
        // Start Last Exercise test class



        
         // Initialize DAO
//        ExerciseDAO exerciseDAO = new ExerciseDAO();
//
//        // 1. Add Exercises
//        System.out.println("=== Adding Exercises ===");
//        Exercise exercise1 = new Exercise("Push-ups", 3, 15);
//        exerciseDAO.addExercise(exercise1);
//
//        Exercise exercise2 = new Exercise("Squats", 4, 12);
//        exerciseDAO.addExercise(exercise2);
//
//        Exercise exercise3 = new Exercise("Plank", 2, 1); // Reps as time in minutes
//        exerciseDAO.addExercise(exercise3);
//
//        // 2. Retrieve All Exercises
//        System.out.println("\n=== Retrieving All Exercises ===");
//        exerciseDAO.getAllExercises().forEach(System.out::println);
//
//        // 3. Fetch Exercise by ID
//        System.out.println("\n=== Fetching Exercise by ID ===");
//        Exercise fetchedExercise = exerciseDAO.getExercise(21);
//        if (fetchedExercise != null) {
//            System.out.println("Fetched Exercise: " + fetchedExercise);
//        } else {
//            System.out.println("Exercise not found.");
//        }
//
//        // 4. Update Exercise Details
//        System.out.println("\n=== Updating Exercise ===");
//        fetchedExercise.setName("Modified Push-ups");
//        fetchedExercise.setReps(20);
//        exerciseDAO.updateExercise(fetchedExercise);
//        System.out.println("Updated Exercise: " + exerciseDAO.getExercise(fetchedExercise.getId()));
//
//        // 5. Delete an Exercise
//        System.out.println("\n=== Deleting Exercise ===");
//        exerciseDAO.deleteExercise(30); // Deleting Squats
//        System.out.println("Remaining Exercises After Deletion:");
//        exerciseDAO.getAllExercises().forEach(System.out::println);
//        


        // End Last Exercise test class
        // Start Last Schedule test class


        
         // Initialize DAO
//        ScheduleDAO scheduleDAO = new ScheduleDAO();
//
//        // 1. Create a new Schedule
//        System.out.println("=== Creating Schedule ===");
//        Schedule schedule = new Schedule("Monday");  // Create schedule for Monday
//        int scheduleId = scheduleDAO.insertSchedule(schedule);  // Insert schedule into the database
//        System.out.println("Schedule created with ID: " + scheduleId);
//
//        // 2. Add Exercises to Schedule
//        System.out.println("\n=== Adding Exercises to Schedule ===");
//        // Assuming Exercise IDs 1, 2, and 3 are already created and exist in the database
//        schedule.addExerciseToDay(scheduleId, 1);  // Adding exercise with ID 1
//        schedule.addExerciseToDay(scheduleId, 2);  // Adding exercise with ID 2
//        schedule.addExerciseToDay(scheduleId, 3);  // Adding exercise with ID 3
//
//        // 3. View Exercises for the Schedule
//        System.out.println("\n=== Viewing Exercises for " + schedule.getDay() + " ===");
//        List<Integer> exerciseIds = schedule.viewExercisesForDay();
//        System.out.println("Exercises for " + schedule.getDay() + ": " + exerciseIds);
//
//        // 4. Remove an Exercise from the Schedule
//        System.out.println("\n=== Removing Exercise from Schedule ===");
//        boolean removed = schedule.removeExerciseFromDay(2);  // Removing exercise with ID 2
//        System.out.println("Exercise removed: " + removed);
//
//        // 5. View Remaining Exercises after Removal
//        System.out.println("\n=== Viewing Remaining Exercises for " + schedule.getDay() + " ===");
//        exerciseIds = schedule.viewExercisesForDay();
//        System.out.println("Remaining Exercises for " + schedule.getDay() + ": " + exerciseIds);
//
//        // 6. Clear All Exercises for the Schedule
//        System.out.println("\n=== Clearing All Exercises for the Schedule ===");
//        boolean cleared = schedule.clearDay();
//        System.out.println("All exercises cleared: " + cleared);
//
//        // 7. View Exercises After Clearing
//        System.out.println("\n=== Viewing Exercises After Clearing ===");
//        exerciseIds = schedule.viewExercisesForDay();
//        System.out.println("Exercises after clearing: " + exerciseIds);

        
        // End last Schedule test class
        
        
        
        // Start Test GUI 
        
        // Show Trainer Management GUI
//        TrainerGUI.showTrainerManagementGUI();

        // Show Member Management GUI
//        MemberGUI.showMemberManagementGUI();

 
        JFrame frame = new JFrame("Gym Management System");
        
        ImageIcon icon = new ImageIcon("resources/logo3.jpg"); 
        
        
        Image resizedImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        frame.setIconImage(resizedIcon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); 

        MainGUI mainGUI = new MainGUI();

        frame.add(mainGUI);

        frame.pack();
        frame.setVisible(true);
        
//         System.out.println("To Know the available font family names");
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//        System.out.println("Getting the font family names");
//
//        // Array of all the fonts available in AWT
//        String fonts[] = ge.getAvailableFontFamilyNames();
//
//        // Getting the font family names
//        for (String i : fonts) {
//            System.out.println(i + " ");
//        }
        
        //End Test GUI

    }
}
