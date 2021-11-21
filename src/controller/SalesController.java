package controller;

import java.time.LocalDate;

import System.SalesSystem;
import product.Product;
import product.ProductFactory;
import staff.Employee;
import tool.Tools;
import transactions.Sales;
import user.Member;

public class SalesController implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static SalesController instance;
	private SalesSystem sales = SalesSystem.getInstance();
	// private function because it is singleton
	private SalesController() {

	}

	public static SalesController getInstance() {
		if (instance == null) {
			instance = new SalesController();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() {
		System.out.printf("---Hi %s, Welcome to Sales System.\n---", employee.getName());
		boolean end = false;
		do {
			System.out.println("---Input commands for further management---");
			System.out.println("Input (1) for Listing Sales");
			System.out.println("Input (2) for checking Total income");
			System.out.println("Input (3) for checking highest sales Product");
			// highest sales category among different age group will be implemented later
			System.out.println("Input (4) for exit");
			int input = Tools.sc.nextInt();
			if (!Tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				sales.listSales();
				break;
			case 2:
				sales.checkForTotalIncome();
				break;
			case 3:
				sales.checkForHighestSalesProductAndPercentage();
				break;
			case 4:
				System.out.println("Exiting...");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setMemberAndEmployee(Employee employee, Member member) {
		this.employee = employee;
		this.member = member;

	}
}