package transactions;

import java.time.LocalDate;

import product.Product;
import staff.Employee;
import user.User;

public class SalesWithMembership extends Sales {

	private User user;

	public SalesWithMembership(LocalDate Date, Employee employee, User user, Product product) {
		super(Date, employee, product);
		this.user = user;
		addpoints(product.getPrice(), user);
		salesList.add(this);
	}
	
	public void addpoints(double price, User user) 
	{	
		int rslt = 0;
		double temp = price;
		while(temp > 100) 
		{
			rslt++;
			temp -= 100;
		}
		user.addPoints(rslt);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
