package termProject.interfaces;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.*;

public interface DashboardManagment {
	
	String APP_NAME = "Student Affairs System";
	int MAX_PROFILE_UPDATES = 3;

	
	void initialize();
	
	
    // Default method for logout
    default void logout(JFrame frame) {
        System.out.println("Logging out...");
        JOptionPane.showMessageDialog(frame, "You have logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();  // Close the dashboard
    }
	// Default method for veiwing profile
	default void viewProfile(String username, String role) {
        JOptionPane.showMessageDialog((Component) this, "Profile Information:\nUsername: " + username + "\nRole:" + role,
                "View Profile", JOptionPane.INFORMATION_MESSAGE);
    }

	// Default method for changing the password
	default void changePassword() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel oldPasswordLabel = new JLabel("Old Password:");
		JLabel newPasswordLabel = new JLabel("New Password:");
		JPasswordField oldPasswordField = new JPasswordField();
		JPasswordField newPasswordField = new JPasswordField();

		panel.add(oldPasswordLabel);
		panel.add(oldPasswordField);
		panel.add(newPasswordLabel);
		panel.add(newPasswordField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String oldPassword = new String(oldPasswordField.getPassword());
			String newPassword = new String(newPasswordField.getPassword());

			// Simple validation logic
			if (oldPassword.isEmpty() || newPassword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Passwords cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (oldPassword.equals(newPassword)) {
				JOptionPane.showMessageDialog(null, "New password cannot be the same as the old password!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	// Default method for handling errors
		default void handleError(String errorMessage) {
			JOptionPane.showMessageDialog(null, "Error: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
		}
}
