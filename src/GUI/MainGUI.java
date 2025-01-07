
package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainGUI() {
        setLayout(new BorderLayout());
        
        Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
//        Color backgroundColor = new Color(0, 20, 64);
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);


        // Load and scale icons
        ImageIcon homeIcon = new ImageIcon("resources/welcome.png");
        ImageIcon memberIcon = new ImageIcon("resources/member2.png");
        ImageIcon trainerIcon = new ImageIcon("resources/trainer2.png");
        ImageIcon shcehuleIcon = new ImageIcon("resources/schedule2.png");
        ImageIcon exerciseIcon = new ImageIcon("resources/exercise2.png");
        ImageIcon exercisePlanIcon = new ImageIcon("resources/exerciseplan2.png");
        ImageIcon gymHallIcon = new ImageIcon("resources/gymhall2.png");
        ImageIcon equipmentsIcon = new ImageIcon("resources/equipments2.png");
        ImageIcon paymentsIcon = new ImageIcon("resources/payments2.png");


        // Scale icons to fit the button size
        int iconWidth = 50;
        int iconHeight = 50;
        homeIcon = new ImageIcon(homeIcon.getImage().getScaledInstance(iconWidth*2, iconHeight*2, Image.SCALE_SMOOTH));
        memberIcon = new ImageIcon(memberIcon.getImage().getScaledInstance(iconWidth*2, iconHeight*2, Image.SCALE_SMOOTH));
        trainerIcon = new ImageIcon(trainerIcon.getImage().getScaledInstance(iconWidth, iconWidth, Image.SCALE_SMOOTH));
        shcehuleIcon = new ImageIcon(shcehuleIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        exerciseIcon = new ImageIcon(exerciseIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        exercisePlanIcon = new ImageIcon(exercisePlanIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        gymHallIcon = new ImageIcon(gymHallIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        equipmentsIcon = new ImageIcon(equipmentsIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        paymentsIcon = new ImageIcon(paymentsIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        


        // Create buttons to switch between GUIs with icons
        JButton btnShowWelcome = new JButton(homeIcon);
        btnShowWelcome.setBackground(primaryColor);
        btnShowWelcome.setFocusPainted(false);
        btnShowWelcome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowMemberGUI = new JButton(memberIcon);
        btnShowMemberGUI.setBackground(primaryColor);
        btnShowMemberGUI.setFocusPainted(false);
        btnShowMemberGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowTrainerGUI = new JButton(trainerIcon);
        btnShowTrainerGUI.setBackground(primaryColor);
        btnShowTrainerGUI.setFocusPainted(false);
        btnShowTrainerGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowScheuleGUI = new JButton(shcehuleIcon);
        btnShowScheuleGUI.setBackground(primaryColor);
        btnShowScheuleGUI.setFocusPainted(false);
        btnShowScheuleGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowExerciseGUI = new JButton(exerciseIcon);
        btnShowExerciseGUI.setBackground(primaryColor);
        btnShowExerciseGUI.setFocusPainted(false);
        btnShowExerciseGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowExercisePlanGUI = new JButton(exercisePlanIcon);
        btnShowExercisePlanGUI.setBackground(primaryColor);
        btnShowExercisePlanGUI.setFocusPainted(false);
        btnShowExercisePlanGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowGymHallGUI = new JButton(gymHallIcon);
        btnShowGymHallGUI.setBackground(primaryColor);
        btnShowGymHallGUI.setFocusPainted(false);
        btnShowGymHallGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowEquipmentsGUI = new JButton(equipmentsIcon);
        btnShowEquipmentsGUI.setBackground(primaryColor);
        btnShowEquipmentsGUI.setFocusPainted(false);
        btnShowEquipmentsGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnShowPaymentsGUI = new JButton(paymentsIcon);
        btnShowPaymentsGUI.setBackground(primaryColor);
        btnShowPaymentsGUI.setFocusPainted(false);
        btnShowPaymentsGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // Add tooltips for better UX
        btnShowWelcome.setToolTipText("Go to Welcome Page");
        btnShowMemberGUI.setToolTipText("Go to Member Management");
        btnShowTrainerGUI.setToolTipText("Go to Trainer Management");
        btnShowScheuleGUI.setToolTipText("Go to Schedule Management");
        btnShowExerciseGUI.setToolTipText("Go to Exercise Management");
        btnShowExercisePlanGUI.setToolTipText("Go to Exercise Plan Management");
        btnShowGymHallGUI.setToolTipText("Go to Gym Hall Management");
        btnShowEquipmentsGUI.setToolTipText("Go to Equipments Management");
        btnShowPaymentsGUI.setToolTipText("Go to Payments Management");


        // Set button size
        Dimension buttonSize = new Dimension(70, 70);
        
        btnShowWelcome.setPreferredSize(buttonSize);
        btnShowMemberGUI.setPreferredSize(buttonSize);
        btnShowTrainerGUI.setPreferredSize(buttonSize);
        btnShowScheuleGUI.setPreferredSize(buttonSize);
        btnShowExerciseGUI.setPreferredSize(buttonSize);
        btnShowExercisePlanGUI.setPreferredSize(buttonSize);
        btnShowGymHallGUI.setPreferredSize(buttonSize);
        btnShowEquipmentsGUI.setPreferredSize(buttonSize);
        btnShowPaymentsGUI.setPreferredSize(buttonSize);


        // Create the panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,0, 10, 0));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(btnShowWelcome);
        buttonPanel.add(btnShowMemberGUI);
        buttonPanel.add(btnShowTrainerGUI);
        buttonPanel.add(btnShowScheuleGUI);
        buttonPanel.add(btnShowExerciseGUI);
        buttonPanel.add(btnShowExercisePlanGUI);
        buttonPanel.add(btnShowGymHallGUI);
        buttonPanel.add(btnShowEquipmentsGUI);
        buttonPanel.add(btnShowPaymentsGUI);


        // Create the card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create instances of MemberGUI, TrainerGUI, and welcome panel
        MemberGUI memberGUI = new MemberGUI();
        TrainerGUI trainerGUI = new TrainerGUI();
        ExerciseGUI exersiceGUI = new ExerciseGUI();
        ExercisePlanGUI exersiceplaneGUI = new ExercisePlanGUI();
        ScheduleGUI scheduleGUI =new ScheduleGUI();
        JPanel welcomePanel = createWelcomePanel();
        GymHallGUI GymHallPanel = new GymHallGUI(); 
        EquipmentGUI EquipmentsPanel = new EquipmentGUI();
        PaymentGUI paymentGUI = new PaymentGUI();


        // Add the GUIs to the card panel
        cardPanel.add(welcomePanel, "WelcomePanel");
        cardPanel.add(memberGUI, "MemberGUI");
        cardPanel.add(trainerGUI, "TrainerGUI");
        cardPanel.add(scheduleGUI, "ScheduleGUI");
        cardPanel.add(exersiceGUI, "ExerciseGUI");
        cardPanel.add(exersiceplaneGUI, "ExercisePlanGUI");
        cardPanel.add(GymHallPanel, "GymHallGUI");
        cardPanel.add(EquipmentsPanel, "EquipmentsGUI");
        cardPanel.add(paymentGUI, "PaymentsGUI");
        


        // Add the button panel and card panel to the main panel
        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add action listeners to switch between cards
        btnShowMemberGUI.addActionListener(e -> cardLayout.show(cardPanel, "MemberGUI"));
        btnShowTrainerGUI.addActionListener(e -> cardLayout.show(cardPanel, "TrainerGUI"));
        btnShowWelcome.addActionListener(e -> cardLayout.show(cardPanel, "WelcomePanel"));
        btnShowScheuleGUI.addActionListener(e -> cardLayout.show(cardPanel, "ScheduleGUI"));
        btnShowExerciseGUI.addActionListener(e -> cardLayout.show(cardPanel, "ExerciseGUI"));
        btnShowExercisePlanGUI.addActionListener(e -> cardLayout.show(cardPanel, "ExercisePlanGUI"));
        btnShowGymHallGUI.addActionListener(e -> cardLayout.show(cardPanel, "GymHallGUI"));
        btnShowEquipmentsGUI.addActionListener(e -> cardLayout.show(cardPanel, "EquipmentsGUI"));
        btnShowPaymentsGUI.addActionListener(e -> cardLayout.show(cardPanel, "PaymentsGUI"));


        // Show welcome panel by default
        cardLayout.show(cardPanel, "WelcomePanel");
    }

    
    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        
        Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);

        // Create a label for the welcome message
        welcomePanel.setBackground(backgroundColor);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel welcomeLabel = new JLabel("Welcome to the Management System", SwingConstants.CENTER);
        welcomeLabel.setBackground(backgroundColor);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        welcomeLabel.setForeground(primaryColor);
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        // Load and scale icons
        ImageIcon homeIcon = new ImageIcon("resources/welcome.png");
        ImageIcon memberIcon = new ImageIcon("resources/member2.png");
        ImageIcon trainerIcon = new ImageIcon("resources/trainer2.png");
        ImageIcon shcehuleIcon = new ImageIcon("resources/schedule2.png");
        ImageIcon exerciseIcon = new ImageIcon("resources/exercise2.png");
        ImageIcon exercisePlanIcon = new ImageIcon("resources/exerciseplan2.png");
        ImageIcon gymHallIcon = new ImageIcon("resources/gymhall2.png");
        ImageIcon equipmentsIcon = new ImageIcon("resources/equipments2.png");
        ImageIcon paymentsIcon = new ImageIcon("resources/payments2.png");

        // Scale icons to fit the button size
        int iconWidth = 40;
        int iconHeight = 40;
        homeIcon = new ImageIcon(homeIcon.getImage().getScaledInstance(iconWidth*2, iconHeight*2, Image.SCALE_SMOOTH));
        memberIcon = new ImageIcon(memberIcon.getImage().getScaledInstance(iconWidth*2, iconHeight*2, Image.SCALE_SMOOTH));
        trainerIcon = new ImageIcon(trainerIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        shcehuleIcon = new ImageIcon(shcehuleIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        exerciseIcon = new ImageIcon(exerciseIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        exercisePlanIcon = new ImageIcon(exercisePlanIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        gymHallIcon = new ImageIcon(gymHallIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        equipmentsIcon = new ImageIcon(equipmentsIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
        paymentsIcon = new ImageIcon(paymentsIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));

        // Create buttons and descriptions
        String[] descriptions = {
            "Manage gym members",
            "Manage trainers and their specialties",
            "Manage gym schedules",
            "Manage exercises",
            "Manage exercise plans",
            "Manage gym halls",
            "Manage gym equipment",
            "Manage payments and transactions"
        };

        ImageIcon[] icons = {
             memberIcon, trainerIcon, shcehuleIcon,
            exerciseIcon, exercisePlanIcon, gymHallIcon, equipmentsIcon, paymentsIcon
        };

        // Main panel with grid layout for 2 items per row
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // 2 columns, dynamic rows
        gridPanel.setBackground(backgroundColor);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        for (int i = 0; i < icons.length; i++) {
            // Create a sub-panel for button and description
            JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
//            itemPanel.setBackground(backgroundColor);
            itemPanel.setPreferredSize(new Dimension(50, 10)); // Half-row size

            // Create button
            JButton button = new JButton(icons[i]);
            button.setBackground(primaryColor);
            button.setPreferredSize(new Dimension(100, 50));

            // Create description label
            JLabel descriptionLabel = new JLabel(descriptions[i]);
            descriptionLabel.setFont(new Font("SansSerif", Font.ITALIC , 16));
            descriptionLabel.setForeground(primaryColor);

            // Add button and description to the item panel
            itemPanel.add(button, BorderLayout.WEST);
            itemPanel.add(descriptionLabel, BorderLayout.CENTER);

            // Add the item panel to the grid
            gridPanel.add(itemPanel);
        }

        // Add the grid panel to the welcome panel
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        welcomePanel.add(scrollPane, BorderLayout.CENTER);

        return welcomePanel;
    }

    
    
//    private JPanel createWelcomePanel() {
//        return new AnimatedWelcomePanel(); 
//    }

    private JPanel createPaymentPanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Payments Coming Soon ... ", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }
    private JPanel createExercisePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Exercise Coming Soon ...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }        
    private JPanel createExercisePlanPanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Exercise Plan Coming Soon ...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }
    private JPanel createGymHallPanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Gym Hall Coming Soon ...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }
    private JPanel createEquipmentsPanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Equipments Coming Soon ...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }
    private JPanel createSchedulePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Schedule Coming Soon ...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        return welcomePanel;
    }
    
    
    
    public class AnimatedWelcomePanel extends JPanel implements Runnable {
        private String text = "Welcome to the Gym  Management System";
        private int x = 150; // Initial X position of the text
        private int y = 50; // Y position for the text
        private final int fontSize = 30;

        public AnimatedWelcomePanel() {
            setPreferredSize(new Dimension(600, 100));
            Thread animationThread = new Thread(this);
            animationThread.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Serif", Font.BOLD, fontSize));
            g.setColor(Color.BLUE);
            g.drawString(text, x, y);
        }

        @Override
        public void run() {
            while (true) {
                x += 5; // Move text to the right
                if (x > getWidth()) { // Reset position if text moves out of bounds
                    x = -text.length() * 10; // Restart the text from the left
                }
                repaint(); // Redraw the panel
                try {
                    Thread.sleep(50); // Control the animation speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    

    public static void main(String[] args) {
        
         SwingUtilities.invokeLater(() -> {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        });
        JFrame frame = new JFrame("GYM Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MainGUI mainGUI = new MainGUI();
        frame.add(mainGUI);

        frame.pack();
        frame.setVisible(true);
    }
}
