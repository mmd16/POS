package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.Trolley;
import user.User;
import product.Product;

public class SalesController implements Controller {
	private static User user;
	private Employee employee;
	private String uid = "-1";
	private static SalesController instance;

	private SalesController(Employee employee) {
		this.employee = employee;
	}

	public static void setInstance(Employee employee) {
		if (instance == null)
			instance = new SalesController(employee);
		else {
			// throw exception
		}
	}

	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		boolean end = false;
		//System.out.println(user.getUserName());				
		do {
			System.out.println("Input commands for further sales management (ex. addTrolley, checkout, markSales, listSales)");
			
			String cmd = MainController.sc.next();
			switch (cmd) {
			case "addTrolley":
				String pName = MainController.sc.next(); 
				String cName = MainController.sc.next(); 
				int items = MainController.sc.nextInt();
				
				// testing date, later will set date input, it is an advance function
				String s2 = "25/10/2021";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate d2 = LocalDate.parse(s2, formatter);
				// end
				
				Product p = Product.searchProduct(pName);
				User u = User.searchUserName(cName);
				
				if (Trolley.checkInventory(p, items))
					System.out.println("Customer's product numbers should not larger than its inventory numbers, please input the correct item numbers.");
				else {
					Trolley t = u.createTrolley(p, items, d2);
					t.deductInventory(p);
				}
				break;
			case "checkout":				
				if(Trolley.checkTrolley()) 
					System.out.println("Sorry, you haven't picked up any products");
				else 
				{
					User.checkout();
					
					// testing employee, later will set date input, it is an advance function
					Employee e = new Employee("You","You","You","You");
					// end
					
					User.markSales(Product.getCopyList(), Trolley.getTrolleyList(), e);
					Trolley.clearTrolley();
				}
				break;
			case "listSales":
				Sales.printList();
				break;
			case "Exit":
				System.out.println("Leaving Sales System...");
				end = true;
				break;
			}

		} while (end == false);
	}

	public void checkout() 
	{
		//user.checkout(employee, LocalDate.now());
		System.out.println("Do you have any problems about the total?");
		boolean reply = MainController.sc.nextBoolean();
		if(reply) 
		{
			user.confirmSale(employee, LocalDate.now());
		}
	}
	public void inputofUID() // on9 function
	{
		if (uid == "-1") {
			Scanner d = new Scanner(System.in);
			System.out.println("Please ask our customer to input their id, leave it as 0 if they are not the members.");
			uid = d.next();
			user = User.searchUserID(uid);
			d.close();
		}
	}

	public boolean validator() {
		if (employee instanceof Manager) {
			return true;
		}
		return false;
	}

	public void refund() {
		if (validator()) {
//			tobeimplemented
		}
		System.out.println("Please ask for your managers for help.");
	}


	public static void setUser(User u) {
		user = u;
		
	}

}
