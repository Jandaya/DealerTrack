/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealertrack2;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 *
 * @author Joseph
 */
public class VehicleInfo extends javax.swing.JFrame {

    private JFileChooser fc = new JFileChooser();
    private File thumbnailFile;
    private String selectedFile;
    
    private int carID;
    private String stock, year, make, model, color, VIN, carid;
    
    Car car = new Car();
    List<Car> carList = new ArrayList<Car>();

    /**
     * Creates new form VehicleInfo
     */
    public VehicleInfo()throws SQLException {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        pullDatabase();
        //setPicture();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        stockLabel = new javax.swing.JLabel();
        stockTextField = new javax.swing.JTextField();
        yearLabel = new javax.swing.JLabel();
        yearTextField = new javax.swing.JTextField();
        makeLabel = new javax.swing.JLabel();
        makeTextField = new javax.swing.JTextField();
        modelLabel = new javax.swing.JLabel();
        modelTextField = new javax.swing.JTextField();
        colorLabel = new javax.swing.JLabel();
        colorTextField = new javax.swing.JTextField();
        vinLabel = new javax.swing.JLabel();
        vinTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        addPhotoButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();
        thumbnailImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vehicle Info");
        setPreferredSize(new java.awt.Dimension(350, 600));
        setResizable(false);

        stockLabel.setText("Stock");

        yearLabel.setText("Year");

        makeLabel.setText("Make");

        modelLabel.setText("Model");

        colorLabel.setText("Color");

        vinLabel.setText("VIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockLabel)
                    .addComponent(yearLabel)
                    .addComponent(makeLabel)
                    .addComponent(modelLabel)
                    .addComponent(colorLabel)
                    .addComponent(vinLabel))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockTextField)
                    .addComponent(yearTextField)
                    .addComponent(makeTextField)
                    .addComponent(modelTextField)
                    .addComponent(colorTextField)
                    .addComponent(vinTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockLabel)
                    .addComponent(stockTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearLabel)
                    .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeLabel)
                    .addComponent(makeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelLabel)
                    .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorLabel)
                    .addComponent(colorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vinLabel)
                    .addComponent(vinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addPhotoButton.setText("Add Photo");
        addPhotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPhotoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPhotoButton)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton)
                    .addComponent(clearButton))
                .addGap(18, 18, 18)
                .addComponent(addPhotoButton)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(errorLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(thumbnailImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thumbnailImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPhotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPhotoButtonActionPerformed
        
        int returnVal = fc.showOpenDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            thumbnailFile  = fc.getSelectedFile();
            selectedFile = thumbnailFile.getPath();
            System.out.println(selectedFile);
            
            ImageIcon thumbnailPic = new ImageIcon(selectedFile); //getClass().getResource(selectedFile));
            Image ti = thumbnailPic.getImage();
            Image thumbnail = ti.getScaledInstance(128, 96, Image.SCALE_SMOOTH);
            thumbnailPic = new ImageIcon(thumbnail);
            thumbnailImage.setIcon(thumbnailPic);
            
            
            
            
            
            Path src = Paths.get(selectedFile);
            //Path dst = Paths.get("C:/Users/Joseph/Desktop");
            //File dst = new File("C:\\Users\\Joseph\\Desktop\\file.jpg");
            File dst = new File(stock + ".jpg");
            System.out.println(dst.getPath().toString());
            //Path src, dst;
            try {
                Files.copy(src, dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(VehicleInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     
    }//GEN-LAST:event_addPhotoButtonActionPerformed

    
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        
        
        try {
            updateQuery();
            pullDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(VehicleInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        errorLabel.setText(null);
        stockTextField.setText(null);
        yearTextField.setText(null);
        makeTextField.setText(null);
        modelTextField.setText(null);
        colorTextField.setText(null);
        vinTextField.setText(null);
    }//GEN-LAST:event_clearButtonActionPerformed

    public void setThumbnail(String stockNo){
        String paths = stockNo + ".jpg";
        Path path = Paths.get(paths);
        if (path.equals(null))
            System.out.println("true");
        
        ImageIcon thumbnailPic = new ImageIcon(stockNo + ".jpg"); 
        Image ti = thumbnailPic.getImage();
        Image thumbnail = ti.getScaledInstance(128, 96, Image.SCALE_SMOOTH);
        thumbnailPic = new ImageIcon(thumbnail);
        thumbnailImage.setIcon(thumbnailPic);
    }
    
    public void setPicture() throws SQLException{
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResult = null;
        
        String user = "root";
        String pass = "";
        String filePath = "C:\\Users\\Joseph\\Desktop\\file.png";
        
        //carList.clear();
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            myStatement = myConnection.createStatement();
            
            String sql = "INSERT INTO picture (stock, photo) values(?,?)";
            PreparedStatement statement = myConnection.prepareStatement(sql);
            statement.setString(1, "78774");
            InputStream inputStream = new FileInputStream(new File(filePath));
            statement.setBlob(2, inputStream);
            
            int row = statement.executeUpdate();
            if(row > 0){
               System.out.println("inserted");
            }
            myConnection.close();
        }
         catch (Exception e){
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
    public void setStock(String s){
        stockTextField.setText(s);
        stock = s;
    }
    
   public void setYear(int y){
       String temp = Integer.toString(y);
        yearTextField.setText(temp);
    }
   
   public void setMake(String m){
        makeTextField.setText(m);
    }
   public void setModel(String m){
        modelTextField.setText(m);
    }
   public void setColor(String c){
       colorTextField.setText(c);
   }
   
   public void setVIN(String v){
       vinTextField.setText(v);
   }
   
   public void setCarID(int id){
       carID = id;
       carid = Integer.toString(carID);
   }
   
   
   public String getStock()
   {
       return stockTextField.getText();
   }
   
   public int getYear(){
       int temp = Integer.parseInt(yearTextField.getText());
       return temp;
   }
   
   public String getMake(){
       return makeTextField.getText();
   }
   
   public String getModel(){
       return modelTextField.getText();
   }
   
   public String getColor(){
       return colorTextField.getText();
   }
   
   public String getVIN(){
       return vinTextField.getText();
   }
   
   public int getCarID(){
       return carID;
   }
   
   public void clearErrorLabel(boolean x){
       if (x){
           errorLabel.setText(null);
       }
   }
    
    
    
    public boolean checkStock(String stockNo){
        
        Iterator ib = carList.iterator();
        int i = 0;
        
        while(ib.hasNext()){
            
            if(!(carid.equals(carList.get(i).getCarID()))){
                if(stockNo.equals(carList.get(i).getStock())){
                    return false;
                }
            }
            ib.next();
            i++;
        }
        return true;
    }
    

   
   private void pullDatabase() throws SQLException{
       Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResult = null;
        
        String user = "root";
        String pass = "";
        carList.clear();
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            myStatement = myConnection.createStatement();
            
            
            myResult = myStatement.executeQuery("select * from car");
            while(myResult.next()){
                String tempCarID;
                tempCarID = myResult.getString("carid");
                carID = Integer.parseInt(tempCarID);
                
                
                stock = myResult.getString("stock");
                //stockList.add(stock);
                
                
                
                year = myResult.getString("year");
                //yearList.add(Integer.parseInt(year));
                int year2;
                year2 = Integer.parseInt(year);
                
                make = myResult.getString("make");
                //makeList.add(make);
                
                model = myResult.getString("model");
                //modelList.add(model);
                
                color = myResult.getString("color");
                //colorList.add(color);
                
                VIN = myResult.getString("vin");
                //vinList.add(VIN);
                
                // test for car obj
                
                car = new Car();
                car.setCarID(tempCarID);
                car.setStock(stock);
                car.setYear(year2);
                car.setMake(make);
                car.setModel(model);
                car.setColor(color);
                car.setVIN(VIN);
                carList.add(car);
                
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
   
   
    private void updateQuery() throws SQLException{
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResult = null;
        
        String user = "root";
        String pass = "";
        String temp1, temp2, temp3, temp4, temp5, temp6;
        
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            myStatement = myConnection.createStatement();
            
            temp1 = stockTextField.getText();
            
            if (temp1.isEmpty())
            {
                System.out.println("in null");
                temp1 = "0";
            }
            
            temp2 = yearTextField.getText();
            temp3 = makeTextField.getText();
            temp4 = modelTextField.getText();
            temp5 = colorTextField.getText();
            temp6 = vinTextField.getText();
            
            
            if (checkStock(temp1)){
                errorLabel.setText(null);
                //myStatement.executeUpdate("update car set stock = "+ temp1 +", year = " + temp2 + ", make = '" + temp3 + "', model = '" + temp4 + "', color = '" + temp5 + "', vin = '" + temp6+ "' where carid = "+ carID);
                

              
                myResult = myStatement.executeQuery("select * from car");

                
            }
            else
            {
                errorLabel.setText("Stock number exists, please try again.");
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
   
   
   
   
    /**
     * @param args the command line arguments
     */
   /*
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VehicleInfo().setVisible(true);
            }
        });
    }
   */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPhotoButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JTextField colorTextField;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel makeLabel;
    private javax.swing.JTextField makeTextField;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JTextField modelTextField;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JTextField stockTextField;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel thumbnailImage;
    private javax.swing.JLabel vinLabel;
    private javax.swing.JTextField vinTextField;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
}