

package GUI;
import Entity.Equipment;
import Entity.GymHall;
import DAO.EquipmentDAO;
import DAO.GymHallDAO;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

/**
 *
 * @author Ashraf U.Seliem
 */
public class GymHallGUI extends JPanel{
    public GymHallGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        //New******
        //Newwwwww
        Color primaryColor = new Color(255, 94, 58); // Bright Coral
        Color secondaryColor = new Color(58, 175, 255); // Bright Blue
        Color backgroundColor = new Color(235, 245, 251); // Light Gray
        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 25);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 20);
        setBackground(backgroundColor);
        
        // Tabbed Pane for better navigation
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Verdana", Font.BOLD, 14));
        tabbedPane.setBackground(secondaryColor);
        tabbedPane.setForeground(Color.BLACK);

        // Panel for Gym Hall Management
        JPanel manageGymHallPanel = new JPanel(new BorderLayout());
        manageGymHallPanel.setBackground(backgroundColor);
        manageGymHallPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Main Panel for adding GymHall
        //JPanel addGymHallPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel gymHallInputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        gymHallInputPanel.setBackground(backgroundColor);
        gymHallInputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblName = new JLabel("Gym Hall Name:");
        lblName.setFont(labelFont);
        lblName.setForeground(primaryColor);
        JTextField txtName = new JTextField();
        txtName.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtName.setBorder(new RoundedBorder(15));

        JLabel lblCapacity = new JLabel("Capacity:");
        lblCapacity.setFont(labelFont);
        lblCapacity.setForeground(primaryColor);
        JTextField txtCapacity = new JTextField();
        txtCapacity.setFont(new Font("Verdana", Font.PLAIN, 14));
        txtCapacity.setBorder(new RoundedBorder(15));
        
        gymHallInputPanel.add(lblName);
        gymHallInputPanel.add(txtName);
        gymHallInputPanel.add(lblCapacity);
        gymHallInputPanel.add(txtCapacity);
        
        // Button Panel
        JPanel gymHallButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
         gymHallButtonPanel.setBackground(backgroundColor);

        JButton btnAdd = createStyledButton("Add Gym Hall", primaryColor, buttonTextColor, buttonFont);
        JButton btnUpdate = createStyledButton("Update Gym Hall Capacity", primaryColor, buttonTextColor, buttonFont);
        JButton btnDelete = createStyledButton("Delete Gym Hall", primaryColor, buttonTextColor, buttonFont);
        JButton btnView = createStyledButton("View All Gym Halls", primaryColor, buttonTextColor, buttonFont);

        gymHallButtonPanel.add(btnAdd);
        gymHallButtonPanel.add(btnUpdate);
        gymHallButtonPanel.add(btnDelete);
        gymHallButtonPanel.add(btnView);

        manageGymHallPanel.add(gymHallInputPanel, BorderLayout.CENTER);
        manageGymHallPanel.add(gymHallButtonPanel, BorderLayout.SOUTH);

        
        tabbedPane.addTab("Manage Gym Halls", manageGymHallPanel);
        //tabbedPane.addTab("Manage Equipment", manageEquipmentPanel);

        add(tabbedPane, BorderLayout.CENTER);

        
        // Button Actions
        btnAdd.addActionListener(e -> {
            try {
                String name = txtName.getText();
                int capacity = Integer.parseInt(txtCapacity.getText());
                //int GymHallID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter GymHall ID:"));
                if (name.isEmpty() || capacity == 0) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields. The capacity must be more than 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                GymHall gymHall = new GymHall(name , capacity);
                GymHallDAO gymHallDAO = new GymHallDAO();
                gymHallDAO.addGymHall(gymHall);

                
               // int id = gymHallDAO.addGymHall(gymHall);
                if (gymHall != null) {
                    JOptionPane.showMessageDialog(this, "Gym Hall added successfully." );
                    txtName.setText("");
                    txtCapacity.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add Gym Hall.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
    btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Gym Hall ID to update:"));
                GymHallDAO gymHallDAO = new GymHallDAO();
                GymHall gymHall = gymHallDAO.getGymHall(id);
                if (gymHall != null) {
                    String newName = JOptionPane.showInputDialog(this, "Enter new name:", gymHall.getName());
                    int newCapacity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new capacity:", gymHall.getCapacity()));
                    gymHall.setName(newName);
                    gymHall.setCapacity(newCapacity);
                    gymHallDAO.updateGymHallCapacity(id,newCapacity);
                    JOptionPane.showMessageDialog(this, "Gym Hall updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Gym Hall not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Gym Hall ID to delete:"));
                GymHallDAO gymHallDAO = new GymHallDAO();
                gymHallDAO.deleteGymHall(id);
                JOptionPane.showMessageDialog(this, "Gym Hall deleted successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnView.addActionListener(e -> {
            try {
                GymHallDAO gymHallDAO = new GymHallDAO();
                List<GymHall> gymHalls = gymHallDAO.getAllGymHalls();
                StringBuilder gymHallList = new StringBuilder("Gym Halls:\n");
                for (GymHall gymHall : gymHalls) {
                    gymHallList.append("ID: ").append(gymHall.getId())
                            .append(", Name: ").append(gymHall.getName())
                            .append(", Capacity: ").append(gymHall.getCapacity()).append("\n");
                }
                JOptionPane.showMessageDialog(this, gymHallList.toString());
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
