package GUI;

import Entity.Exercise;
import DAO.ExerciseDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ExerciseGUI extends JPanel {
    public ExerciseGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        //colors and fonts
        Color primaryColor = new Color(255, 94, 58);
        Color secondaryColor = new Color(58, 175, 255);
        Color backgroundColor = new Color(235, 245, 251);
        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);

        //  navigation bar
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel manageExercisePanel = new JPanel(new BorderLayout());
        manageExercisePanel.setBackground(backgroundColor);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 40, 250, 40));
        inputPanel.setBackground(backgroundColor);

        JLabel lblName = new JLabel("Exercise Name:");
        lblName.setFont(labelFont);
        lblName.setForeground(primaryColor);
        JTextField txtName = new JTextField();
        txtName.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblSets = new JLabel("Sets:");
        lblSets.setFont(labelFont);
        lblSets.setForeground(primaryColor);
        JTextField txtSets = new JTextField();
        txtSets.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblReps = new JLabel("Reps:");
        lblReps.setFont(labelFont);
        lblReps.setForeground(primaryColor);
        JTextField txtReps = new JTextField();
        txtReps.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblEquipment = new JLabel("Equipment:");
        lblEquipment.setFont(labelFont);
        lblEquipment.setForeground(primaryColor);
        JTextField txtEquipment = new JTextField();
        txtEquipment.setFont(new Font("Verdana", Font.PLAIN, 14));

        inputPanel.add(lblName);
        inputPanel.add(txtName);
        inputPanel.add(lblSets);
        inputPanel.add(txtSets);
        inputPanel.add(lblReps);
        inputPanel.add(txtReps);
        inputPanel.add(lblEquipment);
        inputPanel.add(txtEquipment);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor);

        JButton btnAddExercise = createButton("Add Exercise", primaryColor, buttonTextColor, buttonFont);
        JButton btnUpdateExercise = createButton("Update Exercise", primaryColor, buttonTextColor, buttonFont);
        JButton btnDeleteExercise = createButton("Delete Exercise", primaryColor, buttonTextColor, buttonFont);
        JButton btnViewExercises = createButton("View All Exercises", primaryColor, buttonTextColor, buttonFont);

        buttonPanel.add(btnAddExercise);
        buttonPanel.add(btnUpdateExercise);
        buttonPanel.add(btnDeleteExercise);
        buttonPanel.add(btnViewExercises);

        manageExercisePanel.add(inputPanel, BorderLayout.CENTER);
        manageExercisePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Panels to Tabs
        tabbedPane.addTab("Manage Exercises", manageExercisePanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Button Actions
        btnAddExercise.addActionListener(e -> {
            try {
                String name = txtName.getText();
                int sets = Integer.parseInt(txtSets.getText());
                int reps = Integer.parseInt(txtReps.getText());
                Exercise exercise = new Exercise(name, sets, reps);

                String equipment = txtEquipment.getText();
                if (!equipment.isEmpty()) {
                    for (String eq : equipment.split(",")) {
                        exercise.addEquipment(eq.trim());
                    }
                }

                exercise.addExercise();
                JOptionPane.showMessageDialog(this, "Exercise added successfully!");
                clearInputFields(inputPanel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdateExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise ID:"));
                ExerciseDAO exerciseDAO = new ExerciseDAO();
                Exercise exercise = exerciseDAO.getExercise(exerciseId);

                if (exercise != null) {
                    String newName = JOptionPane.showInputDialog(this, "Enter new name:", exercise.getName());
                    int newSets = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new sets:", exercise.getSets()));
                    int newReps = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new reps:", exercise.getReps()));

                    exercise.setName(newName);
                    exercise.setSets(newSets);
                    exercise.setReps(newReps);
                    exercise.updateExercise();

                    JOptionPane.showMessageDialog(this, "Exercise updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Exercise not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDeleteExercise.addActionListener(e -> {
            try {
                int exerciseId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Exercise ID to delete:"));
                ExerciseDAO exerciseDAO = new ExerciseDAO();
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Exercise ID: " + exerciseId + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    exerciseDAO.deleteExercise(exerciseId);
                    JOptionPane.showMessageDialog(this, "Exercise deleted successfully!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnViewExercises.addActionListener(e -> {
            try {
                ExerciseDAO exerciseDAO = new ExerciseDAO();
                List<Exercise> exercises = exerciseDAO.getAllExercises();

                StringBuilder exerciseList = new StringBuilder("All Exercises:\n");
                for (Exercise exercise : exercises) {
                    exerciseList.append(exercise).append("\n");
                }

                JOptionPane.showMessageDialog(this, exerciseList.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JButton createButton(String text, Color bgColor, Color textColor, Font font) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(font);
        button.setFocusPainted(false);
        return button;
    }

    private void clearInputFields(JPanel inputPanel) {
        for (Component component : inputPanel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}