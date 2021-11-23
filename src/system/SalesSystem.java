package system;

import java.time.LocalDate;

import db.InventoryDataBase;
import db.SalesDataBase;
import exception.ExNoSalesExists;
import exception.ExNoSalesInSelectedAge;
import function.SalesFunction;
import product.Product;
import tool.Tools;

public class SalesSystem {
	private static SalesSystem instance;
	private SalesFunction salesFunction = new SalesFunction();
	private Tools tools = Tools.getInstance();
	private SalesSystem() {
	};

	public static SalesSystem getInstance() {
		if (instance == null) {
			instance = new SalesSystem();
			return instance;
		} else {
			return instance;
		}
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
					double percentage = salesFunction.getSalesPercentageForProduct(daysUnit, product, ageFilter,
							ageGroupUnit, salesFunction.getTotalSalesNum());
					System.out.printf("The highest sales product for %s is %s\n", tools.returnStrforMoments(daysUnit),
							product.getName());
					System.out.printf("The Sales Percentage for %s in %s is %.2f %%\n", product.getName(),
							tools.returnStrforMoments(daysUnit), percentage);
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
			input3 = Tools.sc.nextInt();
			isEnd = tools.inputValidator(0, 3, input3);
		} while (isEnd == false);
		return input3;

	}

}
