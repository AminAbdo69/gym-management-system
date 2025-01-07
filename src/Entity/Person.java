package Entity;

import DAO.PersonDAO;

public class Person {
    
    // Attributes
    private String fName;
    private String lName;
    private int id;
    private int age;
    private String phone;
    private String address;
    private String gender;
    private PersonDAO personDAO = new PersonDAO(); // Instance of PersonDAO

    // Constructor
    public Person(String fName, String lName, int age, String phone, String address, String gender) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
    
    public int getId() {
        return id;
    }

    // Setters and Getters
    public void setId(int id) {    
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
        personDAO.updatePerson(this); // Update in DB
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
        personDAO.updatePerson(this); // Update in DB
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        personDAO.updatePerson(this); // Update in DB
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        personDAO.updatePerson(this); // Update in DB
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        personDAO.updatePerson(this); // Update in DB
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        personDAO.updatePerson(this); // Update in DB
    }

    @Override
    public String toString() {
        return "Person{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
