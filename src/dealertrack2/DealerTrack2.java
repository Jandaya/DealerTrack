import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package dealertrack2;

/**
 *
 * @author Joseph
 */
public class DealerTrack2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResult = null;
        
        String user = "root";
        String pass = "";
        
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            myStatement = myConnection.createStatement();
            
            myResult = myStatement.executeQuery("select * from keylog");
            while(myResult.next()){
                System.out.println("------------------------------------");
                System.out.println("Stock #: " + myResult.getString("stock"));
                System.out.println("VIN: " + myResult.getString("vin"));
                System.out.println("Year: " + myResult.getString("year"));
                System.out.println("Make: " + myResult.getString("make"));
                System.out.println("Model: " + myResult.getString("model"));
                System.out.println("Color: " + myResult.getString("color"));
                System.out.println("Price: " + myResult.getString("price"));
                System.out.println("Location: " + myResult.getString("location"));
                System.out.println("Sales status: " + myResult.getString("salesstatus"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            if(myResult != null){
                myResult.close();
            }
            if(myStatement != null){
                myStatement.close();
            }
            if(myConnection != null){
                myConnection.close();
            }
            
        }
        
        
    }
    
}
