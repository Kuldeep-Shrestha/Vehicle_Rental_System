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
     MySqlConnector mysql=new MySqlConnector();

    public void createUser(UserData_Signup user){  

        Connection conn = mysql.openConnection();

        String sql="insert into users(username,email, phone, d_o_b,password,confirm_password,driver_license,address) values (?,?,?,?,?,?,?,?)";

        try(PreparedStatement pstm= conn.prepareStatement(sql)){

            pstm.setString(1,user.getUsername());
            pstm.setString(2,user.getEmail());
            pstm.setInt(3,user.getPhone());
            pstm.setString(4,user.getD_O_B());
            pstm.setString(5,user.getPassword());
            pstm.setString(6,user.getConfirm_Password());
            pstm.setInt(7,user.getDriver_license());
            pstm.setString(8,user.getAddress());

            pstm.executeUpdate();

        }catch(Exception e){

            System.out.println(e);

        }finally{

            mysql.closeConnection(conn);

        }

    }
    public boolean checkUser(UserData_Signup user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where email = ? or username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
           System.out.print(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

}
 

