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
import staff.Employee;
import staff.Manager;
import tool.ProductCodeGenerator;
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
	private ArrayList<Cart> cart;
	private ArrayList<CompletedCart> completedCart;
	private static AtomicInteger uniqueId = new AtomicInteger();

	public Member(String userName, String birthOfYear, String sex, String email) {
		this.userName = userName;
		this.birthOfYear = birthOfYear;
		this.sex = sex;
		this.email = email;
		this.userid = String.valueOf(uniqueId.getAndIncrement());
		this.membership = new SilverMembership(0.95, 5000, "Silver");
		this.ageGroup = AgeGroupFactory.Allocation(Integer.parseInt(birthOfYear));
		this.cart = new ArrayList<Cart>();
		this.completedCart = new ArrayList<CompletedCart>();
	}

	public Member(String userName, String birthOfYear, String sex, String email, String uid) {
		this.userName = userName;
		this.birthOfYear = birthOfYear;
		this.sex = sex;
		this.email = email;
		this.userid = uid;
		this.ageGroup = AgeGroupFactory.Allocation(Integer.parseInt(birthOfYear));
		this.membership = new SilverMembership(0.95, 5000, "Silver");
		this.cart = new ArrayList<Cart>();
		this.completedCart = new ArrayList<CompletedCart>();
	}

	public Member(String userName, String sex) {
		this.userName = userName;
		this.sex = sex;
		this.membership = new NonMembership();
		this.userid = "0";
		this.cart = new ArrayList<Cart>();
	}


	public double getDiscountRate() {
		return this.getMembership().getDiscountRate();
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

	public ArrayList<Cart> getCart() {
		return cart;
	}

	public Cart addProductToCart(Product product, int quantity, LocalDate Date) {
		Cart c = new Cart(product, quantity, Date);
		cart.add(c);
		return c;
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

	public CompletedCart addProductToCompletedCart(Cart c, String orderRefNo, String salesNo) {
		CompletedCart complete = new CompletedCart(c, orderRefNo, salesNo);
		completedCart.add(complete);
		return complete;
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

}
