package product;

import java.sql.Date;

public class Equipment extends Product {
	private String brand;
	private String productCode;
	private Date warrantyPeriod;

	
	public Equipment (String name, String description, String brand, String productCode, Date warrantyPeriod, double price, double discount) {
		super(name, description, "Equipment", price, discount);
		this.warrantyPeriod = warrantyPeriod;
	}
	
	public Date getWarrantyPeriod() {
		return warrantyPeriod;
	}
	
	public void setWarrantyPeriod(Date warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
}
