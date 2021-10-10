package commodity;

//import java.util.Date;
import java.time.LocalDate;
import commodity.Commodity;


public class Food extends Commodity {

	private String brand;

	public Food(String name, String brand, String detail, LocalDate dispatchDate, double fee,
			double discountRate) {
		super(name, detail, "Food", dispatchDate, fee, discountRate);
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
