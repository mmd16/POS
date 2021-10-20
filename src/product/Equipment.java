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

	public static Equipment createEquipment(String name, String brand, Date warrantyPeriod, double price,
			int inventory) {
		Product product = searchProduct(name);
		if (product == null)
			return new Equipment(name, brand, warrantyPeriod, price, inventory);
		else
			return null;
			// throw exception
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
