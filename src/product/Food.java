package product;

import java.util.Date;

public class Food extends Product {
	private Date expireDate;

	public Food (String name, String description, Date expireDate, double price, double discount) {
		super(name, description, "Food", price, discount);
		this.expireDate = expireDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
