package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import membership.Membership;
import membership.NonMembership;
import product.Product;
import random.ProductCodeGenerator;
import staff.Employee;
import staff.Manager;

public class Member {
	private String userName;
	private String sex;
	private String email;
	private String userid;
	private int points = 0;
	private Membership membership;
	private boolean isCheckout;
	private ArrayList<Order> orderList; //preorder items
	private ArrayList<Cart> cart;
	private ArrayList<CompletedCart> completedCart;
	private static AtomicInteger uniqueId =new AtomicInteger();
	private static final Map<String, Member> members = new HashMap<>();

	public Member(String userName, String password, String sex, String email) {
		this.userName = userName;
		this.sex = sex;
		this.email = email;
		this.userid = String.valueOf(uniqueId.getAndIncrement());
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.cart = new ArrayList<Cart>();
		this.completedCart = new ArrayList<CompletedCart>();
		this.isCheckout = false;
		addMember(userid, this);
	}
	
	public Member(String userName, String password, String sex, String email, String uid) {
		this.userName = userName;
		this.sex = sex;
		this.email = email;
		this.userid = uid;
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.isCheckout = false;
		addMember(userid, this);
	}
	
	public Member(String userName, String sex) 
	{
		this.userName = userName;
		this.sex = sex;
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.isCheckout = false;
		addMember(userid, this);
	}
	
	/**
	 * @author noah
	 */

	public static Map<String, Member> getMembers() {
		return members;
	}
	
	public static void addMember(String userId, Member member)
	{
		members.put(userId, member);
	}
	
	public static Member getMember(String userId)
	{
		return members.get(userId);
	}
	
	public static void removeMember(String userId)
	{
		members.remove(userId);
	}
	
	public void checkout(Employee employee) 
	{
		String temp = ProductCodeGenerator.generateOrderRefNo(LocalDate.now());
		for(Cart c: this.getCart()) 
		{
			employee.confirmSales(c, this, temp);
		}
	}
	
	public void countFinalPrice() 
	{
		double total = applyDiscount(Cart.countTotal(cart));
		countPoints(total);
		checkQualifiedForUpgrade();
		System.out.printf("The total payment is $%.2f, Thank you for your buying!\n", total);
	}
	
	public void addAccumulatedspending(double spending) 
	{
		this.getMembership().addAccumulatedSpending(spending);
	}

	public void checkQualifiedForUpgrade() 
	{
		Membership m = this.getMembership();
		if(m.checkQualified())
			this.setMembership(m.upgradeMembership());
	}
	
	public double applyDiscount(double total) 
	{
		return total * (1 + getDiscountRate());
	}
	
	public double getDiscountRate() 
	{
		return this.getMembership().getDiscountRate();
	}
	
	public void listCart() 
	{
		int temp = 1;
		System.out.printf("%-10s%-10s%-10s%-10s","No", "Description", "Quantity", "Price($)");
		for(Cart c: this.getCart()) 
		{
			System.out.printf("%-10d%-10s%-10s%-10s",temp, c.getProductName(), c.getQuantity(), c.getAllPrice());
			temp++;
		}
	}
	
	public static AtomicInteger getUniqueId() {
		return uniqueId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void countPoints(double total) {
		if(total > 100)
			points += (int) (total/100);
	}

	public void deductPoints(int input) {
		points -= input;
	}
	
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public boolean isCheckout() {
		return isCheckout;
	}

	public void setCheckout(boolean isCheckout) {
		this.isCheckout = isCheckout;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public ArrayList<Cart> getCart() {
		return cart;
	}

	public void addProductToCart(Product product, int quantity, LocalDate Date) {
		cart.add(new Cart(product, quantity, Date));
	}
	
	public void setCart(ArrayList<Cart> cart) {
		this.cart = cart;
	}

	public void removeProductInCart(int digit) {
		cart.remove(digit);
	}
	
	public void removePurchaseHistory(CompletedCart c) 
	{
		completedCart.remove(c);
	}
	
	public Product getCartForSpecificProduct(int digit) {
		return cart.get(digit).getProduct();
	}
	
	public void modifyNumofProductInCart(int digit, int quantity) {
		cart.get(digit).setQuantity(quantity);
	}
	
	public void addProductToCompletedCart(Cart c, String orderRefNo, String salesNo) {
		completedCart.add(new CompletedCart(c, orderRefNo, salesNo));
	}
	
	public void refund(String orderRefNo, String productName, String productType, int quantity, Manager manager)
	{
		for(CompletedCart c : completedCart) 
		{
			if(c.getCart().getProduct().getType().equals(productType)&& c.getCart().getProductName().equals(productName))
				manager.refund(c, quantity, this);
		}
	} 

	/**
	 * @author Andy
	 * 
	 */
    public Order createOrder(Member user, Product product, LocalDate orderDate, int deliveryDays) {
    	Order o = new Order(user, product, orderDate, deliveryDays);
        orderList.add(o);
        return o;
    }
	
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
	}
        
	public void printOrders() {
		System.out.printf("%-10s%-10s%-10s%-10s\n", "Category", "Product Name", "Price($)", "OrderDate");
		for (Order o : orderList) {
		System.out.printf("%-10s%-10s%-10s%-10s\n", o.getUserName(), o.getProductName(),
					Double.toString(o.getPrice()), o.getStrDate());
		}
	}
	
	public static void setUniqueId(AtomicInteger uniqueId) {
		Member.uniqueId = uniqueId;
	}

    public void listOrder() {
        System.out.printf("%-14s%-14s%-13s%-13s\n", "Customer Name", "Product Name", "Order Date", "Days");
        for (Order o : orderList) {
            System.out.printf("%-9s%-14s%-13s%-13s\n", o.getUserName(), o.getProductName(), o.getStrDate(),
                    String.valueOf(o.getDeliveryDays()));
        }
    }	

	public String distinguishSex() {
		String title = "";
		if (sex.equals("M"))
			title = "Mr.";
		else if (sex.equals("F"))
			title = "Ms.";
		return title;
	}
}
