package Entity;

import DAO.MemberDAO;
import DAO.TrainerDAO;
import java.util.ArrayList;
import java.util.List;

public class Trainer extends Person {
    private List<Member> customers; // List of assigned customers (members)
    private String specialist;      // Trainer's area of specialization
    private final TrainerDAO trainerDAO;

    // Constructor
    public Trainer(String fName, String lName, int age, String phone, String address, String gender, String specialist) {
        super(fName, lName, age, phone, address, gender);
        this.specialist = specialist;
        this.customers = new ArrayList<>();
        this.trainerDAO = new TrainerDAO(); // Initialize DAO instance for database operations
    }

    // Getters and Setters
    public List<Member> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Member> customers) {
        this.customers = customers;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
        trainerDAO.updateTrainer(this); // Update specialization in the database
    }

    // Methods
    public void addCustomer(int memberid , int TrainerId) {
        MemberDAO memberDao = new MemberDAO();
        Member member = memberDao.getMember(memberid);
        customers.add(member);
        trainerDAO.addCustomerToTrainer(TrainerId, memberid); // Reflect addition in the database
        System.out.println("Customer added: " + member.getfName() + " " + member.getlName());
    }

    public void removeCustomer(int customerId) {
        customers.removeIf(member -> member.getId() == customerId);
        trainerDAO.removeCustomerFromTrainer(this.getId(), customerId); // Reflect removal in the database
        System.out.println("Customer with ID " + customerId + " removed.");
    }

    public void updateCustomerPlan(int customerId, int newPlan) {
        for (Member member : customers) {
            if (member.getId() == customerId) {
                member.setExercisePlan(newPlan);
                // Assume updateExercisePlan() updates the database itself for Member
                System.out.println("Exercise plan updated for customer ID: " + customerId);
            }
        }
    }

    public void loadCustomers() {
        // Load all customers from the database for this trainer
        this.customers = trainerDAO.getCustomersByTrainer(this.getId());
        System.out.println("Loaded customers for Trainer " + this.getfName() + " " + this.getlName());
    }

    public void viewAllCustomers() {
        System.out.println("All customers assigned to Trainer " + this.getfName() + " " + this.getlName() + ":");
        for (Member member : customers) {
            System.out.println(member);
        }
    }

    public boolean searchTrainer(int id) {
        // Using TrainerDAO to perform database lookup
        System.out.println("Searching for trainer with ID: " + id);
        return trainerDAO.getTrainer(id) != null;
    }

    @Override
    public String toString() {
        return super.toString() + ", Trainer{" +
                "specialist='" + specialist + '\'' +
                ", customers=" + customers +
                '}';
    }
}
