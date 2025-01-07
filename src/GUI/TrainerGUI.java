
package GUI;

import Entity.Member;
import Entity.Trainer;
import DAO.MemberDAO;
import DAO.PersonDAO;
import DAO.TrainerDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TrainerGUI extends JPanel {
    public TrainerGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
//        Color backgroundColor = new Color(0, 20, 64);
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);


//        // Tabbed Pane for better navigation
//        JTabbedPane tabbedPane = new JTabbedPane();
//        tabbedPane.setBackground(backgroundColor); 

        // Main Panel for adding trainer
        JPanel addTrainerPanel = new JPanel(new BorderLayout());
        addTrainerPanel.setBorder(new EmptyBorder(50, 20, 0, 20));
        addTrainerPanel.setBackground(backgroundColor);


        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        inputPanel.setBackground(backgroundColor);
    
        
        
        JLabel lblFName = new JLabel("First Name:");
        lblFName.setFont(labelFont);
        lblFName.setForeground(primaryColor);
        JTextField txtFName = new JTextField();
        txtFName.setFont(new Font("Verdana", Font.PLAIN, 14));    
        
        
        JLabel lblLName = new JLabel("Last Name:");
        lblLName.setFont(labelFont);
        lblLName.setForeground(primaryColor);
        JTextField txtLName = new JTextField();
        txtLName.setFont(new Font("Verdana", Font.PLAIN, 14));    
        
        
        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(labelFont);
        lblAge.setForeground(primaryColor);
        JTextField txtAge = new JTextField();
        txtAge.setFont(new Font("Verdana", Font.PLAIN, 14));    
        
        
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(labelFont);
        lblPhone.setForeground(primaryColor);
        JTextField txtPhone = new JTextField();
        txtPhone.setFont(new Font("Verdana", Font.PLAIN, 14));    
        
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(labelFont);
        lblAddress.setForeground(primaryColor);
        JTextField txtAddress = new JTextField();
        txtAddress.setFont(new Font("Verdana", Font.PLAIN, 14));    
        
        
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(labelFont);
        lblGender.setForeground(primaryColor);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(backgroundColor);
        JRadioButton rbMale = new JRadioButton("Male");
        JRadioButton rbFemale = new JRadioButton("Female");
        
        rbMale.setForeground(primaryColor);
        rbFemale.setForeground(primaryColor);

        rbMale.setBackground(backgroundColor); 
        rbFemale.setBackground(backgroundColor); 

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);

        JLabel lblSpecialist = new JLabel("Specialization:");
        lblSpecialist.setFont(labelFont);
        lblSpecialist.setForeground(primaryColor);
        JTextField txtSpecialist = new JTextField();
        txtSpecialist.setFont(new Font("Verdana", Font.PLAIN, 14));    
        

        inputPanel.add(lblFName);
        inputPanel.add(txtFName);
        inputPanel.add(lblLName);
        inputPanel.add(txtLName);
        inputPanel.add(lblAge);
        inputPanel.add(txtAge);
        inputPanel.add(lblPhone);
        inputPanel.add(txtPhone);
        inputPanel.add(lblAddress);
        inputPanel.add(txtAddress);
        inputPanel.add(lblGender);
        inputPanel.add(genderPanel);
        inputPanel.add(lblSpecialist);
        inputPanel.add(txtSpecialist);
       

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor);
        
        ImageIcon addIcon  = new ImageIcon("resources/add2.png");
        ImageIcon updateIcon  = new ImageIcon("resources/update2.png");
        ImageIcon removeIcon  = new ImageIcon("resources/delete2 (2).png");
        ImageIcon deleteIcon  = new ImageIcon("resources/remove.png");
        ImageIcon viewIcon  = new ImageIcon("resources/view2.png");
        
        
        int iconWidth = 50;
        int iconHeight = 50;
        addIcon = new ImageIcon(addIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        updateIcon = new ImageIcon(updateIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        removeIcon = new ImageIcon(removeIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        deleteIcon = new ImageIcon(deleteIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        viewIcon = new ImageIcon(viewIcon.getImage().getScaledInstance(80, 30, Image.SCALE_SMOOTH));


        JButton btnAddTrainer = new JButton(addIcon);
        btnAddTrainer.setBackground(primaryColor);
        btnAddTrainer.setToolTipText("Add a new trainer to the system");
        btnAddTrainer.setFocusPainted(false);
        btnAddTrainer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnUpdateSpecialization = new JButton(updateIcon);
        btnUpdateSpecialization.setBackground(primaryColor);
        btnUpdateSpecialization.setToolTipText("Update the specialization of a trainer");
        btnUpdateSpecialization.setFocusPainted(false);
        btnUpdateSpecialization.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnRemoveCustomer = new JButton(removeIcon);
        btnRemoveCustomer.setBackground(primaryColor);
        btnRemoveCustomer.setToolTipText("Remove a customer from a trainer");
        btnRemoveCustomer.setFocusPainted(false);
        btnRemoveCustomer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnDeleteTrainer = new JButton(deleteIcon);
        btnDeleteTrainer.setBackground(primaryColor);
        btnDeleteTrainer.setToolTipText("Delete a trainer");
        btnDeleteTrainer.setFocusPainted(false);
        btnDeleteTrainer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnViewCustomers = new JButton(viewIcon);
        btnViewCustomers.setBackground(primaryColor);
        btnViewCustomers.setToolTipText("View all customers for a trainer");
        btnViewCustomers.setFocusPainted(false);
        btnViewCustomers.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        
        Dimension buttonSize = new Dimension(70, 70);
        btnAddTrainer.setPreferredSize(buttonSize);
        btnUpdateSpecialization.setPreferredSize(buttonSize);
        btnRemoveCustomer.setPreferredSize(buttonSize);
        btnDeleteTrainer.setPreferredSize(buttonSize);
        btnViewCustomers.setPreferredSize(buttonSize);

        
        
        buttonPanel.add(btnAddTrainer);
        buttonPanel.add(btnUpdateSpecialization);
        buttonPanel.add(btnRemoveCustomer);
        buttonPanel.add(btnDeleteTrainer);
        buttonPanel.add(btnViewCustomers);

        addTrainerPanel.add(inputPanel, BorderLayout.CENTER);
        addTrainerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(addTrainerPanel);

        // Add Panels to Tabs
//        tabbedPane.addTab("Manage Trainers", addTrainerPanel);
//        tabbedPane.addTab("Statistics", createStatisticsPanel());

//        add(tabbedPane, BorderLayout.CENTER);
        
        
        
            btnAddTrainer.addActionListener(e -> {
            try {
                String fName = txtFName.getText();
                String lName = txtLName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String phone = txtPhone.getText();
                String address = txtAddress.getText();
                String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : null;
                String specialist = txtSpecialist.getText();

                if (gender == null) {
                    JOptionPane.showMessageDialog(this, "Please select a gender.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Trainer trainer = new Trainer(fName, lName, age, phone, address, gender, specialist);
                PersonDAO personDAO = new PersonDAO();
                int trainerId = personDAO.addPerson(trainer);

                if (trainerId != -1) {
                    TrainerDAO trainerDAO = new TrainerDAO();
                    trainer.setId(trainerId);
                    trainerDAO.addTrainer(trainer, trainerId);
                    JOptionPane.showMessageDialog(this, "Trainer added successfully with ID: " + trainerId);
                    clearInputFields(inputPanel);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add trainer.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdateSpecialization.addActionListener(e -> {
            try {
                int trainerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Trainer ID:"));
                TrainerDAO trainerDAO = new TrainerDAO();
                Trainer trainer = trainerDAO.getTrainer(trainerId);
                if (trainer != null) {
                    String newSpecialist = JOptionPane.showInputDialog(this, "Enter new specialization :", trainer.getSpecialist());
                    
                    trainer.setSpecialist(newSpecialist);
                    
                    trainerDAO.updateTrainer(trainer);
                    JOptionPane.showMessageDialog(this, "Trainer's specialization updated to: " + newSpecialist);
                } else {
                    JOptionPane.showMessageDialog(this, "Trainer not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRemoveCustomer.addActionListener(e -> {
            try {
                
                int trainerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Trainer ID:"));
                int customerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Customer ID:"));

                TrainerDAO trainerDAO = new TrainerDAO();
                Trainer trainer = trainerDAO.getTrainer(trainerId);
                if (trainer != null) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove Customer ID: " + customerId + " from Trainer ID: " + trainerId + "?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        trainer.removeCustomer(customerId);
                        JOptionPane.showMessageDialog(this, "Customer with ID: " + customerId + " removed from Trainer : " + trainer.getfName());
                        clearInputFields(inputPanel);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Trainer not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDeleteTrainer.addActionListener(e -> {
            try {
                int trainerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Trainer ID to delete:"));
                TrainerDAO trainerDAO = new TrainerDAO();
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Trainer ID: " + trainerId + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    trainerDAO.deleteTrainer(trainerId);
                    JOptionPane.showMessageDialog(this, "Trainer with ID: " + trainerId + " deleted successfully.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnViewCustomers.addActionListener(e -> {
            try {
                int trainerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Trainer ID to view customers:"));
                TrainerDAO trainerDAO = new TrainerDAO();
                Trainer trainer = trainerDAO.getTrainer(trainerId);
                if (trainer != null) {
                    List<Member> customers = trainer.getCustomers();
                    StringBuilder customerList = new StringBuilder("Customers for Trainer ID: " + trainerId + "\n");
                    for (Member customer : customers) {
                        customerList.append("Customer : ").append(customer).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, customerList.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Trainer not found.");
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
