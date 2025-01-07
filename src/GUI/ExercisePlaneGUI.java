/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package GUI;

import Entity.Exercise;
import Entity.ExercisePlan;
import DAO.ExercisePlanDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

class ExercisePlanGUI extends JPanel {

    public ExercisePlanGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Tabbed Pane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();

        // Main Panel for managing exercise plans
        JPanel manageExercisePlanPanel = new JPanel(new BorderLayout());

        // Input Panel for creating or updating Exercise Plan
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblPlanName = new JLabel("Plan Name:");
        JTextField txtPlanName = new JTextField();
        JLabel lblType = new JLabel("Type:");
        JTextField txtType = new JTextField();
        JLabel lblExerciseId = new JLabel("Exercise ID (for adding):");
        JTextField txtExerciseId = new JTextField();

        inputPanel.add(lblPlanName);
        inputPanel.add(txtPlanName);
        inputPanel.add(lblType);
        inputPanel.add(txtType);
        inputPanel.add(lblExerciseId);
        inputPanel.add(txtExerciseId);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        ImageIcon addIcon = new ImageIcon("resources/add.png");
        ImageIcon updateIcon = new ImageIcon("resources/update.png");
        ImageIcon removeIcon = new ImageIcon("resources/delete1.png");
        ImageIcon deleteIcon = new ImageIcon("resources/delete2.png");
        ImageIcon viewIcon = new ImageIcon("resources/view.png");

        int iconWidth = 50;
        int iconHeight = 50;
        addIcon = new ImageIcon(addIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        updateIcon = new ImageIcon(updateIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        removeIcon = new ImageIcon(removeIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        deleteIcon = new ImageIcon(deleteIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        viewIcon = new ImageIcon(viewIcon.getImage().getScaledInstance(80, 30, Image.SCALE_SMOOTH));

        JButton btnAddExercisePlan = new JButton(addIcon);
        btnAddExercisePlan.setToolTipText("Add a new Exercise Plan");

        JButton btnAddExercise = new JButton(updateIcon);
        btnAddExercise.setToolTipText("Add an exercise to the plan");

        JButton btnRemoveExercise = new JButton(removeIcon);
        btnRemoveExercise.setToolTipText("Remove an exercise from the plan");

        JButton btnDeleteExercisePlan = new JButton(deleteIcon);
        btnDeleteExercisePlan.setToolTipText("Delete an Exercise Plan");

        JButton btnViewExercises = new JButton(viewIcon);
        btnViewExercises.setToolTipText("View all exercises in the plan");

        Dimension buttonSize = new Dimension(70, 70);
        btnAddExercisePlan.setPreferredSize(buttonSize);
        btnAddExercise.setPreferredSize(buttonSize);
        btnRemoveExercise.setPreferredSize(buttonSize);
        btnDeleteExercisePlan.setPreferredSize(buttonSize);
        btnViewExercises.setPreferredSize(buttonSize);

        buttonPanel.add(btnAddExercisePlan);
        buttonPanel.add(btnAddExercise);
        buttonPanel.add(btnRemoveExercise);
        buttonPanel.add(btnDeleteExercisePlan);
        buttonPanel.add(btnViewExercises);

        manageExercisePlanPanel.add(inputPanel, BorderLayout.CENTER);
        manageExercisePlanPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Panels to Tabs
        tabbedPane.addTab("Manage Exercise Plans", manageExercisePlanPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Add Exercise Plan Button Action
        btnAddExercisePlan.addActionListener(e -> {
            try {
                String planName = txtPlanName.getText();
                String type = txtType.getText();
                if (planName.isEmpty() || type.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ExercisePlan exercisePlan = new ExercisePlan(planName, type);
                ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
                exercisePlanDAO.addExercisePlan(exercisePlan);
                JOptionPane.showMessageDialog(this, "Exercise Plan added successfully.");
                clearInputFields(inputPanel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add Exercise Button Action
        btnAddExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(txtExerciseId.getText());
                ExercisePlan exercisePlan = ExercisePlan.getExercisePlan(Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID:")));
                if (exercisePlan != null) {
                    Exercise exercise = new Exercise(exerciseId);  // Assuming Exercise constructor takes id as input
                    exercisePlan.addExercise(exercise);
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Remove Exercise Button Action
        btnRemoveExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(txtExerciseId.getText());
                ExercisePlan exercisePlan = ExercisePlan.getExercisePlan(Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID:")));
                if (exercisePlan != null) {
                    exercisePlan.removeExercise(exerciseId);
                    JOptionPane.showMessageDialog(this, "Exercise removed from the plan.");
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Exercise Plan Button Action
        btnDeleteExercisePlan.addActionListener(e -> {
            try {
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID to delete:"));
                ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
                exercisePlanDAO.deleteExercisePlan(planId);
                JOptionPane.showMessageDialog(this, "Exercise Plan deleted successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View Exercises Button Action
        btnViewExercises.addActionListener(e -> {
            try {
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID to view exercises:"));
                ExercisePlan exercisePlan = ExercisePlan.getExercisePlan(planId);
                if (exercisePlan != null) {
                    List<Exercise> exercises = exercisePlan.getExercises();
                    StringBuilder exerciseList = new StringBuilder("Exercises in Plan: " + exercisePlan.getPlanName() + "\n");
                    for (Exercise exercise : exercises) {
                        exerciseList.append("Exercise ID: ").append(exercise.getId()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, exerciseList.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearInputFields(JPanel inputPanel) {
        for (Component component : inputPanel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}
*/package GUI;

import Entity.Exercise;
import Entity.ExercisePlan;
import DAO.ExercisePlanDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

class ExercisePlanGUI extends JPanel {

    public ExercisePlanGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        //colors and fonts
        Color primaryColor = new Color(255, 94, 58);
        Color backgroundColor = new Color(235, 245, 251);
        Color buttonTextColor = Color.WHITE; 
        Font labelFont = new Font("Verdana", Font.BOLD, 15); 
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15); 

        // Set background color of panel
        setBackground(backgroundColor);

        // navigation bar
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel manageExercisePlanPanel = new JPanel(new BorderLayout());
        manageExercisePlanPanel.setBackground(backgroundColor);

        // Input Panel for creating or updating Exercise Plan
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 170));
        inputPanel.setBorder(new EmptyBorder(20, 50, 20, 40));
        inputPanel.setBackground(backgroundColor);

        JLabel lblPlanName = new JLabel("Plan Name:");
        lblPlanName.setFont(labelFont);
        lblPlanName.setForeground(primaryColor);
        JTextField txtPlanName = new JTextField();

        JLabel lblType = new JLabel("Type:");
        lblType.setFont(labelFont);
        lblType.setForeground(primaryColor);
        JTextField txtType = new JTextField();

        inputPanel.add(lblPlanName);
        inputPanel.add(txtPlanName);
        inputPanel.add(lblType);
        inputPanel.add(txtType);

        // Button Panl GridLayout (2 r,3 c)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(backgroundColor);

        // Buttons
        JButton btnAddExercisePlan = new JButton("Add Plan");
        btnAddExercisePlan.setFont(buttonFont);
        btnAddExercisePlan.setBackground(primaryColor);
        btnAddExercisePlan.setForeground(buttonTextColor);

        JButton btnAddExercise = new JButton("Add Exr to Plan");
        btnAddExercise.setFont(buttonFont);
        btnAddExercise.setBackground(primaryColor);
        btnAddExercise.setForeground(buttonTextColor);

        JButton btnRemoveExercise = new JButton("Delete Exr from Plan");
        btnRemoveExercise.setFont(buttonFont);
        btnRemoveExercise.setBackground(primaryColor);
        btnRemoveExercise.setForeground(buttonTextColor);

        JButton btnDeleteExercisePlan = new JButton("Remove Plan");
        btnDeleteExercisePlan.setFont(buttonFont);
        btnDeleteExercisePlan.setBackground(primaryColor);
        btnDeleteExercisePlan.setForeground(buttonTextColor);

        JButton btnViewExercises = new JButton("View Exres");
        btnViewExercises.setFont(buttonFont);
        btnViewExercises.setBackground(primaryColor);
        btnViewExercises.setForeground(buttonTextColor);

        JButton btnViewExercisePlans = new JButton("View Plans");
        btnViewExercisePlans.setFont(buttonFont);
        btnViewExercisePlans.setBackground(primaryColor);
        btnViewExercisePlans.setForeground(buttonTextColor);

        // Set button sizes
        Dimension buttonSize = new Dimension(150, 30);
        btnAddExercisePlan.setPreferredSize(buttonSize);
        btnAddExercise.setPreferredSize(buttonSize);
        btnRemoveExercise.setPreferredSize(buttonSize);
        btnDeleteExercisePlan.setPreferredSize(buttonSize);
        btnViewExercises.setPreferredSize(buttonSize);
        btnViewExercisePlans.setPreferredSize(buttonSize);

        // Add buttons to the panel 
        buttonPanel.add(btnAddExercisePlan);
        buttonPanel.add(btnAddExercise);
        buttonPanel.add(btnRemoveExercise);
        buttonPanel.add(btnDeleteExercisePlan);
        buttonPanel.add(btnViewExercises);
        buttonPanel.add(btnViewExercisePlans);

        manageExercisePlanPanel.add(inputPanel, BorderLayout.CENTER);
        manageExercisePlanPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Panels to Tabs
        tabbedPane.addTab("Manage Exercise Plans", manageExercisePlanPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Add Exer Plan Button Action
        btnAddExercisePlan.addActionListener(e -> {
            try {
                String planName = txtPlanName.getText();
                String type = txtType.getText();
                if (planName.isEmpty() || type.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ExercisePlan exercisePlan = new ExercisePlan(planName, type);
                ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
                exercisePlanDAO.addExercisePlan(exercisePlan);
                JOptionPane.showMessageDialog(this, "Exercise Plan added successfully.");
                clearInputFields(inputPanel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add Exer Button Action
        btnAddExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter exercise ID:"));
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter exercise plan ID:"));
                ExercisePlan exercisePlan = ExercisePlan.getExercisePlan(planId);

                if (exercisePlan != null) {
                    exercisePlan.addExercise(exerciseId);  // Adds the exercise to the plan
                    JOptionPane.showMessageDialog(this, "Exercise added to the plan.");
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numerical values for Exercise ID and Plan ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Remove Exer Button Action
        btnRemoveExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise ID:"));
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Plan ID:"));
                ExercisePlan exercisePlan = ExercisePlan.getExercisePlan(planId);

                if (exercisePlan != null) {
                    exercisePlan.removeExercise(exerciseId);
                    JOptionPane.showMessageDialog(this, "Exercise removed from the plan.");
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Exercise Plan Button Action
        btnDeleteExercisePlan.addActionListener(e -> {
            try {
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID to delete:"));
                ExercisePlanDAO exercisePlanDAO = new ExercisePlanDAO();
                exercisePlanDAO.deleteExercisePlan(planId);
                JOptionPane.showMessageDialog(this, "Exercise Plan deleted successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View Exer Button Action
        btnViewExercises.addActionListener(e -> {
            try {
                int planId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise Plan ID to view exercises:"));
                  
                 ExercisePlanDAO exerciseplan = new ExercisePlanDAO();
                 ExercisePlan exercisePlan2 = exerciseplan.getExercisePlan(planId);
                 
                if (exercisePlan2 != null) {
                    List<Exercise> exercises = exerciseplan.getAllExercisesInPlans(planId);
                    StringBuilder exerciseList = new StringBuilder("Exercises in Plan: " + exercisePlan2.getPlanName() + "\n");
                    for (Exercise exercise : exercises) {
                        exerciseList.append("Exercise : ").append(exercise).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, exerciseList.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise Plan not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View Exer Plans Button Action
        btnViewExercisePlans.addActionListener(e -> {
            ExercisePlanDAO dao = new ExercisePlanDAO();
            List<ExercisePlan> plans = dao.getAllExercisePlans();

            StringBuilder exercisePlansList = new StringBuilder("Exercise Plans: \n");
            for (ExercisePlan plan : plans) {
                exercisePlansList.append("Exercise Plan : ").append(plan).append("\n");
            }
            JOptionPane.showMessageDialog(this, exercisePlansList.toString());
        });
    }

    private void clearInputFields(JPanel inputPanel) {
        for (Component component : inputPanel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}

