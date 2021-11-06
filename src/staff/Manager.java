package staff;

import transactions.Sales;
import user.CompletedCart;
import user.Member;

public class Manager extends Employee {

	public Manager(String name, String sex, String email, String phonenum) {
		super(name, sex, email, phonenum);
	}
/**
 * refund function for members, only the manager hv the right to do it.
 * @param c
 * @param quantity
 * @param member
 */
	public void refund(CompletedCart c, int quantity, Member member) {
		int temp = 0;
		double totalPrice = 0;
		Sales s = Sales.searchSales(c.getSalesCode());
		totalPrice = s.getMarkedprice();
		temp = (totalPrice > 100) ? (int) totalPrice / 100 : 0;
		member.deductAccumulatedSpending(totalPrice);
		member.deductPoints(temp);
		checkForCart(c, quantity, member);
		checkForSales(s, quantity);
	}
	
	public void refund(Sales s, int quantity) 
	{
		checkForSales(s, quantity);
	}
/**
 * used to handle the completed cart and the sales if the customers choose to refund.
 * @param c
 * @param quantity
 * @param member
 * @param s
 */
	public void checkForCart(CompletedCart c, int quantity, Member member) 
	{
		if(c.getCart().getQuantity() == quantity) 
		{
			member.removePurchaseHistory(c);
		}
		else 
		{
			c.getCart().deductQuantity(quantity);
		}
	}
	public void checkForSales(Sales s, int quantity) 
	{
		if(s.getQuantity() == quantity) 
		{
			Sales.removeSales(s);
		}
		else 
		{
			s.deductQuantity(quantity);
		}
	}
}
