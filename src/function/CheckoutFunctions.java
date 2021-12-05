package function;

import java.time.LocalDate;
import java.util.ArrayList;

import db.InventoryDataBase;
import db.SalesDataBase;
import product.Product;
import tool.Tools;
import transactions.MemberSale;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Employee;
import user.Manager;
import user.Member;

public class CheckoutFunctions {
	private SalesDataBase salesDB = SalesDataBase.getInstance();

	private InventoryDataBase invenDB = InventoryDataBase.getInstance();

	private Tools tools = Tools.getInstance();

	public Sales checkout(Member member, Employee employee) {
		String temp = this.tools.generateOrderRefNo(LocalDate.now());
		Sales s = null;
		for (Cart c : member.getCart())
			s = confirmSales(c, member, temp, employee);
		removeCartAfterTransaction(member);
		return s;
	}

	public void refund(CompletedCart c, int quantity, Manager manager, Member member) {
		Sales s = this.salesDB.searchSales(c.getSalesCode());
		checkForCart(c, quantity, member);
		checkForSales(s, quantity);
	}

	public void refund(String orderRefNo, int quantity, Manager manager) {
		Sales s = this.salesDB.getSalesByOrderRefNo(orderRefNo);
		checkForSales(s, quantity);
	}

	public CompletedCart searchHistoryForRefund(String orderRefNo, String productName, String productType, int quantity,
			Member member) {
		for (CompletedCart c : member.getCompletedCart()) {
			if (c.getCart().getProduct().getType().equals(productType)
					&& c.getCart().getProductName().equals(productName) && c.getOrderRefNo().equals(orderRefNo))
				return c;
		}
		return null;
	}

	public Cart updateCart(Cart c, int quantity) {
		double unitPrice = c.getUnitPrice();
		c.setQuantity(quantity);
		c.setAllPrice(quantity * unitPrice);
		return c;
	}
	
	public boolean validator(Employee employee) {
		if (employee instanceof Manager)
			return true;
		return false;
	}

	public void removeProductInCart(Member member, Cart c) {
		member.removeProductInCart(c);
	}

	public boolean changeForthePayment(double total, double cash) {
		boolean enough = true;
		enough = (cash >= total);
		return enough;
	}

	public double countFinalPrice(Member member) {
		double total = applyDiscount(countTotalPrice(member.getCart()), member);
		int pointsTobeAdded = countPoints(total, member);
		member.addPoints(pointsTobeAdded);
		return total;
	}

	public double countTotalPrice(ArrayList<Cart> cartList) {
		double rslt = 0;
		for (Cart cart : cartList)
			rslt += cart.getAllPrice();
		return rslt;
	}

	public double applyDiscount(double total, Member member) {
		return total * member.getMembership().getDiscountRate();
	}

	public int countPoints(double total, Member member) {
		if (!tools.checkMembership(member)) {
			return 0;
		} else {
			int temp = 0;
			if (total >= 100.0)
				temp += (int) (total / 100.0);
			return temp;
		}
	}

	public Sales confirmSales(Cart c, Member member, String orderRefNo, Employee employee) {
		double sellingPrice = applyDiscount(c.getAllPrice(), member);
		if (member.getMembership() instanceof membership.NonMembership) {
			Sales sales = new Sales(c.getProduct(), c.getQuantity(), LocalDate.now(), employee, c.getAllPrice(),
					sellingPrice, orderRefNo);
			Product product = c.getProduct();
			this.invenDB.deductInventoryofProductsFromQueue(c.getQuantity(), product);
			this.salesDB.add(sales);
			product.addSales(sales);
			return sales;
		} else {
			MemberSale s = new MemberSale(c.getProduct(), c.getQuantity(), LocalDate.now(), employee, c.getAllPrice(),
					applyDiscount(c.getAllPrice(), member), orderRefNo, member);
			member.addAccumulatedSpending(sellingPrice);
			member.addProductToCompletedCart(c, orderRefNo, s.getSalesCode());
			Product p = c.getProduct();
			this.invenDB.deductInventoryofProductsFromQueue(c.getQuantity(), p);
			this.salesDB.add(s);
			p.addSales(s);
			return s;
		}
	}

	public void removeCartAfterTransaction(Member member) {
		member.getCart().clear();
	}

	public void checkForSales(Sales s, int quantity) {
		double unitPrice = getSellingUnitPrice(s);
		if (s.getQuantity() == quantity) {
			this.salesDB.remove(s);
			s.getProduct().removeSales(s);
		} else {
			s.deductQuantity(quantity);
			adjustMarkedPrice(s);
			adjustSellingPrice(unitPrice, s);
		}
	}

	public void checkForCart(CompletedCart c, int quantity, Member member) {
		if (c.getCart().getQuantity() == quantity) {
			member.removePurchaseHistory(c);
		} else {
			c.getCart().deductQuantity(quantity);
		}
	}

	public void adjustMarkedPrice(Sales s) {
		s.setMarkedPrice(s.getUnitPrice() * s.getQuantity());
	}

	public double getSellingUnitPrice(Sales s) {
		double unitPrice = s.getSellingPrice() / s.getQuantity();
		return unitPrice;
	}

	public void adjustSellingPrice(double price, Sales s) {
		s.setSellingPrice(price * s.getQuantity());
	}
	
	public Cart SearchCart(Member member, String ProductName) {
		for (Cart c : member.getCart()) {
			if(c.getProductName().equals(ProductName))
				return c;
		}
		return null;
	}
}
