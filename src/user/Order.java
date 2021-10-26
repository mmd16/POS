package user;



import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import product.Product;

public class Order {
	private String productName;
	private LocalDate orderDate;
	private String userName;
	private User user;
	private double price;
	private int deliveryDays;
	
	public Order (User user, Product product, LocalDate orderDate, int deliveryDays){
		// add new parameters
		this.userName = user.getUserName();	
		this.productName = product.getName();
		this.orderDate = orderDate;				
		this.deliveryDays = deliveryDays;
		this.user = user;		
	}		
	
	// added
	public static Order searchOrder(String name) {        
		for (User u : User.getUserList()) {        	
			for (Order o : u.getOrderList()) {               
				if (o.getUserName().equals(name))                    
					return o;           
			}        
		}        
		return null;    
	}

	public int getDeliveryDays() {		
		return deliveryDays;	
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public String getStrDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return formatter.format(this.orderDate);
	}

	public double getPrice() {
		return price;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getProductName() {
		return productName;
	}
	

}
