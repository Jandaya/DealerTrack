/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealertrack2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 *
 * @author Joseph
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    private boolean isTrue = false;
    private String carid, stock, year, make, model, color, VIN;
    private int carID;
    
    int carCount;
    
    Car car = new Car();
    List<Car> carList = new ArrayList<Car>();
    
    List<JLabel> spotList = new ArrayList<JLabel>();
    // for the file chooser
    
    JFileChooser fc = new JFileChooser();
    public File thumbnailFile;
    String selectedFile;
    
    VehicleInfo vehicleInfo = new VehicleInfo();
    
    
    
    public MainWindow() throws SQLException {
        
        setConnection();
        int i = 0;
        Iterator ia = carList.iterator();
        while(ia.hasNext()){
        
            System.out.println("\n\nWriting: " + carList.get(i).getStock());
            ia.next();
            i++;
            
        }
        setBackground2();
        initComponents();
        buildJpanels();

        jButton1.setFocusable(false);
        jButton2.setFocusable(false);
        
        MouseListener listener = new DragMouseAdapter();
        jLabel1.addMouseListener(listener);

        jLabel1.setTransferHandler(new TransferHandler("icon"));
        jButton1.setTransferHandler(new TransferHandler("icon"));
        jButton2.setTransferHandler(new TransferHandler("icon"));
        
        setCarIcons();
        buildLot();
        vehicleInfo.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                vehicleInfo.setVisible(false);
            }
        });
        //vehicleInfo.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    private void buildJpanels(){
        jPanel1.setBackground(Color.BLUE);
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car1.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        jLabel1.setIcon(carImage);

    }
    
    public void buildLot(){
        lot1Panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        
        
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car11.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(40,30,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        carCount = 0;
        for (carCount = 0; carCount < 2; carCount++){
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.gridx = 0;
            gbc.gridy = carCount;
            gbc.gridheight = 1;
            
            System.out.println("asdfL " + carCount);
            JLabel spot1 = new JLabel();
            spot1.setIcon(carImage);
            spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           /* spot1.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    jLabel2.setText("pressed: " + s);
                    //setTextFields(carList.get(carCount).getStock(), carList.get(carCount).getYear(), carList.get(carCount).getMake(), carList.get(carCount).getModel(), carList.get(carCount).getColor(), carList.get(carCount).getVIN());
                    //System.out.println(carList.get(carCount).getStock());
                    
                }
            });*/
            spotList.add(spot1);
            lot1Panel.add(spot1,gbc);
            
        }
        
        int j = 0;
        Iterator ia = spotList.iterator();
        carCount = 0;
        while(ia.hasNext()){
        
            spotList.get(j).addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    System.out.println("pressed: " + carCount++);
                }
            });
            //carCount++;
            ia.next();
            j++;
            
        }
  
            /*
            JButton spot1 = new JButton();
            spot1.setSize(50,25);
            JButton spot2 = new JButton();
            spot2.setSize(50,25);
            lot1Panel.add(spot1);
            lot1Panel.add(spot2);
            */
    }
    
    private void spot1MousePressed(MouseEvent e, int count){
        
        
    }
    
    class DragMouseAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            
            // car image
            ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car1.png"));
            Image img = carImage.getImage() ;  
            Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
            carImage = new ImageIcon( newimg );
            handler.setDragImage(newimg);
            //c.setEnabled(true);
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
    
   

    
    public void setBackground2(){
        
        ImageIcon backgroundImg = new ImageIcon(getClass().getResource("/dealertrack2/overhead view.jpg"));
        Image img = backgroundImg.getImage();  
        Image newimg = img.getScaledInstance(1380,660, java.awt.Image.SCALE_SMOOTH ) ;  
        backgroundImg = new ImageIcon( newimg );
        
        
        setLayout(new BorderLayout());
        setContentPane(new JLabel(backgroundImg));
        setLayout(new FlowLayout());

        // Just for refresh :) Not optional!
        setSize(924,950);
        setSize(925,951);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        thumbnailImage = new javax.swing.JLabel();
        thumbnailAddButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lot1Panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DealerTrack");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Vehicle info");
        jInternalFrame1.setVisible(true);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Stock");

        jLabel4.setText("Make");

        jLabel5.setText("Model");

        jLabel6.setText("Color");

        jLabel7.setText("VIN");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jTextField6.setToolTipText("");

        jLabel8.setText("Year");

        thumbnailAddButton.setText("Add Photo");
        thumbnailAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thumbnailAddButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(thumbnailImage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(thumbnailAddButton)
                                .addGap(18, 18, 18)
                                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errorLabel)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(jTextField2)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(jTextField6)))))
                .addGap(63, 63, 63))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(thumbnailImage)
                .addGap(32, 32, 32)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thumbnailAddButton)
                    .addComponent(submitButton))
                .addGap(18, 18, 18)
                .addComponent(clearButton)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dealertrack2/car1.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(33, 9));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        searchField.setForeground(new java.awt.Color(153, 153, 153));
        searchField.setText("Enter Stock #");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addGap(92, 92, 92)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37))
        );

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lot1Panel.setOpaque(false);

        javax.swing.GroupLayout lot1PanelLayout = new javax.swing.GroupLayout(lot1Panel);
        lot1Panel.setLayout(lot1PanelLayout);
        lot1PanelLayout.setHorizontalGroup(
            lot1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );
        lot1PanelLayout.setVerticalGroup(
            lot1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lot1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(636, 636, 636)
                .addComponent(jInternalFrame1)
                .addGap(64, 64, 64))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(lot1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        /*
        if(isTrue){
            isTrue = false;
            jInternalFrame1.setVisible(false);
        }
        else
        {
            isTrue = true;
            
            jInternalFrame1.setVisible(true);
        }
        */
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        // TODO add your handling code here:
        jLabel1.setText("Released");
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        errorLabel.setText(null);
        isTrue = true;
            carID = 1;
            carid = "1";
   
            thumbnailImage.setIcon(null);
        try {
            setConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
            setTextFields(1, carList.get(1).getStock(), carList.get(1).getYear(), carList.get(1).getMake(), carList.get(1).getModel(), carList.get(1).getColor(), carList.get(1).getVIN());
            vehicleInfo.clearErrorLabel(true);
            vehicleInfo.setThumbnail(carList.get(1).getStock());
            jInternalFrame1.setVisible(true);
            vehicleInfo.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String search = searchField.getText();
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car3.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(40, 43,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        
        if (search.equals(carList.get(0).getStock())){
            jButton1.setIcon(carImage);
            setTextFields(0, carList.get(0).getStock(), carList.get(0).getYear(), carList.get(0).getMake(), carList.get(0).getModel(), carList.get(0).getColor(), carList.get(0).getVIN());
           
        }
        else if (search.equals(carList.get(1).getStock())){
            jButton2.setIcon(carImage);
            setTextFields(0, carList.get(1).getStock(), carList.get(1).getYear(), carList.get(1).getMake(), carList.get(1).getModel(), carList.get(1).getColor(), carList.get(1).getVIN());
           
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        setTextFields(-1, null,0,null,null,null,null);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void thumbnailAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thumbnailAddButtonActionPerformed
        // TODO add your handling code here:
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

        }
    }//GEN-LAST:event_thumbnailAddButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            updateQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        errorLabel.setText(null);
        isTrue = true;
        carID = 0;
        carid = "0";
        /* jTextField1.setText(stockList.get(0));
        jTextField2.setText(Integer.toString(yearList.get(0)));
        jTextField3.setText(makeList.get(0));
        jTextField4.setText(modelList.get(0));
        jTextField5.setText(colorList.get(0));
        jTextField6.setText(vinList.get(0));
        */
        thumbnailImage.setIcon(null);

        try {
            setConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        setTextFields(0, carList.get(0).getStock(), carList.get(0).getYear(), carList.get(0).getMake(), carList.get(0).getModel(), carList.get(0).getColor(), carList.get(0).getVIN());
        vehicleInfo.clearErrorLabel(true);
        vehicleInfo.setThumbnail(carList.get(0).getStock());
        jInternalFrame1.setVisible(true);
        vehicleInfo.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void initLists( ){
        
    }
    
    public void clearLists(){
        
        
    }
    private void setTextFields(int id, String sl, int yl, String mal, String mol, String cl, String vl)
    {
        jTextField1.setText(sl);
        jTextField2.setText(Integer.toString(yl));
        jTextField3.setText(mal);
        jTextField4.setText(mol);
        jTextField5.setText(cl);
        jTextField6.setText(vl);
        
        vehicleInfo.setStock(sl);
        vehicleInfo.setYear(yl);
        vehicleInfo.setMake(mal);
        vehicleInfo.setModel(mol);
        vehicleInfo.setColor(cl);
        vehicleInfo.setVIN(vl);
        
        vehicleInfo.setCarID(id);
    }
    
    public void setCarIcons(){
        Iterator ia = carList.iterator();
        int i = 0;
        
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car1.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        
        while(ia.hasNext()){
        
            if (carList.get(0).getCarID().equals("0") && !(carList.get(0).getStock().equals("none")))
            {
                jButton1.setIcon(carImage);
            }
            if (carList.get(1).getCarID().equals("1") && !(carList.get(1).getStock().equals("none")))
            {
                jButton2.setIcon(carImage);
            }
            ia.next();
            i++;
            
        }
    }
    
    public boolean checkStock(String stockNo){
        Iterator ib = carList.iterator();
        int i = 0;
        
        while(ib.hasNext()){
            /*
            if(carList.get(i).getStock() == null)
            {
                return false;
            }*/
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
            temp1 = jTextField1.getText();
            if (temp1.isEmpty())
            {
                System.out.println("in null");
                temp1 = "0";
            }
            temp2 = jTextField2.getText();
            temp3 = jTextField3.getText();
            temp4 = jTextField4.getText();
            temp5 = jTextField5.getText();
            temp6 = jTextField6.getText();
            
            
            //myStatement.executeUpdate("update car set year = 2000 where carid = "+carID);
            
            if (checkStock(temp1)){
                myStatement.executeUpdate("update car set stock = "+ temp1 +", year = " + temp2 + ", make = '" + temp3 + "', model = '" + temp4 + "', color = '" + temp5 + "', vin = '" + temp6+ "' where carid = "+ carID);
            

                //myStatement.executeUpdate("update car set stock = " + temp1 + " where carid = "+carID);
                // myStatement.executeUpdate("update car set make = '" + temp3 + "' where carid = "+carID);
                 /*
                 myStatement.executeUpdate("update car set model = " + temp4 + " where carid = "+carID);
                 myStatement.executeUpdate("update car set color = " + temp5 + " where carid = "+carID);
                 myStatement.executeUpdate("update car set vin = " + temp6 + " where carid = "+carID);
                */
                clearLists();
                myResult = myStatement.executeQuery("select * from car");

                // clear car
                carList.clear();
                while(myResult.next()){
                    
                    /*
                    carid = myResult.getString("carid");
                    int caridtemp;
                    caridtemp = Integer.parseInt(carid);
                    */
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
                    //car.setCarID(carid);
                    car.setStock(stock);
                    car.setYear(year2);
                    car.setMake(make);
                    car.setModel(model);
                    car.setColor(color);
                    car.setVIN(VIN);
                    carList.add(car);

                }
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
    
    private void setConnection() throws SQLException{
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
                carid = myResult.getString("carid");
                int caridtemp;
                caridtemp = Integer.parseInt(carid);
                
                
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
                car.setCarID(carid);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPanel lot1Panel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton thumbnailAddButton;
    private javax.swing.JLabel thumbnailImage;
    // End of variables declaration//GEN-END:variables
}

