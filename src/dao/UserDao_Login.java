/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnector;
import model.UserData_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class UserDao_Login {
    MySqlConnector mysql = new MySqlConnector();

    public void createUser(UserData_Login user) {
        Connection conn = mysql.openConnection();
        String sql = "insert into users(username, password) values (?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean checkUser(UserData_Login user) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    // 🔥 THE FIXED LOGIN METHOD
    public UserData_Login authenticateUser(String username, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UserData_Login loggedUser = new UserData_Login();
                loggedUser.setId(rs.getInt("user_id"));
                loggedUser.setUsername(rs.getString("username"));
                loggedUser.setPassword(rs.getString("password"));
                return loggedUser;
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
}