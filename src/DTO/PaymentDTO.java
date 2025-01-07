/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author hp
 */

import java.time.LocalDate;

public class PaymentDTO {
    private int id;                       // Unique identifier
    private int memberId;                 // Member ID associated with the payment
    private double amount;                // Payment amount
    private LocalDate paymentDate;        // Date of payment
    private LocalDate dueDate;            // Due date for payment
    private int daysRemaining;            // Remaining days for payment
    private double discountRate;           // Rate of discount applied to the payment
    private String status;                // Current payment status

    // Constructor
    public PaymentDTO( int memberId, double amount, LocalDate paymentDate, LocalDate dueDate, double discountRate) {
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.discountRate = discountRate;
        this.daysRemaining = calculateDaysRemaining();
        this.status = "Pending"; // Default status
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        this.daysRemaining = calculateDaysRemaining();
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to calculate remaining days
    private int calculateDaysRemaining() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}

