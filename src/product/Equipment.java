package product;

import java.util.Date;

public class Equipment extends Product {
	private String brand;
	private Date warrantyPeriod;

	public Equipment(String name, String brand, Date warrantyPeriod, double price, int inventory) {
		super(name, "Equipment", price, inventory);
		this.warrantyPeriod = warrantyPeriod;
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(Date warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

}
