package user;

public class Manager extends Employee {

	public Manager(String name, String sex, String email, String phonenum) {
		super(name, sex, email, phonenum);
	}
	public Manager(String name, String sex, String email, String phonenum, String uid) {
		super(name, sex, email, phonenum, uid);
	}

/**
 * used to handle the completed cart and the sales if the customers choose to refund.
 * @param c
 * @param quantity
 * @param member
 * @param s
 */
//	public void checkForCart(CompletedCart c, int quantity, Member member) 
//	{
//		if(c.getCart().getQuantity() == quantity) 
//		{
//			member.removePurchaseHistory(c);
//		}
//		else 
//		{
//			c.getCart().deductQuantity(quantity);
//		}
//	}
	
//	public void checkForSales(Sales s, int quantity) 
//	{
//		double unitPrice = s.getSellingUnitPrice();
//		if(s.getQuantity() == quantity) 
//		{
//			Sales.removeSales(s);
//			s.getProduct().removeSales(s);
//		}
//		else 
//		{
//			s.deductQuantity(quantity);
//			s.adjustMarkedPrice();
//			s.adjustSellingPrice(unitPrice);
//		}
//	}

}
