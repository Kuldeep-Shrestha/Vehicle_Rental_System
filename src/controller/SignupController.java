/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao_Signup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.UserData_Signup;
import view.login;
import view.Signup;

/**
 *
 * @author User
 */
public class SignupController {

    private final UserDao_Signup userDao = new UserDao_Signup();
    private final Signup userView;

    public SignupController(Signup userView) {
        this.userView = userView;
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    // Handle registration directly
    public void handleRegistration() {
        try {
            // 1. Read data from the view fields
            String fullName = userView.FullName_Label.getText().trim();
            String email = userView.Email_Label.getText().trim();
            String phoneStr = userView.Phone_Label.getText().trim();
            String dob = userView.Dob_Label.getText().trim();
            String password = userView.Password_Label.getText().trim();
            String confirmPassword = userView.ConfirmPassword_Label.getText().trim();
            String licenseStr = userView.DriverLicense_Label.getText().trim();
            String address = userView.Address_Label.getText().trim();

            // 2. Basic validations
            if (fullName.isEmpty() || email.isEmpty() || phoneStr.isEmpty() ||
                dob.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                licenseStr.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(userView, "All fields are required!",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(userView, "Passwords do not match!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Parse numeric fields
            int phone, license;
            try {
                phone = Integer.parseInt(phoneStr);
                license = Integer.parseInt(licenseStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(userView, "Phone and License must be valid numbers.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Create user object
            UserData_Signup user = new UserData_Signup();
            user.setUsername(email);
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setD_O_B(dob);
            user.setPassword(password);
            user.setConfirm_Password(confirmPassword);
            user.setDriver_license(license);
            user.setAddress(address);

            // 5. Check if user already exists
            boolean exists = userDao.checkUser(user);
            if (exists) {
                JOptionPane.showMessageDialog(userView,
                        "A user with this email or username already exists!",
                        "Duplicate User", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 6. Save to database
            userDao.createUser(user);

            // 7. Success → go to login
            JOptionPane.showMessageDialog(userView,
                    "Registration Successful! Please login.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            userView.dispose();
            new login().setVisible(true);

        } catch (Exception ex) {
            System.out.println("Error during registration: " + ex.getMessage());
            JOptionPane.showMessageDialog(userView,
                    "An unexpected error occurred. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Go to Login
    public void goToLogin() {
        login loginView = new login();
        loginView.setVisible(true);
        userView.dispose();
    }
}