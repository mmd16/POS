package controller;

import java.time.LocalDate;
import java.util.Scanner;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.Trolley;
import user.User;
import product.Product;

public class CheckoutController implements Controller {
	private User user;
	private Employee employee;
	private String uid = "-1";
	private static CheckoutController instance;

	private CheckoutController(Employee employee, User user) {
		this.employee = employee;
		// set customer.
		this.user = user;
	}
	
	public static void setInstance(Employee employee, User user) {
		if (instance == null) {
			instance = new CheckoutController(employee, user);
		}
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
		System.out.println(user.getUserName() + "'s Payments: ");				
		do {
			System.out.println("Input commands for further payment management:");
			String cmd = MainController.sc.next();
			switch (cmd) {
			case "addTrolley":
				String pName = MainController.sc.next(); 
				int items = MainController.sc.nextInt();			
				String date = MainController.sc.next(); 			
				Product p = Product.searchProduct(pName);
				LocalDate pDate = user.ConvertStrToDate(date);
				
				if (Trolley.checkInventory(p, items))
					System.out.println("Customer's product numbers should not larger than its inventory numbers, please input the correct item numbers.");
				else {
					Trolley t = user.createTrolley(p, items, pDate);
					t.deductInventory(p);
					user.setIsCheckout(false);
				}
				break;
			case "checkout":				
				if(Trolley.checkTrolley()) 
					System.out.println("Sorry, you haven't picked up any products yet!");
				else 
				{
					user.checkout();
					Sales.markSales(Product.getCopyList(), Trolley.getTrolleyList(), employee);
					Trolley.clearTrolley();
					user.setIsCheckout(true);
				}
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
}

