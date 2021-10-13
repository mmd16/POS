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
	private int userid;
	private int points = 0;
	private Membership membership;
	private ArrayList<Order> orderList;
	private static AtomicInteger uniqueId =new AtomicInteger();
	private static ArrayList<User> UserList = new ArrayList<User>();

	public User(String username, String password, String sex, String email, LocalDate orderDate) {
		this.username = username;
		this.sex = sex;
		this.email = email;
		this.userid = uniqueId.getAndIncrement();
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		UserList.add(this);
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


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
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


	public static User searchUser(int uid) {
		for (User user : UserList) {
			if (user.userid == uid) {
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
}
