package user;

import membership.*;
import random.idGenerator;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

	private String username;
	private String sex;
	private String password;
	private String email;
	private int userid;
	private int points = 0;
	private LocalDate orderDate;
	private Membership membership;
	private ArrayList<Order> orderList;
	private static ArrayList<User> UserList = new ArrayList<User>();

	public User(String username, String password, String sex, String email, LocalDate orderDate) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.userid = idGenerator.IDgenerator();
		setOrderDate(orderDate);
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		UserList.add(this);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setNewPassword(String oldPassword, String newPassword) {
		if (this.password.equals(oldPassword))
			this.password = newPassword;
		else
			System.out.println("The old password is not mactch. Please enter again.");
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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
		// setDate();
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getStrDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return formatter.format(getOrderDate());
	}

	public void setDate() {
		// Calendar currentDate = Calendar.getInstance();
		// Calendar preorderDate = Calendar.getInstance();
		// preorderDate.setTime(orderDate);
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public void addOrder(Order order) {
		orderList.add(order);
		// addSpending(order.getPrice());
	}

	public void removeOrder(Order order) {
		orderList.remove(order);
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public String getMembershipGrade() {
		return this.membership.getName();
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
