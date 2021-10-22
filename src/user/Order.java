package user;

import static java.time.temporal.ChronoUnit.DAYS;

//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;
import java.time.format.DateTimeFormatter;

import product.Product;

public class Order {
	private String productName;
	private String category;
	private LocalDate orderDate;
	private double price;

	private int deliveryDays;
	private String message;
	
	public Order (Product commodity, double membershipDiscount, int deliveryDays){
		this.productName = commodity.getName();
//		this.category = commodity.getDescription(); // no need desc qaq (karina
		this.orderDate = null;
		
		
		// add new parameters
		this.deliveryDays = deliveryDays;
		this.message = null;
		
//		if (membershipDiscount < commodity.getDiscountRate())
//			this.price = commodity.getFee()*membershipDiscount;
//		else
//			this.price = commodity.getDiscountedFee();
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getCategory() {
		return category;
	}

	public String getProductName() {
		return productName;
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
	//And it should separate with printOrder function, so make another digit or another controller to handle it.
	public void suggestMsgToSend(User u, Order o, LocalDate produceDate) {
        LocalDate deliveryPeriod = produceDate.plusDays(this.deliveryDays);  // this is the date after the product is produced and deliver to the customer
        long daysOfEarly = DAYS.between(deliveryPeriod, o.orderDate);  // this calculate how many days it is earlier than the order date
        long daysOfDelay = DAYS.between(o.orderDate, deliveryPeriod);   // this calculate how many days it is later than the order date     
        long refund = 50 * daysOfDelay;
        
        // check the date with the orderDate
        if (deliveryPeriod.equals(o.orderDate)) //equals
        	this.message = String.format("Hello %s%s! Your package will arrive exactly in the order date.", u.distinguishSex(), u.getUsername());
        else if (deliveryPeriod.isBefore(o.orderDate)) //earlier case
        	this.message = String.format("Hello %s%s! Congratulate that your package will arrive %d days earlier than the orignal date."
        			, u.distinguishSex(), u.getUsername(), daysOfEarly, getStrDate()); 
        else if (deliveryPeriod.isAfter(o.orderDate)) // late case
        	this.message = String.format("Hello %s%s! Sorry, your package will be delayed for %d days. You will get $%d as compensation."
        			, u.distinguishSex(), u.getUsername(), daysOfDelay, refund);
	}
}
