package customerData;

import java.util.ArrayList;
import java.util.Collections;

import customerRequestHandler.Order;
import exception.ExCustomerNotFound;
import exception.ExProductNotFound;
import product.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
	private ArrayList<Customer> allCustomers;
	private ArrayList<Order> allOrders;
	private ArrayList<Product> allProducts;
	private static Record instance = new Record();

	public Record (){
		allCustomers = new ArrayList<Customer>();
		allOrders = new ArrayList<Order>();
		allProducts = new ArrayList<>();
	}	
	
    public static Record getInstance() {
        return instance;
    }

    public Customer createCustomer(String username, char sex, String membership) {
    	Customer c = new Customer(username, sex, membership);
        allCustomers.add(c);
        Collections.sort(allCustomers);
        return c;
    }
    
    public Order createOrder(String userName, String productName, LocalDate orderDate, int deliveryDays) throws ExCustomerNotFound, ExProductNotFound {
    	Customer c = Customer.searchCustomer(allCustomers, userName);
    	Order o = new Order(c, orderDate, deliveryDays);
        Product p = Product.searchProduct(allProducts, productName);
        
    	o.suggestMsgToSend(c, o, p.getDispatchDate());
        allOrders.add(o);
        Collections.sort(allOrders);
        return o;
    }
    
    public Product createOnlineProduct(String productName, double fee, String inventoryStatus, LocalDate dispatchDate, int numbersToImport) {
    	Product p = new Product(productName, fee, inventoryStatus, dispatchDate, numbersToImport);   
        allProducts.add(p);
        Collections.sort(allProducts);
        return p;
    }
    
    public Product createPhysicalProduct(String productName, double fee, String inventoryStatus) {
    	Product p = new Product(productName, fee, inventoryStatus);   	
        allProducts.add(p);
        Collections.sort(allProducts);
        return p;
    }
    
    public void suggestMsgToSend() {
    	Order.listOrder(allOrders);
    }
    
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
	}	
	
	
	public boolean inventoryIsEmpty(String inventoryStatus) {
		if (inventoryStatus.equals("Empty")) {
			return true;
		}
		else 	
			return false;
	}
}
