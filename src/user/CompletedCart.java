package user;

public class CompletedCart{

	private Cart cart;
	private String orderRefNo;
	private String salesCode;
	public CompletedCart(Cart cart, String orderRefNo, String salesCode) 
	{
		this.cart = cart;
		this.orderRefNo = orderRefNo;
		this.salesCode = salesCode;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getOrderRefNo() {
		return orderRefNo;
	}
	
	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}
	
	public String getSalesCode() {
		return salesCode;
	}
	
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	
	
	
	
	
}
