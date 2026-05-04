/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
/**
 *
 * @author Nitro
 */
public class MySqlConnector implements Db {
   public Connection openConnection(){
       try{
           String username="root";
           String password="Sthaku!deep07";
           String database="hello";
           Connection connection;
           connection=DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/" + database,username,password
           );
           if (connection==null){
               System.out.println("Connection null");
           }else{
               System.out.println("Connection success");
           }
           return connection;
       }catch(Exception e) {
           System.out.println(e);
       }
       return null;
   }
    
}
