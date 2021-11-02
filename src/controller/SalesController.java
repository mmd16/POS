package controller;

import java.time.LocalDate;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.Member;

public class SalesController implements Controller {
	private Employee employee;
	private Member member;
	private static SalesController instance;

	SalesController(Employee employee) {
		this.employee = employee;
	}
	
	public static void setInstance(Employee employee) {
		if (instance == null) {
			instance = new SalesController(employee);
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
		System.out.println("Welcome to SalesSystem.");
		boolean end = false;			
		do {
			System.out.println("Input commands for further sales management:");
			System.out.println("Input (1) for Checkout");
			System.out.println("Input (2) for refund");
			System.out.println("Input (3) for Listing Sales");
			System.out.println("Input (4) for checking Total income Today");
			System.out.println("Input (5) for exit");
			int input = MainController.sc.nextInt();
			switch(input) {
			case 1:
				checkout(member);
				break;
			case 2:
				refund();
				break;
			case 3:
				Sales.listSales();
				break;
			case 4:
				Sales.getTotalRevenue(LocalDate.now());
				break;
			case 5:
				System.out.println("Exiting...");
				end = true;
				break;
			}
		} while (end == false);
	}
	
	public boolean validator() {
		if (employee instanceof Manager) {
			return true;
		}
		return false;
	}
	
	public void refund() 
	{
		if(validator()) 
		{
			boolean isTrue = false;
			do {
				System.out.println("Please input the Order Reference Number:");
				String orderRefNo = MainController.sc.next();
				System.out.println("Please input the Product Name:");
				String productName = MainController.sc.next();
				System.out.println("Please input the Product Type:");
				String productType = MainController.sc.next();
				System.out.println("Please input the quantity that the customers would like to return:");
				int quantity = MainController.sc.nextInt();
				member.refund(orderRefNo, productName, productType, quantity, (Manager) employee);
				System.out.println("Task Completed.");
				System.out.println("Do you have any other actions to continue ?");
				System.out.print("Please input (0) to continue");
				System.out.print("Please input (1) to exit");
				isTrue = continuationValidator(MainController.sc.nextInt());
			}while(isTrue == false);
		}
		else 
		{
			System.out.println("Please ask your manager to handle.");
		}
	}
	
	
	public void checkout(Member member) 
	{
		if(member.isEmpty()==false) 
		{
			boolean isCompleted = false;
			do {
				boolean complete = false;
				member.listCart();
				System.out.print("Please input (0) for proceed to checkout");
				System.out.print("Please input (1) for remove products");
				System.out.print("Please input (2) for modify quantities of products");
				System.out.print("Please input (3) to cancel the checkout process");
				int input = MainController.sc.nextInt();
				switch(input) {
				case 0:
					member.checkout(employee);
					member.countFinalPrice();
					isCompleted = true;
					break;
				case 1:
					do 
					{
						System.out.println("Please input which products customers would like to remove from their cart.");
						int input2 = MainController.sc.nextInt() - 1;
						member.removeProductInCart(input2);
						System.out.println("Task Completed.");
						System.out.println("Do you have any other actions to continue ?");
						System.out.print("Please input (0) to continue");
						System.out.print("Please input (1) to exit");
						complete = continuationValidator(MainController.sc.nextInt());
					}while(complete == false);
					break;
				case 2:
					do 
					{
						System.out.println("Please input which products customers would like to modify from their cart.");
						int input2 = MainController.sc.nextInt() - 1;
						System.out.println("Please input the quantity that they would like to change.");
						int input3 = MainController.sc.nextInt();
						member.modifyNumofProductInCart(input2, input3);
						System.out.println("Task Completed.");
						System.out.println("Do you have any other actions to continue ?");
						System.out.print("Please input (0) to continue");
						System.out.print("Please input (1) to exit");
						complete = continuationValidator(MainController.sc.nextInt());
					}while(complete == false);
					break;
				case 3:
					System.out.println("Exiting the checkout process...");
					isCompleted = true;
					break;
				}
			}while(isCompleted == false);
		}
	}

	public boolean continuationValidator(int digit) 
	{
		if(digit == 0)
			return true;
		return false;
	}
	
	@Override
	public void setUser(Member member) {
		this.member = member;
		
	}
}
