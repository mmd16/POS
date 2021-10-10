package user;

import commodity.Commodity;

//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Order {
	private String productName;
	private String category;
	private LocalDate orderDate;
	private double price;

	public Order (Commodity commodity, double membershipDiscount){
		this.productName = commodity.getProductName();
		this.category = commodity.getCategory();
		this.orderDate = null;
		
		if (membershipDiscount < commodity.getDiscountRate())
			this.price = commodity.getFee()*membershipDiscount;
		else
			this.price = commodity.getDiscountedFee();
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getCategory() {
		return category;
	}

	public String getProductName() {
		return productName;
	}

	public LocalDate getOrderDate() throws ParseException {
		return orderDate;
	}

	public String getStrDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return formatter.format(this.orderDate);
	}

	public double getPrice() {
		return price;
	}
}
