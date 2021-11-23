package function;

import java.time.LocalDate;
import java.util.ArrayList;

import db.InventoryDataBase;
import db.SalesDataBase;
import membership.NonMembership;
import product.Product;
import staff.Employee;
import staff.Manager;
import tool.ProductCodeGenerator;
import transactions.MemberSale;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Member;

public class CheckoutFunctions {

	public CheckoutFunctions() {
	};

	private SalesDataBase salesDB = SalesDataBase.getInstance();
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	private ProductCodeGenerator codeGen = ProductCodeGenerator.getInstance();

	public void checkout(Member member, Employee employee) {
		String temp = codeGen.generateOrderRefNo(LocalDate.now());
		for (Cart c : member.getCart()) {
			confirmSales(c, member, temp, employee);
		}
		removeCartAfterTransaction(member);
	}

	public void refund(CompletedCart c, int quantity, Manager manager, Member member) {
		if (member.getMembership() instanceof NonMembership) {
			Sales s = salesDB.searchSales(c.getSalesCode());
			checkForSales(s, quantity);
		} else {
			Sales s = salesDB.searchSales(c.getSalesCode());
			checkForCart(c, quantity, member);
			checkForSales(s, quantity);
		}
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

	public Cart updateCart(int position, int quantity, Member member) {
		double unitPrice = member.getCart().get(position).getUnitPrice();
		member.getCart().get(position).setQuantity(quantity);
		member.getCart().get(position).setAllPrice(quantity * unitPrice);
		return member.getCart().get(position);
	}

	public boolean validator(Employee employee) {
		if (employee instanceof Manager) {
			return true;
		}
		return false;
	}

	public void removeProductInCart(Member member, int position) {
		member.removeProductInCart(position);
	}

	public boolean changeForthePayment(double total, double cash) {
		boolean enough = true;
		enough = (cash >= total) ? true : false;
		return enough;
	}

	public double countFinalPrice(Member member) {
		double total = applyDiscount(countTotalPrice(member.getCart()), member);
		int pointsTobeAdded = countPoints(total, member);
		member.addPoints(pointsTobeAdded);
		return total;
	}

	public static double countTotalPrice(ArrayList<Cart> cartList) {
		double rslt = 0;
		for (Cart cart : cartList) {
			rslt += cart.getAllPrice();
		}
		return rslt;
	}

	public double applyDiscount(double total, Member member) {
		return total * member.getMembership().getDiscountRate();
	}

	public int countPoints(double total, Member member) {
		if (member.getMembership() instanceof NonMembership) {
			return 0;
		} else {
			int temp = 0;
			if (total > 100)
				temp += (int) (total / 100);
			return temp;
		}

	}

	public Sales confirmSales(Cart c, Member member, String orderRefNo, Employee employee) {
		double sellingPrice = applyDiscount(c.getAllPrice(), member);
		if (member.getMembership() instanceof NonMembership) {
			Sales s = new Sales(c.getProduct(), c.getQuantity(), LocalDate.now(), employee, c.getAllPrice(),
					sellingPrice, orderRefNo);
			Product p = c.getProduct();
			invenDB.deductInventoryofProductsFromQueue(c.getQuantity(), p);
			salesDB.add(s);
			p.addSales(s);
			return s;
		} else {
			MemberSale s = new MemberSale(c.getProduct(), c.getQuantity(), LocalDate.now(), employee, c.getAllPrice(),
					applyDiscount(c.getAllPrice(), member), orderRefNo, member);
			member.addAccumulatedSpending(sellingPrice);
			member.addProductToCompletedCart(c, orderRefNo, s.getSalesCode());
			Product p = c.getProduct();
			invenDB.deductInventoryofProductsFromQueue(c.getQuantity(), p);
			salesDB.add(s);
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
			salesDB.remove(s);
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
}
