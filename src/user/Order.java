package user;



import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import product.Product;

public class Order {
	private String productName;
	private String orderCode;
	private String userName;	
	private LocalDate orderDate;
	private Member member;
	private double price;
	private int deliveryDays;
	private static AtomicInteger uniqueId =new AtomicInteger();
	
	public Order (Member user, Product product, LocalDate orderDate, int deliveryDays){
		// add new parameters
		this.userName = user.getUserName();	
		this.productName = product.getName();
		this.orderDate = orderDate;				
		this.deliveryDays = deliveryDays;
		this.member = user;		
		this.orderCode = String.valueOf(uniqueId.getAndIncrement());
	}		
	
	// added
	/**
	 * 
	 * @param member for specific member, if you are wanna loop for all members, you should generate a static orderList instead.
	 * @param orderCode
	 * @return
	 */
	public static Order searchOrder(ArrayList<Order> orderList, String orderCode) {        
		for(Order o : orderList) {
			if(o.getOrderCode().equals(orderCode))
				return o;
		}
		return null;
	}
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	
	
	public static void suggestMsgToSend(ArrayList<Order> orderList, String orderCode, Member member, LocalDate produceDate) { // User is for getting their names
		Order o = Order.searchOrder(orderList, orderCode);
		Member u = member;
		LocalDate deliveryPeriod = produceDate.plusDays(o.getDeliveryDays());  // this is the date after the product is produced and deliver to the customer
		long daysOfEarly = DAYS.between(deliveryPeriod, o.getOrderDate());  // this calculate how many days it is earlier than the order date
		long daysOfDelay = DAYS.between(o.getOrderDate(), deliveryPeriod);   // this calculate how many days it is later than the order date     
		long refund = 50 * daysOfDelay;

		// check the date with the orderDate
		if (deliveryPeriod.equals(o.getOrderDate())) //equals
			System.out.printf("Hello %s%s! Your package will arrive exactly in the order date.\n", u.distinguishSex(), u.getUserName());
        else if (deliveryPeriod.isBefore(o.getOrderDate()))
        	System.out.printf("Hello %s%s! Congratulate that your package will arrive %d days earlier than the orignal date on %s.\n"
        			, u.distinguishSex(), u.getUserName(), daysOfEarly, o.getOrderDate().toString()); 
        else if (deliveryPeriod.isAfter(o.getOrderDate()))
        	System.out.printf("Hello %s%s! Sorry, your package will be delayed for %d days. You will get $%d as compensation.\n"
        			, u.distinguishSex(), u.getUserName(), daysOfDelay, refund);
	}
	

}
