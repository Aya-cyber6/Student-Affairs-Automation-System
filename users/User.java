package termProject.users;

public abstract class User {
    protected String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Abstract method for displaying the user's role
    public abstract String displayRole();
}


