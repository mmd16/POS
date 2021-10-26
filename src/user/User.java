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
	public static Order searchOrder(String name) {        
		for (User u : UserList) {        	
			for (Order o : orderList) {               
				if (o.getUserName().equals(name))                    
					return o;           
			}        
		}        
		return null;    
	}
	
	// added	
    public Order createOrder(String ProductName, LocalDate orderDate, int deliveryDays) {
    	User u = User.searchCustomer(UserList, this.getUsername());
        Product p = Product.searchProduct(Product.getCopyList(), productName);
	Order o = new Order(u, p, orderDate, deliveryDays);
        OrderList.add(o);
        return o;
    }
	// added
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
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


        
	public void printOrders() {
		System.out.printf("%-10s%-10s%-10s%-10s\n", "Category", "Product Name", "Price($)", "OrderDate");
		for (Order o : orderList) {
			System.out.printf("%-10s%-10s%-10s%-10s\n", o.getCategory(), o.getProductName(),
					Double.toString(o.getPrice()), o.getStrDate());
		}
	}
	
	// added
	// encounter bug: "%-9s%-14s%-9s%-26s%\n", the last % causes conversion bug.
    public static void listOrder() {
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
