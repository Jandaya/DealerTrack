/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealertrack2;

/**
 *
 * @author Joseph
 */
public class Car {
    private String stock;
    private int year;
    private String make;
    private String model;
    private String color;
    private String VIN;
    private String carID;
    public Car(){
        carID = null;
        stock = null;
        year = 0;
        make = null;
        model = null;
        color = null;
        VIN = null;
        
    }
    
    public void setCarID(String ci){
        carID = ci;
    }
    
    public void setStock(String s){
        stock = s;
    }
    
   public void setYear(int y){
        year = y;
    }
   
   public void setMake(String m){
        make = m;
    }
   public void setModel(String m){
        model = m;
    }
   public void setColor(String c){
       color = c;
   }
   
   public void setVIN(String v){
       VIN = v;
   }
   
   public String getCarID(){
       return carID;
   }
   
   public String getStock()
   {
       return stock;
   }
   
   public int getYear(){
       return year;
   }
   
   public String getMake(){
       return make;
   }
   
   public String getModel(){
       return model;
   }
   
   public String getColor(){
       return color;
   }
   
   public String getVIN(){
       return VIN;
   }
}
