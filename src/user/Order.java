package user;

import static java.time.temporal.ChronoUnit.DAYS;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import product.Product;

public class Order {
	private String productName;
	private LocalDate orderDate;
	private User user;
	private double price;
	private int deliveryDays;
	
	public Order (User user, LocalDate orderDate, int deliveryDays){
		// add new parameters
		this.userName = user.getUsername();	
		this.orderDate = orderDate;				
		this.deliveryDays = deliveryDays;
		this.user = user;		
	}		

	public static Order searchOrder(ArrayList<User> customerList, ArrayList<Order> orderList) {        
		for (User u : customerList) {        	
			for (Order o : orderList) {               
				if (o.getUserName().equals(c.getUserName()))                    
					return o;           
			}        
		}        
		return null;    
	}

	public LocalDate getOrderDate() throws ParseException {
		return orderDate;
	}

	public String getStrDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return formatter.format(this.orderDate);
	}

	public double getPrice() {
		return price;
	}
	
	//This is a new add function, it will calculate the suitable response message to customer 
	//by the predicted order's delivery day and the product's produce date
	//Here I think that this part can connect with product class' inventory status, when the inputted product' inventory number = 0, 
        //Then, the staff can create an order for the customer, 
        //Also, after making many sales payments, if the inventory has reduced to 0, then that product can similarly be created as an order by the staff.
	//Although it maybe weird in reality, however the project needs to have different inputs and outputs 
	
	//Also I want to let the staff input produce date freely rather than only setting it from the beginning.
	public void suggestMsgToSend(User u, Order o, LocalDate produceDate) { // User is for getting their names
        LocalDate deliveryPeriod = produceDate.plusDays(this.deliveryDays);  // this is the date after the product is produced and deliver to the customer
        long daysOfEarly = DAYS.between(deliveryPeriod, o.orderDate);  // this calculate how many days it is earlier than the order date
        long daysOfDelay = DAYS.between(o.orderDate, deliveryPeriod);   // this calculate how many days it is later than the order date     
        long refund = 50 * daysOfDelay;
        
        // check the date with the orderDate
        if (deliveryPeriod.equals(o.orderDate)) //equals
        	System.out.printf("Hello %s%s! Your package will arrive exactly in the order date.", u.distinguishSex(), u.getUsername());
        else if (deliveryPeriod.isBefore(o.orderDate)) //earlier case
        	System.out.printf("Hello %s%s! Congratulate that your package will arrive %d days earlier than the orignal date."
        			, u.distinguishSex(), u.getUsername(), daysOfEarly, getStrDate()); 
        else if (deliveryPeriod.isAfter(o.orderDate)) // late case
        	System.out.printf("Hello %s%s! Sorry, your package will be delayed for %d days. You will get $%d as compensation."
        			, u.distinguishSex(), u.getUsername(), daysOfDelay, refund);
	}
}
