package staff;

import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Member;

public class Manager extends Employee {

	public Manager(String name, String sex, String email, String phonenum) {
		super(name, sex, email, phonenum);
	}

	public void refund(CompletedCart c, int quantity, Member member) {
		int temp = 0;
		double totalPrice = 0;
		Sales s = Sales.searchSales(c.getSalesCode());
		totalPrice = s.getMarkedprice();
		temp = (totalPrice > 100) ? (int) totalPrice / 100 : 0;
		member.getMembership().deductAccumulatedSpending(totalPrice);
		member.deductPoints(temp);
		checkForDelSales(c, quantity, member, s);
	}
	
	public void checkForDelSales(CompletedCart c, int quantity, Member member, Sales s) 
	{
		if(c.getCart().getQuantity() == quantity) 
		{
			member.removePurchaseHistory(c);
			Sales.removeSales(s);
		}
		else 
		{
			int temp = c.getCart().getQuantity();
			c.getCart().setQuantity(temp - quantity);
			s.setQuantity(temp - quantity);
		}
	}
}
