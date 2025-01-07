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
import java.util.List;

public class TrainerDTO extends PersonDTO {
    private List<MemberDTO> customers; // List of assigned customers
    private String specialist;         // Trainer's area of specialization

    // Constructor
    public TrainerDTO(String fName, String lName, int age, String phone, String address, String gender, String specialist, List<MemberDTO> customers) {
        super(fName, lName, age, phone, address, gender);
        this.specialist = specialist;
        this.customers = customers;
    }
    

    // Getters and Setters

    public List<MemberDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<MemberDTO> customers) {
        this.customers = customers;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    @Override
    public String toString() {
        return super.toString() + ", TrainerDTO{" +
                "specialist='" + specialist + '\'' +
                ", customers=" + customers +
                '}';
    }
}

