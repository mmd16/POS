package customerData;

import java.util.ArrayList;

import exception.ExCustomerNotFound;

public class Customer implements Comparable<Customer> {
	
	private String userName;
	private char sex;
	private String membership;
	
	public Customer(String userName, char sex, String membership) {
		this.userName = userName;
		this.sex = sex;
		this.membership = membership;
	}
	
    public static Customer searchCustomer(ArrayList<Customer> customerList, String userName) throws ExCustomerNotFound {
        for (Customer c : customerList) {
            if(c.getUserName().equals(userName))
                return c;
        }
        throw new ExCustomerNotFound();
    }

	public String getUserName() {
		return userName;
	}
	
	
	public String distinguishSex() {
		String title = "";
		if (sex == 'M')
			title = "Mr.";
		else if (sex == 'F')
			title = "Ms.";
		return title;
	}
	
    @Override
    public int compareTo(Customer another) {
        return this.getUserName().compareTo(another.getUserName());
    }
}
