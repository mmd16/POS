package user;

import membership.*;
import product.Product;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

	private String username;
	private String sex;
	private String email;
	private String userid;
	private int points = 0;
	private Membership membership;
	
	private ArrayList<Order> orderList;
	private static AtomicInteger uniqueId =new AtomicInteger();
	private static ArrayList<User> UserList = new ArrayList<User>();

	public User(String username, String password, String sex, String email) {
		this.username = username;
		this.sex = sex;
		this.email = email;
		this.userid = String.valueOf(uniqueId.getAndIncrement());
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		UserList.add(this);
	}
	
	public User(String username, String password, String sex, String email, String uid) {
		this.username = username;
		this.sex = sex;
		this.email = email;
		this.userid = uid;
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		UserList.add(this);
	}
	
	
	public static ArrayList<User> getUserList() {
		return UserList;
	}
	
    public Order createOrder(User user, Product product, LocalDate orderDate, int deliveryDays) {
    	Order o = new Order(user, product, orderDate, deliveryDays);
        orderList.add(o);
        return o;
    }
    
	public static User searchUserName(String name) {
		for (User user : UserList) {
			if (user.getUserName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public static void suggestMsgToSend(String userName, LocalDate produceDate) { // User is for getting their names
		Order o = Order.searchOrder(userName);
		User u = User.searchUserName(userName);
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
	
	// added
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
	}
	
	public String getUserName() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public int getPoints() {
		return points;
	}


	public void addPoints(int points) {
		this.points += points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}


	public Membership getMembership() {
		return membership;
	}


	public void setMembership(Membership membership) {
		this.membership = membership;
	}


	public ArrayList<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}


	public static User searchUserID(String uid) {
		for (User user : UserList) {
			if (user.getUserid().equals(uid)) {
				return user;
			}
		}
		return null;
	}


        
	public void printOrders() {
		System.out.printf("%-10s%-10s%-10s%-10s\n", "Category", "Product Name", "Price($)", "OrderDate");
		for (Order o : orderList) {
		System.out.printf("%-10s%-10s%-10s%-10s\n", o.getUserName(), o.getProductName(),
					Double.toString(o.getPrice()), o.getStrDate());
		}
	}
	
	// added
	// encounter bug: "%-9s%-14s%-9s%-26s%\n", the last % causes conversion bug.
    public void listOrder() {
        System.out.printf("%-14s%-14s%-13s%-13s\n", "Customer Name", "Product Name", "Order Date", "Days");
        for (Order o : orderList) {
            System.out.printf("%-9s%-14s%-13s%-13s\n", o.getUserName(), o.getProductName(), o.getStrDate(),
                    String.valueOf(o.getDeliveryDays()));
        }
    }	
	
	// new method 
	public String distinguishSex() {
		String title = "";
		if (sex.equals("M"))
			title = "Mr.";
		else if (sex.equals("F"))
			title = "Ms.";
		return title;
	}
}
