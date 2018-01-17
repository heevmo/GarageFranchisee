package view.franchisee.customersvehicles;

import java.util.EventObject;

/**
 *
 * @author Alex
 */
public class VehicleEvent extends EventObject {
	
    private String regNo;
    private String engSer;
    private String make;
    private String chNo;
    private String model;
    private String colour;
    private int Vehicle_ID;

    public int getVehicle_ID() {
		return Vehicle_ID;
	}

	public VehicleEvent(Object object, String regNo, String engSer, String make, String chNo, String model, String colour) {
        super(object);
        
        this.regNo = regNo;
        this.engSer = engSer;
        this.make = make;
        this.chNo = chNo;
        this.model = model;
        this.colour = colour;
    }
    
    public VehicleEvent(Object object, String regNo, String engSer, String make, String chNo, String model, String colour, int Vehicle_ID) {
        
        this(object, regNo, engSer, make, chNo, model, colour);
        
        this.Vehicle_ID = Vehicle_ID;
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
}
