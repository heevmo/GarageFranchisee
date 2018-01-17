package view.franchisee.jobs;

public class JobFieldsEvent {
   
    private String customer;
    private String telephone;
    private String model;
    private String make;
    private String registration;

	public JobFieldsEvent(String customer, String telephone, String model, String make, String registration) {
    	this.customer = customer;
    	this.telephone = telephone;
    	this.model = model;
    	this.make = make;
    	this.registration = registration;
    }

	public String getCustomer() {
		return customer;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}
    
    public String getRegistration() {
		return registration;
	}
    
}
