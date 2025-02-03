package termProject.dashboards;

import javax.swing.*;

public abstract class BaseDashboard extends JFrame {
 
	private static final long serialVersionUID = 1L;
	protected String username; // Common property for all dashboards
	protected String role;
    public BaseDashboard(String username, String role) {
        this.username = username;
        this.role = role;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    // Common method to show the dashboard
    public void showDashboard() {
        setVisible(true);
    }
}

