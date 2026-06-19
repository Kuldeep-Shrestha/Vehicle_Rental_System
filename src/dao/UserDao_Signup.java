/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnector;
import java.sql.Connection;
import model.UserData_Signup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class UserDao_Signup {
    
    MySqlConnector mysql = new MySqlConnector();

    // ---------- FIXED: Now includes full_name, removes confirm_password ----------
    public void createUser(UserData_Signup user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (username, email, phone, d_o_b, password, driver_license, address, full_name) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setInt(3, user.getPhone());
            pstm.setString(4, user.getD_O_B());
            pstm.setString(5, user.getPassword());
            pstm.setInt(6, user.getDriver_license());
            pstm.setString(7, user.getAddress());
            pstm.setString(8, user.getFullName());   // <-- NOW INCLUDED
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error creating user: " + e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // ---------- Check for duplicates (email or username) ----------
    public boolean checkUser(UserData_Signup user) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE email = ? OR username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            System.out.print("Check user error: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}