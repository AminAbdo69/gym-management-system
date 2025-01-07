package DAO;

import Entity.Person;
import java.sql.*;

public class PersonDAO {

    private static final String USERNAME = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/java-project"; // Your database name

    // Method to add a person to the database
    public int addPerson(Person person) {
        String sql = "INSERT INTO Person (FName, LName, Age, Phone, Address, Gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, person.getfName());
            pstmt.setString(2, person.getlName());
            pstmt.setInt(3, person.getAge());
            pstmt.setString(4, person.getPhone());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getGender());

            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Returns the generated Person ID
            }
        } catch (SQLException e) {
            System.out.println("Error adding person: " + e.getMessage());
        }
        return -1;
    }

    // Method to get a person by ID
    public Person getPersonById(int id) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getInt("Age"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Gender")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving person: " + e.getMessage());
        }
        return null; // Return null if person with ID is not found
    }

    // Method to update a person's details
    public boolean updatePerson(Person person) {
        String sql = "UPDATE Person SET FName = ?, LName = ?, Age = ?, Phone = ?, Address = ?, Gender = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getfName());
            pstmt.setString(2, person.getlName());
            pstmt.setInt(3, person.getAge());
            pstmt.setString(4, person.getPhone());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getGender());
            pstmt.setInt(7, person.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            System.out.println("Error updating person: " + e.getMessage());
        }
        return false; // Return false if the update failed
    }

    // Method to delete a person by ID
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0; // Returns true if the deletion was successful
        } catch (SQLException e) {
            System.out.println("Error deleting person: " + e.getMessage());
        }
        return false; // Return false if the deletion failed
    }

    // Main method for testing
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();

        // Add a new person
        Person newPerson = new Person("Amin", "Abdo", 21, "1234567890", "123 Gym Street", "Male");
        int personId = personDAO.addPerson(newPerson);
        System.out.println("New person added with ID: " + personId);

        // Get person by ID
        Person person = personDAO.getPersonById(personId);
        System.out.println("Retrieved person: " + person);

        // Update person's details
        person.setPhone("0987654321");
        boolean updateSuccess = personDAO.updatePerson(person);
        System.out.println("Person update successful: " + updateSuccess);

        // Delete person
        boolean deleteSuccess = personDAO.deletePerson(personId);
        System.out.println("Person deletion successful: " + deleteSuccess);
    }
}
