package product;

import java.time.LocalDate;
import java.util.ArrayList;

import customerData.Customer;
import exception.ExCustomerNotFound;
import exception.ExProductNotFound;

public class Product implements Comparable<Product> {

	private String productName;
	private double fee;
	private String inventoryStatus;
	private LocalDate dispatchDate;
	private int numbersToImport;

	public Product(String productName, double fee, String inventoryStatus) {
		this.productName = productName;
		this.fee = fee;	
		this.inventoryStatus = inventoryStatus;
	}
	
	public Product(String productName, double fee, String inventoryStatus, LocalDate dispatchDate, int numbersToImport) {
		this.productName = productName;
		this.fee = fee;	
		this.inventoryStatus = inventoryStatus;
		this.dispatchDate = dispatchDate;
		this.numbersToImport = numbersToImport;
	}
	
    public static Product searchProduct(ArrayList<Product> productList, String productName) throws ExProductNotFound {
        for (Product p : productList) {
            if(p.getProductName().equals(productName))
                return p;
        }
        throw new ExProductNotFound();
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

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public int getNumbers() {
		return numbersToImport;
	}

	public void setNumbers(int numbersToImport) {
		this.numbersToImport = numbersToImport;
	}
	
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	
	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	
   @Override
    public int compareTo(Product another) {
        return this.getProductName().compareTo(another.getProductName());
    }	
}
