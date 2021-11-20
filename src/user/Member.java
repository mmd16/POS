package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import ageGroup.AgeGroup;
import ageGroup.AgeGroupFactory;
import membership.Membership;
import membership.NonMembership;
import membership.PlatinumMembership;
import membership.SilverMembership;
import product.Product;
import random.ProductCodeGenerator;
import staff.Employee;
import staff.Manager;
import transactions.Sales;

public class Member {
	private String userName;
	private String sex;
	private String email;
	private String userid;
	private String birthOfYear;
	private int points = 0;
	private double accumulatedSpending = 0;
	private Membership membership;
	private AgeGroup ageGroup;
	private ArrayList<Order> orderList; // preorder items
	private ArrayList<Cart> cart;
	private ArrayList<CompletedCart> completedCart;
	private static AtomicInteger uniqueId = new AtomicInteger();
	private static final Map<String, Member> members = new HashMap<>();

	public Member(String userName, String birthOfYear, String sex, String email) {
		this.userName = userName;
		this.birthOfYear = birthOfYear;
		this.sex = sex;
		this.email = email;
		this.userid = String.valueOf(uniqueId.getAndIncrement());
		this.membership = new SilverMembership(0.95, 5000, "Silver");
		this.ageGroup = AgeGroupFactory.Allocation(Integer.parseInt(birthOfYear));
		this.orderList = new ArrayList<Order>();
		this.cart = new ArrayList<Cart>();
		this.completedCart = new ArrayList<CompletedCart>();
		addMember(userid, this);
	}

	public Member(String userName, String birthOfYear, String sex, String email, String uid) {
		this.userName = userName;
		this.birthOfYear = birthOfYear;
		this.sex = sex;
		this.email = email;
		this.userid = uid;
		this.ageGroup = AgeGroupFactory.Allocation(Integer.parseInt(birthOfYear));
		this.membership = new SilverMembership(0.95, 5000, "Silver");
		this.orderList = new ArrayList<Order>();
		this.cart = new ArrayList<Cart>();
		this.completedCart = new ArrayList<CompletedCart>();
		addMember(userid, this);
	}

	public Member(String userName, String sex) {
		this.userName = userName;
		this.sex = sex;
		this.membership = new NonMembership();
		this.orderList = new ArrayList<Order>();
		this.userid = "0";
		this.cart = new ArrayList<Cart>();
		addMember(userid, this);
	}

	/**
	 * @author noah
	 */

	public void checkout(Employee employee) {
		String temp = ProductCodeGenerator.generateOrderRefNo(LocalDate.now());
		for (Cart c : this.getCart()) {
			employee.confirmSales(c, this, temp);
		}
		this.upgradeMembership();
		removeCartAfterTransaction();
	}

	public int countPoints(double total) {
		int temp = 0;
		if (total > 100)
			temp += (int) (total / 100);
		return temp;
	}

	public void removeCartAfterTransaction() {
		if (checkMembership()) {
			for (CompletedCart c : this.getCompletedCart()) {
				this.removeProductInCart(c.getCart());
			}
		}

	}

	public double countFinalPrice() {
		double total = applyDiscount(Cart.countTotal(cart));
		int pointsTobeAdded = countPoints(total);
		addPoints(pointsTobeAdded);
		return total;
	}

	public boolean checkMembership() {
		if (this.getMembership() instanceof NonMembership)
			return false;
		return true;
	}

	public void upgradeMembership() {
		if (checkMembership()) {
			while (this.checkRemainingProgress() < 0 && this.getMembership() instanceof PlatinumMembership) {
				setMembership(this.getMembership().upgradeMembership());
			}
		}
	}

	public double applyDiscount(double total) {
		return total * getDiscountRate();
	}

	public double getDiscountRate() {
		return this.getMembership().getDiscountRate();
	}

	public double checkRemainingProgress() {
		return this.getMembership().getUpgradeRequirement() - this.accumulatedSpending;
	}

	public static Map<String, Member> getMembers() {
		return members;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public double getAccumulatedSpending() {
		return accumulatedSpending;
	}

	public void setAccumulatedSpending(double accumulatedSpending) {
		this.accumulatedSpending = accumulatedSpending;
	}

	public void addAccumulatedSpending(double spding) {
		this.accumulatedSpending += spding;
	}

	public void deductAccumulatedSpending(double spding) {
		this.accumulatedSpending -= spding;
	}

	public String getBirthOfYear() {
		return birthOfYear;
	}

	public void setBirthOfYear(String birthOfYear) {
		this.birthOfYear = birthOfYear;
	}

	public ArrayList<CompletedCart> getCompletedCart() {
		return completedCart;
	}

	public void setCompletedCart(ArrayList<CompletedCart> completedCart) {
		this.completedCart = completedCart;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int pointsTobeAdded) {
		this.points += pointsTobeAdded;
	}

	public void deductPoints(int pointsTobeDeduct) {
		this.points -= pointsTobeDeduct;
	}

	public static Member getMember(String userId) {
		return members.get(userId);
	}

	public static void addMember(String userId, Member member) {
		members.put(userId, member);
	}

	public static void removeMember(String userId) {
		members.remove(userId);
	}

	public void listCart() {
		int temp = 1;
		System.out.printf("%-20s%-20s%-20s%-20s", "No", "Description", "Quantity", "Price($)");
		for (Cart c : this.getCart()) {
			System.out.printf("\n%-20d%-20s%-20s%-20s", temp, c.getProductName(), c.getQuantity(), c.getAllPrice());
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

	public void removeProductInCart(Cart c) {
		cart.remove(c);
	}

	public void removePurchaseHistory(CompletedCart c) {
		completedCart.remove(c);
	}

	public Product getCartForSpecificProduct(int digit) {
		return cart.get(digit).getProduct();
	}

	public Cart updateCart(int digit, int quantity) {
		double unitPrice = cart.get(digit).getUnitPrice();
		cart.get(digit).setQuantity(quantity);
		cart.get(digit).setAllPrice(quantity * unitPrice);
		return cart.get(digit);
	}

	public void addProductToCompletedCart(Cart c, String orderRefNo, String salesNo) {
		completedCart.add(new CompletedCart(c, orderRefNo, salesNo));
	}

	public CompletedCart searchHistoryForrefund(String orderRefNo, String productName, String productType,
			int quantity) {
		for (CompletedCart c : completedCart) {
			if (c.getCart().getProduct().getType().equals(productType)
					&& c.getCart().getProductName().equals(productName))
				return c;
		}
		return null;
	}

	public void refund(CompletedCart c, int quantity, Manager manager) {
		if (this.getMembership() instanceof NonMembership) {
			Sales s = Sales.searchSales(c.getSalesCode());
			manager.checkForSales(s, quantity);
		} else {
			Sales s = Sales.searchSales(c.getSalesCode());
			manager.checkForCart(c, quantity, this);
			manager.checkForSales(s, quantity);
		}
	}

	public void refund(String orderRefNo, int quantity, Manager manager) {
		Sales s = Sales.getSalesByOrderRefNo(orderRefNo);
		manager.refund(s, quantity);
	}

	public boolean isEmpty() {
		return cart.isEmpty();
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
