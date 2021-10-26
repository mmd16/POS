package user;

import membership.*;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.Calendar;
//import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

	private String username;
	private String sex;
	private String email;
	private String userid;
	private int points = 0;
	private Membership membership;
	
	private String membershipID;
	
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
	
	
	// new added constructor.
	public User(String userName, char sex, String membershipID) {		
		this.userName = userName;		
		this.sex = sex;		
		this.membershipID = membershipID;
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
	
	
	// added
	    public static User searchCustomer(ArrayList<User> customerList, String userName)  {
		for (User u : customerList) {
		    if(u.getUsername().equals(userName))
			return u;
		}
		    return null;
	    }
	
	// added	
    public Order createOrder(String userName, String productName, LocalDate orderDate, int deliveryDays) {
    	User u = User.searchCustomer(UserList, userName);
        Product p = Product.searchProduct(Product.getCopyList(), productName);
	Order o = new Order(u, p, orderDate, deliveryDays);
        OrderList.add(o);
        return o;
    }
	
	public String getUsername() {
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


	public static User searchUser(String uid) {
		for (User user : UserList) {
			if (user.getUserid().equals(uid)) {
				return user;
			}
		}
		return null;
	}


        
        // Here doesn't need category, instead It should be the customer's name.

	public void printOrders() {
		System.out.printf("%-10s%-10s%-10s%-10s\n", "Customer Name", "Product Name", "Price($)", "OrderDate");
		for (Order o : orderList) {
			System.out.printf("%-10s%-10s%-10s%-10s\n", o.getUserName(), o.getProductName(),
					Double.toString(o.getPrice()), o.getStrDate());
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
