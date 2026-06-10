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
    MySqlConnector mysql=new MySqlConnector();

    public void createUser(UserData_Login user){  

        Connection conn = mysql.openConnection();

        String sql="insert into users(username,password) values (?,?)";

        try(PreparedStatement pstm= conn.prepareStatement(sql)){

            pstm.setString(1,user.getUsername());


            pstm.setString(2,user.getPassword());

            pstm.executeUpdate();

        }catch(Exception e){

            System.out.println(e);

        }finally{

            mysql.closeConnection(conn);

        }

    }
    public boolean checkUser(UserData_Login user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where username = ? or password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
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
 
    

