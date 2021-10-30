package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import membership.Membership;
import membership.NonMembership;
import product.Product;
import staff.Employee;
import transactions.Sales;

public class User {
	private String userName;
	private String sex;
	private String email;
	private String userid;
	private int points = 0;
	private Membership membership;
	private boolean isCheckout;

	private ArrayList<Order> orderList;
	private static AtomicInteger uniqueId =new AtomicInteger();
	private static ArrayList<User> UserList = new ArrayList<User>();

	public User(String userName, String password, String sex, String email) {
		this.userName = userName;
		this.sex = sex;
		this.email = email;
		this.userid = String.valueOf(uniqueId.getAndIncrement());
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.isCheckout = false;
		UserList.add(this);
	}
	
	public User(String userName, String password, String sex, String email, String uid) {
		this.userName = userName;
		this.sex = sex;
		this.email = email;
		this.userid = uid;
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.isCheckout = false;
		UserList.add(this);
	}
	
	public boolean getIsCheckout() {
		return isCheckout;
	}
	
	public void setIsCheckout(boolean flag) {
		isCheckout = flag;
	}
	
	public static ArrayList<User> getUserList() {
		return UserList;
	}
	
	public void checkout() 
	{
		double total = Trolley.calculateTotal(Trolley.getTrolleyList());
		System.out.printf("%s's total payment is $%.2f, Thank you for your buying!\n", this.userName, total);
	}
	
	public static Sales createSales(String productName, int totalNum, LocalDate d, Employee e, int totalPrice) {
		return new Sales(productName, totalNum, d, e, totalPrice);
	}
	
	public Trolley createTrolley(Product product, int itemNum, LocalDate Date) // exception handle later
	{
		Trolley t = new Trolley(product, itemNum, Date);
		t.addProduct(t);
		return t;
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
	
	// added
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getSex() {
		return sex;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUserid() {
		return userid;
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
	
	public void confirmSale(Employee e, LocalDate date) 
	{
		Sales.markSales(Product.getCopyList(), Trolley.getTrolleyList(), e);
		System.out.println("Thank you for your patience, Wish you have a good day.");
	}

	public static AtomicInteger getUniqueId() {
		return uniqueId;
	}

	public static void setUniqueId(AtomicInteger uniqueId) {
		User.uniqueId = uniqueId;
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
