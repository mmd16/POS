package customerRequestHandler;

import customerData.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class Order implements Comparable<Order> {
	private String userName;
	private LocalDate orderDate;
	private int deliveryDays;
	private String message;
	private Customer customer;
	
	public Order (Customer customer, LocalDate orderDate, int deliveryDays){
		this.userName = customer.getUserName();		
		this.orderDate = orderDate;
		this.deliveryDays = deliveryDays;
		this.customer = customer;
		this.message = null;
	}
	
    public Order (Order order) {
		this.userName = order.getUserName();		
		this.orderDate = order.getOrderDate();
		this.deliveryDays = order.getDeliveryDays();		
    }
    
    public static Order searchOrder(ArrayList<Customer> customerList, ArrayList<Order> orderList) {
        for (Customer c : customerList) {
        	for (Order o : orderList) {
                if (o.getUserName().equals(c.getUserName()))
                    return o;
            }
        }
        return null;
    }
    // encounter bug: "%-9s%-14s%-9s%-26s%\n", the last % causes conversion bug.
    public static void listOrder(ArrayList<Order> orderList) {
        System.out.printf("%-9s%-14s%-13s%-13s\n", "Name", "Order Date", "Days", "Suggested Message");
        for (Order o : orderList) {
            System.out.printf("%-9s%-14s%-13s%-13s\n", o.getUserName(), o.getStrDate(),
                    String.valueOf(o.getDeliveryDays()), o.getMessage());
        }
    }
	
	public String getUserName() {
		return userName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public int getDeliveryDays() {
		return deliveryDays;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String msg) {
		message = msg;
	}
	
	public String getStrDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return formatter.format(this.orderDate);
	}
	
	public void suggestMsgToSend(Customer c, Order o, LocalDate produceDate) {
        LocalDate deliveryPeriod = produceDate.plusDays(this.deliveryDays);  
        long daysOfEarly = DAYS.between(deliveryPeriod, o.orderDate);
        long daysOfDelay = DAYS.between(o.orderDate, deliveryPeriod);        
        long refund = 50 * daysOfDelay;
        
        if (deliveryPeriod.equals(o.orderDate))
        	this.message = String.format("Hello %s%s! Your package will arrive exactly in the order date.", c.distinguishSex(), c.getUserName());
        else if (deliveryPeriod.isBefore(o.orderDate))
        	this.message = String.format("Hello %s%s! Congratulate that your package will arrive %d days earlier than the orignal date."
        			, c.distinguishSex(), c.getUserName(), daysOfEarly, getStrDate()); 
        else if (deliveryPeriod.isAfter(o.orderDate))
        	this.message = String.format("Hello %s%s! Sorry, your package will be delayed for %d days. You will get $%d as compensation."
        			, c.distinguishSex(), c.getUserName(), daysOfDelay, refund);
	}
	
    @Override
    public int compareTo(Order another) {
        return this.getUserName().compareTo(another.getUserName());
    }	
}
