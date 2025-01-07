
package GUI;

import DAO.ExercisePlanDAO;
import Entity.Member;
import DAO.PersonDAO;
import DAO.MemberDAO;
import DAO.ScheduleDAO;
import DAO.TrainerDAO;
import Entity.ExercisePlan;
import Entity.Schedule;
import Entity.Trainer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class MemberGUI extends JPanel {
    public MemberGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 500));

        
        
        Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
//        Color backgroundColor = new Color(0, 20, 64);
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);
        
        


        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

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

        JLabel lblPhone = new JLabel("Phone Number:");
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

        JLabel lblWeight = new JLabel("Weight:");
        lblWeight.setFont(labelFont);
        lblWeight.setForeground(primaryColor);
        JTextField txtWeight = new JTextField();
        txtWeight.setFont(new Font("Verdana", Font.PLAIN, 14));


        JLabel lblExercisePlan = new JLabel("Exercise Plan ID:");
        lblExercisePlan.setFont(labelFont);
        lblExercisePlan.setForeground(primaryColor);
        JTextField txtExercisePlan = new JTextField();
        txtExercisePlan.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblAssignedTrainer = new JLabel("Assigned Trainer ID:");
        lblAssignedTrainer.setFont(labelFont);
        lblAssignedTrainer.setForeground(primaryColor);
        JTextField txtAssignedTrainer = new JTextField();
        txtAssignedTrainer.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblSchedule = new JLabel("Schedule ID:");
        lblSchedule.setFont(labelFont);
        lblSchedule.setForeground(primaryColor);
        JTextField txtSchedule = new JTextField();
        txtSchedule.setFont(new Font("Verdana", Font.PLAIN, 14));

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
        inputPanel.add(lblWeight);
        inputPanel.add(txtWeight);
        inputPanel.add(lblExercisePlan);
        inputPanel.add(txtExercisePlan);
        inputPanel.add(lblAssignedTrainer);
        inputPanel.add(txtAssignedTrainer);
        inputPanel.add(lblSchedule);
        inputPanel.add(txtSchedule);

        // Button Panel
        JButton btnAddMember = createStyledButton("Add Member", primaryColor, buttonTextColor, buttonFont);
        JButton btnDeleteMember = createStyledButton("Delete Member", primaryColor, buttonTextColor, buttonFont);
        JButton btnRetrieveMember = createStyledButton("Retrieve Member", primaryColor, buttonTextColor, buttonFont);
        JButton btnUpdateMember = createStyledButton("Update Member", primaryColor, buttonTextColor, buttonFont);
        JButton btnRetrieveAllMembers = createStyledButton("Retrieve Members", primaryColor, buttonTextColor, buttonFont);
        JButton btnRetrieveAllTrainers = createStyledButton("Retrieve Trainer", primaryColor, buttonTextColor, buttonFont);
        JButton btnRetrieveAllSchedules = createStyledButton("Retrieve Shedules", primaryColor, buttonTextColor, buttonFont);
        JButton btnRetrieveAllExercisePlans = createStyledButton("Retrieve Plans", primaryColor, buttonTextColor, buttonFont);




        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        buttonPanel.add(btnAddMember);
        buttonPanel.add(btnDeleteMember);
        buttonPanel.add(btnRetrieveMember);
        buttonPanel.add(btnUpdateMember);
        buttonPanel.add(btnRetrieveAllMembers);
        buttonPanel.add(btnRetrieveAllTrainers);
        buttonPanel.add(btnRetrieveAllSchedules);
        buttonPanel.add(btnRetrieveAllExercisePlans);



        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add Member Button Action
        btnAddMember.addActionListener(e -> {
            try {
                // Gather input
                String fName = txtFName.getText();
                String lName = txtLName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String phone = txtPhone.getText();
                String address = txtAddress.getText();
                String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : null;
                double weight = Double.parseDouble(txtWeight.getText());
                int exercisePlan = Integer.parseInt(txtExercisePlan.getText());
                int assignedTrainer = Integer.parseInt(txtAssignedTrainer.getText());
                int schedule = Integer.parseInt(txtSchedule.getText());

                if (gender == null) {
                    JOptionPane.showMessageDialog(this, "Please select a gender.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create Member
                Member member = new Member(fName, lName, age, phone, address, gender, assignedTrainer, weight, exercisePlan, schedule);
                PersonDAO personDAO = new PersonDAO();
                int personId = personDAO.addPerson(member);

                if (personId != -1) {
                    MemberDAO memberDAO = new MemberDAO();
                    memberDAO.addMember(member, personId);

                    JOptionPane.showMessageDialog(this, "Member added successfully ");
                    clearInputFields(inputPanel);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to create person.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Member Button Action
        btnDeleteMember.addActionListener(e -> {
            try {
                String personIdStr = JOptionPane.showInputDialog(this, "Enter Member ID to delete:");
                if (personIdStr != null) {
                    int personId = Integer.parseInt(personIdStr);
                    MemberDAO memberDAO = new MemberDAO();
                    
                    if( memberDAO.deleteMember(personId))
                        JOptionPane.showMessageDialog(this, "Member deleted successfully.");
                    else 
                        JOptionPane.showMessageDialog(this, "Member deleted Failure.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Retrieve Member Button Action
        btnRetrieveMember.addActionListener(e -> {
            try {
                String personIdStr = JOptionPane.showInputDialog(this, "Enter Member ID to retrieve:");
                if (personIdStr != null) {
                    int personId = Integer.parseInt(personIdStr);
                    MemberDAO memberDAO = new MemberDAO();
                    Member member = memberDAO.getMember(personId);

                    if (member != null) {
                        JOptionPane.showMessageDialog(this, "Retrieved Member:\n" + member);
                    } else {
                        JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update Member Button Action
        btnUpdateMember.addActionListener(e -> {
            try {
                String personIdStr = JOptionPane.showInputDialog(this, "Enter Member ID to update:");
                if (personIdStr != null) {
                    int personId = Integer.parseInt(personIdStr);
                    MemberDAO memberDAO = new MemberDAO();
                    Member member = memberDAO.getMember(personId);

                    if (member != null) {
                        // Gather updated input
                        String newWeight = JOptionPane.showInputDialog(this, "Enter new weight:", member.getWeight());
                        String newExercisePlan = JOptionPane.showInputDialog(this, "Enter new Exercise Plan ID:", member.getExercisePlan());
                        String newAssignedTrainer = JOptionPane.showInputDialog(this, "Enter new Assigned Trainer ID:", member.getAssignedTrainer());
                        String newSchedule = JOptionPane.showInputDialog(this, "Enter new Schedule ID:", member.getSchedule());

                        member.setWeight(Double.parseDouble(newWeight));
                        member.setExercisePlan(Integer.parseInt(newExercisePlan));
                        member.setAssignedTrainer(Integer.parseInt(newAssignedTrainer));
                        member.setSchedule(Integer.parseInt(newSchedule));

                        // Update in the database
                        memberDAO.updateMember(member, personId);

                        JOptionPane.showMessageDialog(this, "Member updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
       btnRetrieveAllMembers.addActionListener(e -> {
            try {
            MemberDAO memberDAO = new MemberDAO(); 
            List<Member> members = memberDAO.getAllMembers();
            //List<Member> members = memberDAO.getAllMembers();
            if (!members.isEmpty()) {
                StringBuilder memberList = new StringBuilder("All Members:\n");
                for (Member member : members) {
                    memberList.append("Member ID: ").append(member.getId())
                              .append(", Name: ").append(member.getfName()).append(" ").append(member.getlName())
                              .append(", Age: ").append(member.getAge())
                              .append(", Phone: ").append(member.getPhone())
                              .append(", Address: ").append(member.getAddress())
                              .append(", Gender: ").append(member.getGender())
                              .append(", Assigned Trainer ID: ").append(member.getAssignedTrainer())
                              .append(", Weight: ").append(member.getWeight())
                              .append(", Exercise Plan ID: ").append(member.getExercisePlan())
                              .append(", Schedule ID: ").append(member.getSchedule())
                              .append("\n");
                }
                JOptionPane.showMessageDialog(this, memberList.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No members found.");
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
       
        btnRetrieveAllTrainers.addActionListener(e -> {
            try {
            TrainerDAO trainerDAO = new TrainerDAO(); 
            List<Trainer> trainers = trainerDAO.getAllTrainers();   
            if (!trainers.isEmpty()) {
                StringBuilder trainerList = new StringBuilder("All Trainers:\n");
                for (Trainer trainer : trainers) {
                    trainerList.append("Trainer ID: ").append(trainer.getId())
                              .append(", Name: ").append(trainer.getfName()).append(" ").append(trainer.getlName())
                              .append(", Age: ").append(trainer.getAge())
                              .append(", Phone: ").append(trainer.getPhone())
                              .append(", Address: ").append(trainer.getAddress())
                              .append(", Gender: ").append(trainer.getGender())
                              .append(", Specialist: ").append(trainer.getSpecialist())
                              .append("\n");
                }
                JOptionPane.showMessageDialog(this, trainerList.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No Trainers found.");
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnRetrieveAllExercisePlans.addActionListener(e -> {
            try {
            ExercisePlanDAO exercisePlandao = new ExercisePlanDAO(); 
            List<ExercisePlan> exercisePlanes = exercisePlandao.getAllExercisePlans();
            if (!exercisePlanes.isEmpty()) {
                StringBuilder exercisePlanesList = new StringBuilder("All ExercisePlanes:\n");
                for (ExercisePlan exercisePlan : exercisePlanes) {
                    exercisePlanesList.append("ExercisePlan ID: ").append(exercisePlan.getId())
                              .append(", Plan Name: ").append(exercisePlan.getPlanName())
                              .append(", Type: ").append(exercisePlan.getType())
                              .append("\n");
                }
                JOptionPane.showMessageDialog(this, exercisePlanesList.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No exercisePlanes found.");
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
         btnRetrieveAllSchedules.addActionListener(e -> {
            try {
            ScheduleDAO scheduledao = new ScheduleDAO(); 
            List<Schedule> shedules = scheduledao.getAllSchedules();
            if (!shedules.isEmpty()) {
                StringBuilder shedulesList = new StringBuilder("All Shedules:\n");
                for (Schedule schedule : shedules) {
                    shedulesList.append(" Schedule ID: ").append(schedule.getId())
                              .append(", Schedule Day: ").append(schedule.getDay())
                              .append("\n");
                }
                JOptionPane.showMessageDialog(this, shedulesList.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No Shedules found.");
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
    
    private JButton createStyledButton(String text, Color background, Color foreground, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        
        button.setForeground(foreground);
        button.setBackground(background);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    
    }
}

