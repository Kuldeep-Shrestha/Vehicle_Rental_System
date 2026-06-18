/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao_Login;
import javax.swing.JOptionPane;
import model.UserData_Login;

/**
 *
 * @author User
 */
public class LoginController {

    private UserDao_Login userDao = new UserDao_Login();

    public UserData_Login handleLogin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and Password cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        UserData_Login loggedUser = userDao.authenticateUser(username, password);

        if (loggedUser != null) {
            JOptionPane.showMessageDialog(null, "Welcome back, " + loggedUser.getUsername() + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
        return loggedUser;
    }
}