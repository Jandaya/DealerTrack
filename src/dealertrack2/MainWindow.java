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
import java.awt.Dimension;
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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    final int NUM_CARS = 33;
    private boolean isTrue = false;
    private String  stock, year, make, model, color, VIN, price;
    private int carid;
    private int hasPicture;
    private int lotCount=0;
    
    private int carCount, numCars = 0;
    
    private Car car = new Car();
    private List<Car> carList = new ArrayList<Car>();
    
    private List<JButton> spotList = new ArrayList<JButton>();
    private ImageIcon carImageRight, carImageLeft, carImageUp, carImageDown;
    private ImageIcon carFoundRight, carFoundLeft, carFoundUp, carFoundDown;
    
// for the file chooser
    private JFileChooser fc = new JFileChooser();
    public File thumbnailFile;
    private String selectedFile;
    
    public VehicleInfo vehicleInfo = new VehicleInfo();
    
    
    
    public MainWindow() throws SQLException {
        
        setConnection();
        setEmptyCars();
        setCarImages();
       
        setBackground2();
        initComponents();
        buildJpanels();
        setToolTips();

        
        MouseListener listener = new DragMouseAdapter();
        //jLabel1.addMouseListener(listener);

        
        setCarIcons();
        buildLot();
        buildLot2();
        buildLot3();
        buildLot4();
        buildLot5();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setEmptyCars() throws SQLException{
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResult = null;

        String user = "root";
        String pass = "";
        //carList.clear();
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            while(numCars < NUM_CARS){
                myStatement = myConnection.createStatement();
                myStatement.executeUpdate("INSERT INTO car VALUES (" + numCars + ", '0', 'none', 1900, 'none', 'none', 'none', 0, false);");

                    car = new Car();
                    //String tempCarID = Integer.toString(numCars);

                    car.setCarID(numCars);
                    car.setStock("0");
                    car.setYear(0);
                    car.setMake("none");
                    car.setModel("none");
                    car.setColor("none");
                    car.setVIN("none");
                    car.setPrice(0);
                    car.setHasPicture(0);
                    carList.add(car);
                    numCars++;
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
    public void buildJpanels(){
        jPanel1.setBackground(Color.BLUE);
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/carDown.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        //jLabel1.setIcon(carImage);

    }
    
    public void setCarImages(){
        ImageIcon carImage;
        Image img, newimg;
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carDown.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImageDown = new ImageIcon( newimg );
        
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carDown.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImageUp = new ImageIcon( newimg );
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carLeft.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(40, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImageLeft = new ImageIcon( newimg );
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carRight.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(40, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImageRight = new ImageIcon( newimg );
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carFoundRight.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(43, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carFoundRight = new ImageIcon( newimg );
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carFoundLeft.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(43, 40,  java.awt.Image.SCALE_SMOOTH ) ;
        carFoundLeft = new ImageIcon( newimg );
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carFoundDown.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(40, 43,  java.awt.Image.SCALE_SMOOTH ) ;
        carFoundDown = new ImageIcon( newimg );
        
        
        carImage = new ImageIcon(getClass().getResource("/dealertrack2/carFoundUp.png"));
        img = carImage.getImage() ;  
        newimg = img.getScaledInstance(40, 43,  java.awt.Image.SCALE_SMOOTH ) ;
        carFoundUp = new ImageIcon( newimg );
    }
    
    public void buildLot(){
        lotPanel1.setLayout(new GridBagLayout());
        lotPanel1.setSize(20,220);
        lotPanel1.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        carCount = 0;
        for (carCount = 0; carCount < 10; carCount++){
            gbc.fill = GridBagConstraints.VERTICAL;
            
            gbc.gridx = 0;
            gbc.gridy = carCount;
            System.out.println("asdfL " + carCount);
            JButton spot1 = new JButton();
            if(!(carList.get(lotCount).getStock().equals("0")))
            spot1.setIcon(carImageRight);
            spot1.setPreferredSize(new Dimension(48, 23));
            
            //spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           spot1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                    System.out.println("pressed: " + a);
                }
            });
            spotList.add(spot1);
            lotPanel1.add(spot1,gbc);
            lotCount++;
        }
        /*
        int j = 0;
        Iterator ia = spotList.iterator();
        carCount = 0;
        while(ia.hasNext()){
        
            spotList.get(j).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                    System.out.println("pressed: " + a);
                }
            });
            //carCount++;
            ia.next();
            j++;
            
        }*/
    }
    
    public void buildLot2(){
        lotPanel2.setLayout(new GridBagLayout());
        lotPanel2.setSize(20,220);
        lotPanel2.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        carCount = 0;
        for (carCount = 0; carCount < 7; carCount++){
            gbc.fill = GridBagConstraints.VERTICAL;
            
            gbc.gridx = 0;
            gbc.gridy = carCount;
            JButton spot1 = new JButton();
            if(!(carList.get(lotCount).getStock().equals("0")))
            spot1.setIcon(carImageLeft);
            spot1.setPreferredSize(new Dimension(48, 23));
            
            //spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           spot1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                }
            });
            spotList.add(spot1);
            lotPanel2.add(spot1,gbc);
            lotCount++;
        }
    }
    
    public void buildLot3(){
        lotPanel3.setLayout(new GridBagLayout());
        lotPanel3.setSize(20,220);
        lotPanel3.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        carCount = 0;
        for (carCount = 0; carCount < 6; carCount++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            gbc.gridx = carCount;
            gbc.gridy = 0;
            JButton spot1 = new JButton();
            if(!(carList.get(lotCount).getStock().equals("0")))
            spot1.setIcon(carImageDown);
            spot1.setPreferredSize(new Dimension(23, 48));
            
            //spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           spot1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                }
            });
            spotList.add(spot1);
            lotPanel3.add(spot1,gbc);
            lotCount++;
        }
    }
    
    public void buildLot4(){
        lotPanel4.setLayout(new GridBagLayout());
        lotPanel4.setSize(20,220);
        lotPanel4.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        carCount = 0;
        for (carCount = 0; carCount < 7; carCount++){
            gbc.fill = GridBagConstraints.VERTICAL;
            
            gbc.gridx = 0;
            gbc.gridy = carCount;
            System.out.println("asdfL " + carCount);
            JButton spot1 = new JButton();
            if(!(carList.get(lotCount).getStock().equals("0")))
            spot1.setIcon(carImageRight);
            spot1.setPreferredSize(new Dimension(48, 23));
            
            //spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           spot1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                }
            });
            spotList.add(spot1);
            lotPanel4.add(spot1,gbc);
            lotCount++;
        }
    }
    public void buildLot5(){
        lotPanel5.setLayout(new GridBagLayout());
        lotPanel5.setSize(20,220);
        lotPanel5.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        carCount = 0;
        for (carCount = 0; carCount < 3; carCount++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            gbc.gridx = carCount;
            gbc.gridy = 0;
            JButton spot1 = new JButton();
            if(!(carList.get(lotCount).getStock().equals("0")))
            spot1.setIcon(carImageDown);
            spot1.setPreferredSize(new Dimension(23, 48));
            
            //spot1.setSize(50,25);
            String s = Integer.toString(carCount);
           spot1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int a = spotList.indexOf(e.getSource());
                    
                    defaultActionPerformed();
                    setTextFields2(a);
                    
                }
            });
            spotList.add(spot1);
            lotPanel5.add(spot1,gbc);
            lotCount++;
        }
    }
    
    
    
    class DragMouseAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            
            // car image
            ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/carUp.png"));
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
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        dealerTrackLabel = new javax.swing.JLabel();
        resetMapButton = new javax.swing.JButton();
        lotPanel3 = new javax.swing.JPanel();
        lotPanel1 = new javax.swing.JPanel();
        lotPanel2 = new javax.swing.JPanel();
        lotPanel4 = new javax.swing.JPanel();
        lotPanel5 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DealerTrack");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Vehicle info");
        jInternalFrame1.setVisible(false);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchField.setForeground(new java.awt.Color(153, 153, 153));
        searchField.setText("Enter Stock #");
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchFieldMousePressed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        dealerTrackLabel.setFont(new java.awt.Font("Antique Olive Nord D", 3, 14)); // NOI18N
        dealerTrackLabel.setText("Dealer Track");

        resetMapButton.setText("Reset Map");
        resetMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMapButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(dealerTrackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addGap(18, 18, 18)
                .addComponent(resetMapButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerTrackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(resetMapButton))
                .addGap(29, 29, 29))
        );

        lotPanel3.setOpaque(false);

        javax.swing.GroupLayout lotPanel3Layout = new javax.swing.GroupLayout(lotPanel3);
        lotPanel3.setLayout(lotPanel3Layout);
        lotPanel3Layout.setHorizontalGroup(
            lotPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        lotPanel3Layout.setVerticalGroup(
            lotPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lotPanel1Layout = new javax.swing.GroupLayout(lotPanel1);
        lotPanel1.setLayout(lotPanel1Layout);
        lotPanel1Layout.setHorizontalGroup(
            lotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        lotPanel1Layout.setVerticalGroup(
            lotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lotPanel2Layout = new javax.swing.GroupLayout(lotPanel2);
        lotPanel2.setLayout(lotPanel2Layout);
        lotPanel2Layout.setHorizontalGroup(
            lotPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        lotPanel2Layout.setVerticalGroup(
            lotPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lotPanel4Layout = new javax.swing.GroupLayout(lotPanel4);
        lotPanel4.setLayout(lotPanel4Layout);
        lotPanel4Layout.setHorizontalGroup(
            lotPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        lotPanel4Layout.setVerticalGroup(
            lotPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
        );

        lotPanel5.setOpaque(false);

        javax.swing.GroupLayout lotPanel5Layout = new javax.swing.GroupLayout(lotPanel5);
        lotPanel5.setLayout(lotPanel5Layout);
        lotPanel5Layout.setHorizontalGroup(
            lotPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        lotPanel5Layout.setVerticalGroup(
            lotPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(960, 960, 960)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(lotPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lotPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lotPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lotPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344)
                        .addComponent(lotPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(896, 896, 896)
                        .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lotPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lotPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lotPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(lotPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(56, 56, 56)
                                .addComponent(lotPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String search = searchField.getText();
        int c = 0;
        while(c < NUM_CARS){
            if (search.equals(carList.get(c).getStock()) && (c >= 0 && c <= 9)){
                spotList.get(c).setIcon(carFoundRight);
                setTextFields2(c);
            }
            else if (search.equals(carList.get(c).getStock()) && (c >= 10 && c <= 16)){
                spotList.get(c).setIcon(carFoundLeft);
                setTextFields2(c);
            }
            
            else if (search.equals(carList.get(c).getStock()) && (c >= 17 && c <= 22)){
                spotList.get(c).setIcon(carFoundDown);
                setTextFields2(c);
            }
            else if (search.equals(carList.get(c).getStock()) && (c >= 23 && c <= 29)){
                spotList.get(c).setIcon(carFoundRight);
                setTextFields2(c);
            }
            else if (search.equals(carList.get(c).getStock()) && (c >= 30 && c <= 32)){
                spotList.get(c).setIcon(carFoundDown);
                setTextFields2(c);
            }
            c++;
        }
         
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        setTextFields(-1, null,0,null,null,null,null,0, 0);
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
        /*try {
            // TODO add your handling code here:
            updateQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_submitButtonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void searchFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldMousePressed
        searchField.setText(null);
    }//GEN-LAST:event_searchFieldMousePressed

    private void resetMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMapButtonActionPerformed
        try {
            setConnection();
            setCarIcons();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resetMapButtonActionPerformed

    
    
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
    
    
    public void defaultActionPerformed()
    {
        try {
            setConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        errorLabel.setText(null);
        thumbnailImage.setIcon(null);
        vehicleInfo.clearErrorLabel(true);
        vehicleInfo.setVisible(true);
        
        
    }
    public void initLists( ){
        
    }
    
    public void clearLists(){
        
        
    }
    
    public void setToolTips(){
        Iterator ia = spotList.iterator();
        int i = 0;
        
        while(ia.hasNext()){
            spotList.get(i).setToolTipText("Stock: " + carList.get(i).getStock());
            
            i++;
            ia.next();
        }
    }
    
    private void setTextFields(int id, String sl, int yl, String mal, String mol, String cl, String vl, int p, int hp)
    {
        vehicleInfo.setStock(sl);
        vehicleInfo.setYear(yl);
        vehicleInfo.setMake(mal);
        vehicleInfo.setModel(mol);
        vehicleInfo.setColor(cl);
        vehicleInfo.setVIN(vl);
        vehicleInfo.setPrice(p);
        vehicleInfo.setCarID(id);
        vehicleInfo.setHasPicture(hp);
        
        
        vehicleInfo.setStock(sl);
    }
    
    private void setTextFields2(int id){
        vehicleInfo.setStock(carList.get(id).getStock());
        vehicleInfo.setYear(carList.get(id).getYear());
        vehicleInfo.setMake(carList.get(id).getMake());
        vehicleInfo.setModel(carList.get(id).getModel());
        vehicleInfo.setColor(carList.get(id).getColor());
        vehicleInfo.setVIN(carList.get(id).getVIN());
        vehicleInfo.setPrice(carList.get(id).getPrice());
        vehicleInfo.setCarID(id);
        vehicleInfo.setHasPicture(carList.get(id).getHasPicture());
        vehicleInfo.setThumbnail(carList.get(id).getStock());
        vehicleInfo.setVisible(true);
    }
    
    public void setCarIcons(){
        Iterator ia = spotList.iterator();
        int i = 0;
        
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/carDown.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        
        while(ia.hasNext()){
        
            if (!(carList.get(i).getStock().equals("0")) && (i >= 0 && i <= 9))
                spotList.get(i).setIcon(carImageRight);
            else if (!(carList.get(i).getStock().equals("0")) && (i >= 10 && i <= 16))
                spotList.get(i).setIcon(carImageLeft);
            else if (!(carList.get(i).getStock().equals("0")) && (i >= 17 && i <= 22))
                spotList.get(i).setIcon(carImageDown);
            else if (!(carList.get(i).getStock().equals("0")) && (i >= 23 && i <= 29))
                spotList.get(i).setIcon(carImageRight);
            else if (!(carList.get(i).getStock().equals("0")) && (i >= 30 && i <= 32))
                spotList.get(i).setIcon(carImageDown);
            else
                spotList.get(i).setIcon(null);

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
            if(!(carid == carList.get(i).getCarID())){
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
                myStatement.executeUpdate("update car set stock = "+ temp1 +", year = " + temp2 + ", make = '" + temp3 + "', model = '" + temp4 + "', color = '" + temp5 + "', vin = '" + temp6+ "' where carid = "+ carid);
            
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


                    year = myResult.getString("year");
                    //yearList.add(Integer.parseInt(year));
                    int year2;
                    year2 = Integer.parseInt(year);

                    make = myResult.getString("make");

                    model = myResult.getString("model");

                    color = myResult.getString("color");

                    VIN = myResult.getString("vin");
                    price = myResult.getString("price");
                    int priceTemp = Integer.parseInt(price);
                
                    hasPicture = myResult.getInt("haspicture");
                

                    // test for car obj
                    car = new Car();
                    //car.setCarID(carid);
                    car.setStock(stock);
                    car.setYear(year2);
                    car.setMake(make);
                    car.setModel(model);
                    car.setColor(color);
                    car.setVIN(VIN);
                    car.setPrice(priceTemp);
                    car.setHasPicture(hasPicture);
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
        numCars = 0;
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DealerTrack", user, pass);
            
            myStatement = myConnection.createStatement();
            
            
            myResult = myStatement.executeQuery("select * from car");
            while(myResult.next()){
                carid = myResult.getInt("carid");
                //int caridtemp;
                //caridtemp = Integer.parseInt(carid);
                
                
                stock = myResult.getString("stock");

                year = myResult.getString("year");
                int year2;
                year2 = Integer.parseInt(year);
                
                make = myResult.getString("make");
                
                model = myResult.getString("model");
                
                color = myResult.getString("color");
                
                VIN = myResult.getString("vin");
                
                price = myResult.getString("price");
                int priceTemp = Integer.parseInt(price);
                
                hasPicture = myResult.getInt("haspicture");
                
                // test for car obj
                car = new Car();
                car.setCarID(carid);
                car.setStock(stock);
                car.setYear(year2);
                car.setMake(make);
                car.setModel(model);
                car.setColor(color);
                car.setVIN(VIN);
                car.setPrice(priceTemp);
                car.setHasPicture(hasPicture);
                carList.add(car);
                numCars++;
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
    private javax.swing.JLabel dealerTrackLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JPanel lotPanel1;
    private javax.swing.JPanel lotPanel2;
    private javax.swing.JPanel lotPanel3;
    private javax.swing.JPanel lotPanel4;
    private javax.swing.JPanel lotPanel5;
    private javax.swing.JButton resetMapButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton thumbnailAddButton;
    private javax.swing.JLabel thumbnailImage;
    // End of variables declaration//GEN-END:variables
}

