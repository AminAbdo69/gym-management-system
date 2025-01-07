
package GUI;

import Entity.Schedule;
import DAO.ScheduleDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ScheduleGUI extends JPanel {

    public ScheduleGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
                Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
//        Color backgroundColor = new Color(0, 20, 64);
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);

        // Tabbed Pane for managing schedules
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(backgroundColor);

        // Main Panel for managing schedules
        JPanel manageSchedulePanel = new JPanel(new BorderLayout());
        manageSchedulePanel.setBackground(backgroundColor);


        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblDay = new JLabel("Day of the Week:");
        lblDay.setForeground(primaryColor);
        JTextField txtDay = new JTextField();
        //JLabel lblExerciseId = new JLabel("Exercise ID:");
        //JTextField txtExerciseId = new JTextField();

        inputPanel.add(lblDay);
        inputPanel.add(txtDay);
        //inputPanel.add(lblExerciseId);
        //inputPanel.add(txtExerciseId);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        //JButton btnAddExercise = new JButton("Add Exercise");
        JButton btnAddExerciseTOschedule = new JButton("Add Exercise to Schedule");
        btnAddExerciseTOschedule.setBackground(primaryColor);
        btnAddExerciseTOschedule.setForeground(Color.WHITE);
        
        JButton btnViewExercises = new JButton("View Exercises");
        btnViewExercises.setBackground(primaryColor);
        btnViewExercises.setForeground(Color.WHITE);
        
        JButton btnRemoveExercise = new JButton("Remove Exercise");
        btnRemoveExercise.setBackground(primaryColor);
        btnRemoveExercise.setForeground(Color.WHITE);
        
        JButton btnClearDay = new JButton("Clear All Exercises");
        btnClearDay.setBackground(primaryColor);
        btnClearDay.setForeground(Color.WHITE);

        //buttonPanel.add(btnAddExercise);
        buttonPanel.add(btnAddExerciseTOschedule);

        buttonPanel.add(btnViewExercises);
        buttonPanel.add(btnRemoveExercise);
        buttonPanel.add(btnClearDay);

        manageSchedulePanel.add(inputPanel, BorderLayout.CENTER);
        manageSchedulePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Panels to Tabs
        tabbedPane.addTab("Manage Schedule", manageSchedulePanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Button Actions
        /*btnAddExercise.addActionListener(e -> {
            try {
                String day = txtDay.getText();
                int exerciseId = Integer.parseInt(txtExerciseId.getText());

                ScheduleDAO scheduleDAO = new ScheduleDAO();
                Schedule schedule = new Schedule(day);
                if (schedule.addExerciseToDay(schedule.getId(), exerciseId)) {
                    JOptionPane.showMessageDialog(this, "Exercise added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add exercise.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });*/
        btnAddExerciseTOschedule.addActionListener(e -> {
            
            try{
                int scheduleID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Day ID:"));
                int ExerciseID = Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Exercise ID:"));
                
                ScheduleDAO schduledao = new ScheduleDAO();
                
                Schedule schedule = schduledao.getScheduleById(scheduleID);

                if (schedule != null) {
                    // Assuming Exercise constructor takes ID as input
                    schduledao.addExerciseToDay(scheduleID ,ExerciseID );  // Adds the exercise to the plan

                    // Optionally, update the exercise list on the GUI (display real-time changes)
                    JOptionPane.showMessageDialog(this, "Exercise added to the schedule.");
                } else {
                    JOptionPane.showMessageDialog(this, "schedule Plan not found.");
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        // 1- DAO
        // 2- Entity
        // 3- GUI
        btnViewExercises.addActionListener(e -> {
            try {
                String day = txtDay.getText();
                ScheduleDAO scheduleDAO = new ScheduleDAO();
                List<String> exercises = scheduleDAO.viewExercisesForDay(day);

                if (!exercises.isEmpty()) {
                    StringBuilder exerciseList = new StringBuilder("Exercises for " + day + ":\n");
                    for (String name : exercises) {
                        exerciseList.append("Exercise ID: ").append((name)).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, exerciseList.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "No exercises found for this day.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemoveExercise.addActionListener(e -> {
            try {
                int scheduleID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Day ID:"));
                int exerciseId = Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Exercise ID:"));
                
                
                ScheduleDAO scheduleDAO = new ScheduleDAO();
                Schedule schedule = scheduleDAO.getScheduleById(scheduleID);

                
                if (schedule!=null) {
                    scheduleDAO.removeExerciseFromDay(scheduleID, exerciseId);
                    JOptionPane.showMessageDialog(this, "Exercise removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove exercise.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClearDay.addActionListener(e -> {
            try {
                String day = txtDay.getText();
                ScheduleDAO scheduleDAO = new ScheduleDAO();
               
                    
                int scheduleID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Day ID:"));
                     Schedule schedule = scheduleDAO.getScheduleById(scheduleID);
                 if( schedule !=null) {
                    
                    if (scheduleDAO.clearDay(scheduleID)) {
                        JOptionPane.showMessageDialog(this, "All exercises cleared successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to clear exercises.");
                    }
                }else{
                     JOptionPane.showMessageDialog(this, "Schedule not found...");
                 } 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}