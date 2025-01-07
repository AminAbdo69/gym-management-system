package Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Payment {
    
    private int id;                    // Unique identifier
    private int memberID;              // The member associated with the payment
    private double amount;             // Payment amount
    private LocalDate paymentDate;     // Date of payment
    private LocalDate dueDate;         // Due date for payment
    private int daysRemaining;         // Remaining days for payment
    private double discountRate;       // Rate of discount applied to the payment
    private String status;             // Current payment status

    // Constructor
    public Payment(int memberID, double amount, LocalDate paymentDate, LocalDate dueDate, double discountRate) {
        this.memberID = memberID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.discountRate = discountRate;
        this.status = "Pending"; // Default status
        this.daysRemaining = calculateDaysRemaining2(); // This will be calculated in DAO
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public int getDaysRemaining() { return daysRemaining; }
    public void setDaysRemaining(int daysRemaining) { this.daysRemaining = daysRemaining; }
    public double getDiscountRate() { return discountRate; }
    public void setDiscountRate(double discountRate) { this.discountRate = discountRate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // ToString method for debugging purposes
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", memberID=" + memberID +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", dueDate=" + dueDate +
                ", daysRemaining=" + daysRemaining +
                ", discountRate=" + discountRate +
                ", status='" + status + '\'' +
                '}';
    }

    // Methods to interact with DAO
    public void save() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        dao.savePayment(this);
    }

    public void update() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        dao.updatePayment(this);
    }

    public static Payment getById(int id) {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        return dao.getPaymentById(id);
    }

    public static List<Payment> getAll() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        return dao.getAllPayments();
    }

    public void delete() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        dao.deletePayment(this.id);
    }

    public double calculateDiscount() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        return dao.calculateDiscount(this);
    }

    public int calculateDaysRemaining() {
        DAO.PaymentDAO dao = new DAO.PaymentDAO();
        return dao.calculateDaysRemaining(this.dueDate);
    }
    
        // Method to calculate remaining days
    public int calculateDaysRemaining2() {
        if (dueDate == null) {
            return -1; // Return a special value indicating an error
        }
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(today, dueDate);
    }
    
}