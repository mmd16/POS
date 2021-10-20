package commodity;

//import java.util.Date;
import java.time.LocalDate;

public abstract class Commodity {

	private String productName;
	private String detail;
	private String category;
	private LocalDate dispatchDate;
	private double fee;
	private double discountRate;

	public Commodity(String productName, String detail, String category, LocalDate dispatchDate, double fee, double discountRate) {
		this.productName = productName;
		this.detail = detail;
		this.category = category;
		this.dispatchDate = dispatchDate;
		this.fee = fee;
		this.discountRate = discountRate;		
	}
	
	public LocalDate getDispatchDate() {
		return dispatchDate;
	}
	
	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCategory() {
		return category;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	public double getDiscountedFee() {
		return this.fee * this.discountRate;
	}

}
