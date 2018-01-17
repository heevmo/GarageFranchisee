
package model;

/**
 *
 * @author Alex
 */
public class Customer {
	
	private static int count = 1;
	
	private int id;
    private String name;
    private String customerClass;
    private String address;
    private String postcode;
    private String telephone;
    private String email;
    private String fax;
    
    /**
     * 
     */
	public Customer(String name, String postcode , String address, 
			String customerClass, String telephone, String fax , String email) {
		this.name = name;
		this.customerClass = customerClass;
		this.telephone = telephone;
		this.address = address;
		this.postcode = postcode;
		this.email = email;
		this.fax = fax;
		this.id = count;
		count++;
	}
	
	/**
	 * 
	 */
	public Customer(int id, String name, String postcode, String address, 
			String customerClass , String telephone, String fax, String email) {
		this(name, postcode, address, customerClass, telephone, fax, email);
		this.id = id;
	}
	
    public static void setCount(int c) {
    	count = c;
    }

	public static int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}
	
	public boolean equals(Customer c) {
		String name1 = c.getName().replaceAll("\\s", "");
		String name2 = this.name.replaceAll("\\s", "");
		
		String p1 = c.getPostcode().replaceAll("\\s", "");
		String p2 = this.postcode.replaceAll("\\s", "");
		if (name1.equalsIgnoreCase(name2) && p1.equalsIgnoreCase(p2))
			return true;
		else {
			return false;
		}
	}
       
}
