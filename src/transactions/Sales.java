package transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import product.Product;
import user.Employee;

public class Sales {
	private LocalDate date;
	private Employee employee;
	private double markedPrice;
	private double sellingPrice;
	private double unitPrice;
	private String productName;
	private String productCode;
	private String productType;
	private String salesCode;
	private String orderRefNo;
	private Product product;
	private int quantity;
	private static AtomicInteger uniqueId = new AtomicInteger();

	public Sales(Product p, int quantity, LocalDate date, Employee employee, double markedprice, double sellingPrice,
			String orderRefNo) {
		this.date = date;
		this.employee = employee;
		this.product = p;
		this.productName = p.getName();
		this.productCode = p.getProductCode();
		this.productType = p.getType();
		this.quantity = quantity;
		this.unitPrice = p.getPrice();
		this.markedPrice = markedprice;
		this.sellingPrice = sellingPrice;
		this.orderRefNo = orderRefNo;
		this.salesCode = String.valueOf(uniqueId.getAndIncrement());
	}


	public String getStrDate() {
		DateTimeFormatter datestr = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return datestr.format(this.date);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getMarkedPrice() {
		return markedPrice;
	}

	public void setMarkedPrice(double markedprice) {
		this.markedPrice = markedprice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void deductQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public static AtomicInteger getUniqueId() {
		return uniqueId;
	}

	public static void setUniqueId(AtomicInteger uniqueId) {
		Sales.uniqueId = uniqueId;
	}

	public String getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
