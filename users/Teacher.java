package termProject.users;

public class Teacher extends User {
    public Teacher(String username, String password) {
        super(username, password);
    }

    @Override
    public String displayRole() {
        return "Teacher";
    }
}