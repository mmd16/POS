package product;

public class Product {
	private String name;
	private String description;
	private String type;
	private double price;
	private double discount;

	public Product(String name, String description, String type, double price, double discount) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double currentDiscount) {
		this.discount = currentDiscount;
	}

	public double getDiscountedPrice() {
		return this.price * this.discount;
	}

}
