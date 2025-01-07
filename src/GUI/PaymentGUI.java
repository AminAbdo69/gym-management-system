package GUI;

import Entity.Payment;
import DAO.PaymentDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PaymentGUI extends JPanel {

    public PaymentGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        Color primaryColor = new Color(255, 94, 58); 
        Color secondaryColor = new Color(58, 175, 255); 
//        Color backgroundColor = new Color(0, 20, 64);
        Color backgroundColor = new Color(235, 245, 251); 

        Color buttonTextColor = Color.WHITE;
        Font labelFont = new Font("Verdana", Font.BOLD, 15);
        Font buttonFont = new Font("Verdana", Font.ITALIC, 15);

        // Tabbed Pane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();

        // Main Panel for managing payments
        JPanel managePaymentsPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(backgroundColor); 
        inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblMemberID = new JLabel("Member ID:");
        lblMemberID.setForeground(primaryColor);
        JTextField txtMemberID = new JTextField();
        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setForeground(primaryColor);
        JTextField txtAmount = new JTextField();
        JLabel lblPaymentDate = new JLabel("Payment Date (YYYY-MM-DD):");
        lblPaymentDate.setForeground(primaryColor);

        JTextField txtPaymentDate = new JTextField();
        JLabel lblDueDate = new JLabel("Due Date (YYYY-MM-DD):");
        lblDueDate.setForeground(primaryColor);

        JTextField txtDueDate = new JTextField();
        JLabel lblDiscountRate = new JLabel("Discount Rate (%):");
        lblDiscountRate.setForeground(primaryColor);

        JTextField txtDiscountRate = new JTextField();

        inputPanel.add(lblMemberID);
        inputPanel.add(txtMemberID);
        inputPanel.add(lblAmount);
        inputPanel.add(txtAmount);
        inputPanel.add(lblPaymentDate);
        inputPanel.add(txtPaymentDate);
        inputPanel.add(lblDueDate);
        inputPanel.add(txtDueDate);
        inputPanel.add(lblDiscountRate);
        inputPanel.add(txtDiscountRate);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor); 
        JButton btnAddPayment = new JButton("Add Payment");
        btnAddPayment.setBackground(primaryColor);
        btnAddPayment.setForeground(Color.WHITE);
        JButton btnViewPayments = new JButton("View Payments");
        btnViewPayments.setBackground(primaryColor);
        btnViewPayments.setForeground(Color.WHITE);
        JButton btnUpdatePayment = new JButton("Update Payment");
        btnUpdatePayment.setBackground(primaryColor);
        btnUpdatePayment.setForeground(Color.WHITE);
        JButton btnDeletePayment = new JButton("Delete Payment");
        btnDeletePayment.setBackground(primaryColor);
        btnDeletePayment.setForeground(Color.WHITE);

        buttonPanel.add(btnAddPayment);
        buttonPanel.add(btnViewPayments);
        buttonPanel.add(btnUpdatePayment);
        buttonPanel.add(btnDeletePayment);

        managePaymentsPanel.add(inputPanel, BorderLayout.CENTER);
        managePaymentsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Panels to Tabs
        tabbedPane.addTab("Manage Payments", managePaymentsPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Button Actions
        btnAddPayment.addActionListener(e -> {
            try {
                int memberID = Integer.parseInt(txtMemberID.getText());
                double amount = Double.parseDouble(txtAmount.getText());
                LocalDate paymentDate = LocalDate.parse(txtPaymentDate.getText());
                LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
                double discountRate = Double.parseDouble(txtDiscountRate.getText());

                Payment payment = new Payment(memberID, amount, paymentDate, dueDate, discountRate);
                PaymentDAO dao = new PaymentDAO();
                dao.savePayment(payment);

                JOptionPane.showMessageDialog(this, "Payment added successfully!");
                clearInputFields(inputPanel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnViewPayments.addActionListener(e -> {
            try {
                PaymentDAO dao = new PaymentDAO();
                List<Payment> payments = dao.getAllPayments();
                if (!payments.isEmpty()) {
                    StringBuilder paymentList = new StringBuilder("All Payments:\n");
                    for (Payment payment : payments) {
                        paymentList.append(payment).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, paymentList.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "No payments found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdatePayment.addActionListener(e -> {
            try {
                int paymentID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Payment ID to update:"));
                PaymentDAO dao = new PaymentDAO();
                Payment payment = dao.getPaymentById(paymentID);
                if (payment != null) {
                    double newAmount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new amount:", payment.getAmount()));
                    LocalDate newDueDate = LocalDate.parse(JOptionPane.showInputDialog(this, "Enter new due date (YYYY-MM-DD):", payment.getDueDate()));
                    payment.setAmount(newAmount);
                    payment.setDueDate(newDueDate);
                    dao.updatePayment(payment);
                    JOptionPane.showMessageDialog(this, "Payment updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Payment not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDeletePayment.addActionListener(e -> {
            try {
                int paymentID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Payment ID to delete:"));
                PaymentDAO dao = new PaymentDAO();
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Payment ID: " + paymentID + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.deletePayment(paymentID);
                    JOptionPane.showMessageDialog(this, "Payment deleted successfully!");
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

