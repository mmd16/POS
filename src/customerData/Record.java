package customerData;

import java.util.ArrayList;
import java.util.Collections;

import customerRequestHandler.Order;
import exception.ExCustomerNotFound;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
	private ArrayList<Customer> allCustomers;
	private ArrayList<Order> allOrders;
	ArrayList<Order> alltempOrders;
	private static Record instance = new Record();

	public Record (){
		allCustomers = new ArrayList<Customer>();
		allOrders = new ArrayList<Order>();
		alltempOrders = new ArrayList<>();
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
    
    public Order createOrder(String userName, LocalDate orderDate, int deliveryDays) throws ExCustomerNotFound {
    	Customer c = Customer.searchCustomer(allCustomers, userName);
    	Order o = new Order(c, orderDate, deliveryDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate produceDate =  LocalDate.parse("10/08/2021", formatter);
    	o.suggestMsgToSend(c, o, produceDate);
        allOrders.add(o);
        Collections.sort(allOrders);
        return o;
    }
    
    public void suggestMsgToSend() {
    	Order.listOrder(allOrders);
    }
    
    public void addCustomer(Customer customer) {
        allCustomers.add(customer);
        Collections.sort(allCustomers);
    }
    
	public LocalDate ConvertStrToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate result = LocalDate.parse(date, formatter);
		return result;
	}	
}
