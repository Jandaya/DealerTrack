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
    private int carID;
    private int hasPicture;
    private int price;
    public Car(){
        carID = 0;
        stock = null;
        year = 0;
        make = null;
        model = null;
        color = null;
        VIN = null;
        hasPicture = 0;
    }
    
    public int getHasPicture(){
        return hasPicture;
    }
    
    public void setHasPicture(int p){
        hasPicture = p;
    }
    
    public void setCarID(int ci){
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
   
   public int getCarID(){
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
   
   public int getPrice(){
       return price;
   }
   
   public void setPrice(int p){
       price = p;
   }
}
