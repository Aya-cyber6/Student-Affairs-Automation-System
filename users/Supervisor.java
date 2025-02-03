package termProject.users;

public class Supervisor extends User {
    // Constructor to initialize Supervisor with username and password
    public Supervisor(String username, String password) {
        super(username, password);  // Call the constructor of the superclass (User)
    }

    // Implement the abstract method from User class
    @Override
    public String displayRole() {
        return "Supervisor";  // Return the role of the user
    }
}