package controller;

import java.time.LocalDate;
import java.util.Scanner;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.User;

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
		do {
			System.out.println(user.getUserName());
			System.out.println("Input (1) for checkout");
			System.out.println("Input (2) for refund");
			System.out.println("Input (3) for checking Total Revenue");
			System.out.println("Input (4) to leave");
			int digits = MainController.sc.nextInt();
			switch (digits) {
			case 1:
				if(user.getBag().checkifEmpty()) 
				{
					System.out.println("Sorry, you haven't picked up any products");
				}
				else 
				{
					checkout();
				}
				break;
			case 2:
                                // I didn't plan to make a refund method for Sales, so maybe delete this part.
				refund();
				break;
			case 3:
				Sales.getTotalRevenue();
				break;
			case 4:
				System.out.println("Leaving Sales System...");
				end = true;
				break;
			}

		} while (end == false);
	}

	public void checkout() 
	{
		user.checkout(employee, LocalDate.now());
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
