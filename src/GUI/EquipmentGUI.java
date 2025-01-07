package GUI;

import Entity.Equipment;
import DAO.EquipmentDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class EquipmentGUI extends JPanel {

    public EquipmentGUI() {
        // Colors and Fonts
        Color primaryColor = new Color(255, 94, 58); // Bright Coral
        Color secondaryColor = new Color(58, 175, 255); // Bright Blue
        Color backgroundColor = new Color(235, 245, 251); // Light Gray
        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 20);
        Font buttonFont = new Font("Verdana", Font.PLAIN, 15);
        
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBackground(backgroundColor);
        
        // Panel for Equipment Management
        JPanel manageEquipmentPanel = new JPanel(new BorderLayout());
        manageEquipmentPanel.setBackground(backgroundColor);
        manageEquipmentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblGymHallId = new JLabel("Gym Hall ID:");
        lblGymHallId.setFont(labelFont);
        lblGymHallId.setForeground(primaryColor);
        
        JTextField txtGymHallId = new JTextField();
        txtGymHallId.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtGymHallId.setBorder(new RoundedBorder(15));
        
        JLabel lblEquipmentName = new JLabel("Equipment Name:");
        lblEquipmentName.setFont(labelFont);
        lblEquipmentName.setForeground(primaryColor);
        
        JTextField txtEquipmentName = new JTextField();
        txtEquipmentName.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtEquipmentName.setBorder(new RoundedBorder(15));
        
        JLabel lblEquipmentType = new JLabel("Equipment Type:");
        lblEquipmentType.setFont(labelFont);
        lblEquipmentType.setForeground(primaryColor);
        
        JTextField txtEquipmentType = new JTextField();
        txtEquipmentType.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtEquipmentType.setBorder(new RoundedBorder(15));
        
        
        JLabel lblEquipmentState = new JLabel("Equipment Condition:");
        lblEquipmentState.setFont(labelFont);
        lblEquipmentState.setForeground(primaryColor);
        
        JTextField txtEquipmentState = new JTextField();
        txtEquipmentState.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtEquipmentState.setBorder(new RoundedBorder(15));
        
        inputPanel.add(lblGymHallId);
        inputPanel.add(txtGymHallId);
        inputPanel.add(lblEquipmentName);
        inputPanel.add(txtEquipmentName);
        inputPanel.add(lblEquipmentType);
        inputPanel.add(txtEquipmentType);
        inputPanel.add(lblEquipmentState);
        inputPanel.add(txtEquipmentState);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        /*
        JButton btnAddEquipment = new JButton("Add Equipment");
        JButton btnUpdateCondition = new JButton("Update Condition");
        JButton btnRemoveEquipment = new JButton("Remove Equipment");
        JButton btnViewAllEquipment = new JButton("View All Equipment");
        */
        JButton btnAddEquipment = createStyledButton("Add Equipment", primaryColor, buttonTextColor, buttonFont);
        JButton btnUpdateCondition = createStyledButton("Update Condition", primaryColor, buttonTextColor, buttonFont);
        JButton btnRemoveEquipment = createStyledButton("Remove Equipment", primaryColor, buttonTextColor, buttonFont);
        JButton btnViewAllEquipment = createStyledButton("View All Equipments", primaryColor, buttonTextColor, buttonFont);

        buttonPanel.add(btnAddEquipment);
        buttonPanel.add(btnUpdateCondition);
        buttonPanel.add(btnRemoveEquipment);
        buttonPanel.add(btnViewAllEquipment);

        manageEquipmentPanel.add(inputPanel, BorderLayout.CENTER);
        manageEquipmentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(manageEquipmentPanel, BorderLayout.CENTER);

        // Button Actions
        btnAddEquipment.addActionListener(e -> {
            try {
                int gymHallId = Integer.parseInt(txtGymHallId.getText());
                String name = txtEquipmentName.getText();
                String type = txtEquipmentType.getText();
                String state = txtEquipmentState.getText();

                Equipment equipment = new Equipment(gymHallId, name, type, state);
                EquipmentDAO equipmentDAO = new EquipmentDAO();
                int equipmentId = equipmentDAO.addEquipment(equipment);

                if (equipmentId != -1) {
                    JOptionPane.showMessageDialog(this, "Equipment added successfully with ID: " + equipmentId);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add equipment.");
                }

                txtGymHallId.setText("");
                txtEquipmentName.setText("");
                txtEquipmentType.setText("");
                txtEquipmentState.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdateCondition.addActionListener(e -> {
            try {
                int equipmentId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Equipment ID to update:"));
                String newCondition = JOptionPane.showInputDialog(this, "Enter new condition:");

                EquipmentDAO equipmentDAO = new EquipmentDAO();
                equipmentDAO.updateCondition(equipmentId, newCondition);

                JOptionPane.showMessageDialog(this, "Equipment condition updated successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemoveEquipment.addActionListener(e -> {
            try {
                int equipmentId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Equipment ID to remove:"));

                EquipmentDAO equipmentDAO = new EquipmentDAO();
                equipmentDAO.removeEquipment(equipmentId);

                JOptionPane.showMessageDialog(this, "Equipment removed successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnViewAllEquipment.addActionListener(e -> {
            try {
                EquipmentDAO equipmentDAO = new EquipmentDAO();
                List<Equipment> equipmentList = equipmentDAO.getAllEquipment();

                if (!equipmentList.isEmpty()) {
                    StringBuilder equipmentDetails = new StringBuilder("Equipment List:\n");
                    for (Equipment equipment : equipmentList) {
                        equipmentDetails.append(equipment.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, equipmentDetails.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "No equipment found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private JButton createStyledButton(String text, Color background, Color foreground, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Rounded Border Class
    static class RoundedBorder implements javax.swing.border.Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 2, this.radius + 2, this.radius + 2, this.radius + 2);
        }

        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        }
    }

}
