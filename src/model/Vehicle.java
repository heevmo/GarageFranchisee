package model;

/**
 *
 * @author Alex
 */
public class Vehicle {

    public static int count = 1;

    private int id;

    private String regNo;
    private String engSer;
    private String make;
    private String chNo;
    private String model;
    private String colour;

    public Vehicle(String regNo, String make, String model, 
            String engSer, String chNo, String colour) {
       
    this.regNo = regNo;
    this.engSer = engSer;
    this.make = make;
    this.chNo = chNo;
    this.model = model;
    this.colour = colour;
    this.id = count;
    count++;
    
    }
    
    public Vehicle(int id, String regNo, String make, String model, 
            String engSer, String chNo, String colour) {
    	 this(regNo, make, model, engSer, chNo, colour);
    	 
    	 this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getEngSer() {
        return engSer;
    }

    public String getMake() {
        return make;
    }

    public String getChNo() {
        return chNo;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }
    
    public String toString() {
    	return regNo + " " + model;
    }
    
    public static void setCount(int c) {
    	count = c;
    }
}
