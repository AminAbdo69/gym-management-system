package DAO;

import Entity.Payment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/java-project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    

    // Save Payment to database
    public void savePayment(Payment payment) {
        String query = "INSERT INTO payments (memberID, amount, paymentDate, dueDate, discountRate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, payment.getMemberID());
            statement.setDouble(2, payment.getAmount());
            statement.setDate(3, Date.valueOf(payment.getPaymentDate()));
            statement.setDate(4, Date.valueOf(payment.getDueDate()));
            statement.setDouble(5, payment.getDiscountRate());
            statement.setString(6, payment.getStatus());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        payment.setId(generatedKeys.getInt(1));  // Set the generated ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Payment in database
    public void updatePayment(Payment payment) {
        String query = "UPDATE payments SET amount = ?, paymentDate = ?, dueDate = ?, discountRate = ?, status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, payment.getAmount());
            statement.setDate(2, Date.valueOf(payment.getPaymentDate()));
            statement.setDate(3, Date.valueOf(payment.getDueDate()));
            statement.setDouble(4, payment.getDiscountRate());
            statement.setString(5, payment.getStatus());
            statement.setInt(6, payment.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Payment by ID
    public Payment getPaymentById(int id) {
        String query = "SELECT * FROM payments WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Payment payment = new Payment(
                            resultSet.getInt("memberID"),
                            resultSet.getDouble("amount"),
                            resultSet.getDate("paymentDate").toLocalDate(),
                            resultSet.getDate("dueDate").toLocalDate(),
                            resultSet.getDouble("discountRate")
                    );
                    payment.setId(resultSet.getInt("id"));
                    payment.setStatus(resultSet.getString("status"));
                    payment.setDaysRemaining(calculateDaysRemaining(payment.getDueDate()));
                    return payment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all Payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("memberID"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("paymentDate").toLocalDate(),
                        resultSet.getDate("dueDate").toLocalDate(),
                        resultSet.getDouble("discountRate")
                );
                payment.setId(resultSet.getInt("id"));
                payment.setStatus(resultSet.getString("status"));
                payment.setDaysRemaining(calculateDaysRemaining(payment.getDueDate()));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    // Delete Payment by ID
    public void deletePayment(int id) {
        String query = "DELETE FROM payments WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate discount
    public double calculateDiscount(Payment payment) {
        return payment.getAmount() * (payment.getDiscountRate() / 100);
    }

    // Method to calculate remaining days
    public int calculateDaysRemaining(LocalDate dueDate) {
        if (dueDate == null) {
            System.out.println("Due date is null"); 
            return -1; // Return a special value indicating an error
        }
        LocalDate today = LocalDate.now();
        if (dueDate.isBefore(today)) {
            System.out.println("Due date is in the past");
            return 0; // If the due date is in the past, return 0
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(today, dueDate);
    }
}
