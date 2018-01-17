package view.franchisee.customersvehicles;

import java.util.EventObject;

public class CustomerEvent extends EventObject {
	
    private String name;
    private String postcode;
    private String address;
    private String customerClass;
    private String telephone;
    private String fax;
    private String email;
    private int Customer_ID;
	
	public CustomerEvent(Object object, String name, String postcode, String address,
			String customerClass, String telephone, String fax, String email) {
		
		super(object);
		
	    this.name = name;
	    this.postcode = postcode;
	    this.address = address;
	    this.customerClass = customerClass;
	    this.telephone = telephone;
	    this.fax = fax;
	    this.email = email;
	}
	
	// use to pass customer update events
	public CustomerEvent(Object object, String name, String postcode, String address,
			String customerClass, String telephone, String fax, String email, int Customer_ID) {
		this(object, name, postcode, address, customerClass, telephone, fax, email);
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
	
	public int getCustomer_ID() {
		return Customer_ID;
	}
}
