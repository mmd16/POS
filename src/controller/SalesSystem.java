package controller;

import java.time.LocalDate;

import product.ProductFactory;
import staff.Employee;
import tool.Tools;
import transactions.Sales;
import user.Member;

public class SalesSystem implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static SalesSystem instance;

	// private function because it is singleton
	private SalesSystem() {
		
	}
	

	public static SalesSystem getInstance() {
		if (instance == null) {
			instance = new SalesSystem();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() {
		System.out.printf("Hi %s, Welcome to Sales System.\n", employee.getName());
		boolean end = false;			
		do {
			System.out.println("Input commands for further sales management:");
			System.out.println("Input (1) for Listing Sales");
			System.out.println("Input (2) for checking Total income");
			System.out.println("Input (3) for checking highest sales Product");
			// highest sales category among different age group will be implemented later
			System.out.println("Input (4) for exit");
			int input = Tools.sc.nextInt();
			if(!Tools.inputValidator(1, 4, input))
				continue;
			switch(input) {
			case 1:
				Sales.listSales();
				break;
			case 2:
				System.out.println("Input (1) for checking Total income Today");
				System.out.println("Input (2) for checking Total income this month");
				System.out.println("Input (3) for checking Total income this year");
				int input2 = Tools.sc.nextInt();
				Sales.getTotalRevenue(LocalDate.now(), input2);
				break;
			case 3:
				System.out.println("Input (1) for checking highest sales Product Today");
				System.out.println("Input (2) for checking highest sales Product month");
				System.out.println("Input (3) for checking highest sales Product year");
				int input3 = Tools.sc.nextInt();
				ProductFactory.printHighestSalesProduct(input3);
				break;
			case 4:
				System.out.println("Exiting...");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setMemberAndEmployee(Employee employee,Member member) {
		this.employee = employee;
		this.member = member;
		
	}
}	