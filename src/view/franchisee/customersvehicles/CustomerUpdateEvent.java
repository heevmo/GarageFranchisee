package view.franchisee.customersvehicles;

import java.util.EventObject;

public class CustomerUpdateEvent extends EventObject {
	
    private String name;
    private String postcode;
    private String address;
    private String customerClass;
    private String telephone;
    private String fax;
    private String email;
    private int Customer_ID;
	
	public int getCustomer_ID() {
		return Customer_ID;
	}

	public CustomerUpdateEvent(Object object, String name, String address, 
			String postcode, String telephone, String email, String fax, int Customer_ID) {
		super(object);
		
	    this.name = name;
	    this.postcode = postcode;
	    this.address = address;
	    this.telephone = telephone;
	    this.fax = fax;
	    this.email = email;
	    this.Customer_ID = Customer_ID;
	}

	public String getName() {
		return name;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getAddress() {
		return address;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

}
