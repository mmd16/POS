package controller;

import java.time.LocalDate;

import exception.ExNoSalesExists;
import exception.ExNoSalesInSelectedAge;
import function.SalesFunction;
import product.Product;
import tool.Tools;
import user.Employee;
import user.Member;

public class SalesController implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static SalesController instance;
	private Tools tools = Tools.getInstance();
	private SalesFunction salesFunction = new SalesFunction();

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
		System.out.printf("Hi %s, Welcome to Sales System.\n", employee.getName());
		boolean end = false;
		do {
			System.out.println("Input commands for further management");
			System.out.println("Input (1) for Listing Sales");
			System.out.println("Input (2) for checking Total income");
			System.out.println("Input (3) for checking highest sales Product");
			System.out.println("Input (4) for exit");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input commands for further management");
				System.out.println("Input (1) for Listing Sales");
				System.out.println("Input (2) for checking Total income");
				System.out.println("Input (3) for checking highest sales Product");
				System.out.println("Input (4) for exit");
				Tools.sc.next();
			}
			int input = Tools.sc.nextInt();
			Tools.sc.nextLine();
			if (!tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				listSales();
				break;
			case 2:
				checkForTotalIncome();
				break;
			case 3:
				checkForHighestSalesProductAndPercentage();
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

	public void listSales() {
		salesFunction.listSales();
	}

	public void checkForTotalIncome() {
		int daysUnit = getCheckIncomeDaysUnit();
		double total = salesFunction.getTotalRevenue(LocalDate.now(), daysUnit);
		System.out.printf("The Total Revenue for %s is %.2f\n", tools.returnStrforMoments(daysUnit), total);
	}

	public void checkForHighestSalesProductAndPercentage() {
		try {
			String str = "";
			String str2 = "";
			if (salesFunction.checkSalesIsEmpty()) {
				throw new ExNoSalesExists();
			} else {
				int daysUnit = getCheckSalesDaysUnit();
				int ageGroupUnit = getCheckAgeGroupDigit();
				boolean ageFilter = (ageGroupUnit == 0) ? false : true;
				Product product = salesFunction.printHighestSalesProduct(daysUnit, ageFilter, ageGroupUnit);
				if (product == null) {
					throw new ExNoSalesInSelectedAge();
				} else {
					for (Product p : salesFunction.getHighestSalesProductList()) {
						double percentage = salesFunction.getSalesPercentageForProduct(daysUnit, p, ageFilter,
								ageGroupUnit, salesFunction.getTotalSalesNum());
						str += p.getName() + ", ";
						str2 += percentage + "%, ";
					}
					System.out.printf("The highest sales product for %s : %s\n", tools.returnStrforMoments(daysUnit),
							str.substring(0, str.length() - 2));
					System.out.printf("The Sales Percentage for %s in %s is %s \n", str.substring(0, str.length() - 2),
							tools.returnStrforMoments(daysUnit), str2.substring(0, str2.length() - 2));
				}
			}
		} catch (ExNoSalesExists e) {
			System.out.println(e.getMessage());
		} catch (ExNoSalesInSelectedAge e) {
			System.out.println(e.getMessage());
		}

	}

	public int getCheckSalesDaysUnit() {
		boolean isEnd = false;
		int input4 = 0;
		do {
			System.out.println("Input (0) for checking highest sales Product Today");
			System.out.println("Input (1) for checking highest sales Product month");
			System.out.println("Input (2) for checking highest sales Product year");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input (0) for checking highest sales Product Today");
				System.out.println("Input (1) for checking highest sales Product month");
				System.out.println("Input (2) for checking highest sales Product year");
				Tools.sc.next();
			}
			input4 = Tools.sc.nextInt();
			isEnd = tools.inputValidator(0, 2, input4);
		} while (isEnd == false);
		return input4;

	}

	public int getCheckIncomeDaysUnit() {
		boolean isEnd = false;
		int input4 = 0;
		do {
			System.out.println("Input (0) for checking Total income Today");
			System.out.println("Input (1) for checking Total income this month");
			System.out.println("Input (2) for checking Total income this year");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input (0) for checking Total income Today");
				System.out.println("Input (1) for checking Total income this month");
				System.out.println("Input (2) for checking Total income this year");
				Tools.sc.next();
			}
			input4 = Tools.sc.nextInt();
			isEnd = tools.inputValidator(0, 2, input4);

		} while (isEnd == false);
		return input4;

	}

	public int getCheckAgeGroupDigit() {
		boolean isEnd = false;
		int input3 = 0;
		do {
			System.out.println("Input (0) for overall highest sales Product");
			System.out.println("Input (1) for highest sales Product for Teenagers");
			System.out.println("Input (2) for highest sales Product for Adult");
			System.out.println("Input (3) for highest sales Product for Elderly");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input (0) for overall highest sales Product");
				System.out.println("Input (1) for highest sales Product for Teenagers");
				System.out.println("Input (2) for highest sales Product for Adult");
				System.out.println("Input (3) for highest sales Product for Elderly");
				Tools.sc.next();
			}
			input3 = Tools.sc.nextInt();
			isEnd = tools.inputValidator(0, 3, input3);
		} while (isEnd == false);
		return input3;

	}
}