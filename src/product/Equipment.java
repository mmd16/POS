package product;

import java.util.Date;

public class Equipment extends Product{
	private String brand;
	private Date warrantyPeriod;


	public Equipment (String name, String description, String brand, String productCode, Date warrantyPeriod, double price) {
		super(name, description, "Equipment", price);
		this.warrantyPeriod = warrantyPeriod;
		this.brand = brand;
		productList.add(this);
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
