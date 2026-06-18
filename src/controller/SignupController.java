/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao_Signup;
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
            UserData_Signup user = new UserData_Signup();
            user.setUsername(userView.Username_Label.getText());
            user.setEmail(userView.Email_Label.getText());
            user.setPhone(Integer.parseInt(userView.Phone_Label.getText()));
            user.setD_O_B(userView.Dob_Label.getText());
            user.setPassword(userView.Password_Label.getText());
            user.setConfirm_Password(userView.ConfirmPassword_Label.getText());
            user.setDriver_license(Integer.parseInt(userView.DriverLicense_Label.getText()));
            user.setAddress(userView.Address_Label.getText());

            // Check if passwords match
            if (!user.getPassword().equals(user.getConfirm_Password())) {
                JOptionPane.showMessageDialog(userView, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean check = userDao.checkUser(user);

            if (check) {
                JOptionPane.showMessageDialog(userView, "Username or Email already exists!", "Duplicate User", JOptionPane.WARNING_MESSAGE);
            } else {
                userDao.createUser(user);
                JOptionPane.showMessageDialog(userView, "Registration Successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                userView.dispose();
                new login().setVisible(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(userView, "Please enter valid numbers for Phone and License.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.out.println("Error adding user: " + ex.getMessage());
            JOptionPane.showMessageDialog(userView, "Please fill all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Go to Login
    public void goToLogin() {
        login loginView = new login();
        loginView.setVisible(true);
        userView.dispose();
    }
}