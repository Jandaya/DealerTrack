/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealertrack2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Joseph
 */
public class DealerTrack {
    
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel car;
    
    public DealerTrack(){
        initGUI();
    }
    
    private void initGUI(){
        mainFrame = new JFrame("DealerTrack");
        mainFrame.setSize(640,480);
        mainFrame.setLayout(new GridLayout(3,1));
        
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        //panel.setSize(640,480);
        panel.setLayout(null);
        
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
       
        statusLabel.setSize(350,100);
        
        // to close the main window
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        buildMap();
        panel.add(car);
        
        mainFrame.setContentPane(panel);
        //mainFrame.add(car);
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
       
    }
    class DragMouseAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
    private void buildMap(){
        car = new JLabel();
        car.setSize(30, 40);
        car.setLocation(0, 0);
        car.setText("test");
        
        ImageIcon carImage = new ImageIcon(getClass().getResource("/dealertrack2/car1.png"));
        Image img = carImage.getImage() ;  
        Image newimg = img.getScaledInstance(30, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
        carImage = new ImageIcon( newimg );
        
        // car.setAlignmentX(1000);
        MouseListener listener = new DragMouseAdapter();
        //car.addMouseListener(listener);
        car.setTransferHandler(new TransferHandler("icon"));
        car.setIcon(carImage);
        car.setVisible(true);
        
        
        car.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                car.setText("Mouse Pressed: (" + e.getX() + ", " + e.getY() + ")");
                
            }
            public void mouseReleased(MouseEvent e){
  
                car.setText("Mouse Released: (" + e.getX() + ", " + e.getY() + ")");
                car.setLocation(e.getX(), e.getY());
                
            }
            public void mouseExited(MouseEvent e){

            }
        });
        

    }
    
    
    public static void main(String[] args){
        DealerTrack dealerTrack = new DealerTrack();
    }
    
}
