package dao;
import database.MySqlConnector;
import model.UserData;
import java.sql.*;


public class UserDao {
    MySqlConnector mysql = new MySqlConnector();
    
    public void createUser(UserData user){
        Connection conn = mysql.openConnection();
        String sql = "insert into users( username, email, password) values (?,?,?)";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUsername());
              pstm.setString(2, user.getEmail());
                pstm.setString(3, user.getPassword());
                pstm.executeUpdate();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            mysql.closeConnection(conn);
        }
    }
    
    public boolean checkUser(UserData user){
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
