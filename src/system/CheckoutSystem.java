package system;

import exception.ExFailInRefund;
import exception.ExInsufficientPayment;
import function.CheckoutFunctions;
import function.MembershipFunctions;
import staff.Employee;
import staff.Manager;
import tool.Tools;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Member;

public class CheckoutSystem {
	private static CheckoutSystem instance;
	private CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	private MembershipFunctions membershipFunction = new MembershipFunctions();
	private Tools tools = Tools.getInstance();
	private CheckoutSystem() {
	};

	public static CheckoutSystem getInstance() {
		if (instance == null) {
			instance = new CheckoutSystem();
			return instance;
		} else {
			return instance;
		}
	}

	public void refund(Member member, Employee employee) {
		try {
			if (checkoutFunction.validator(employee)) {
				boolean isTrue = false;
				do {
					System.out.println("Please input the Order Reference Number:");
					String orderRefNo = Tools.sc.next();
					System.out.println("Please input the Product Name:");
					String productName = Tools.sc.next();
					System.out.println("Please input the Product Type:");
					String productType = Tools.sc.next();
					System.out.println("Please input the quantity that the customers would like to return:");
					int quantity = Tools.sc.nextInt();
					CompletedCart completedCart = checkoutFunction.searchHistoryForRefund(orderRefNo, productName, productType, quantity,
							member);
					if (completedCart == null) {
						throw new ExFailInRefund();
					} else {
						checkoutFunction.refund(completedCart, quantity, (Manager) employee, member);
						System.out.println("---Task Completed.---");
						System.out.println("Do you have any other actions to continue ?");
						System.out.println("Please input (0) to exit");
						System.out.println("Please input (1) to continue");
						isTrue = tools.continuationValidator(Tools.sc.nextInt());
					}
				} while (isTrue == false);
			} else {
				System.out.println("Please ask your manager to handle.");
			}
		} catch (ExFailInRefund e) {
			System.out.println(e.getMessage());
		}
	}


	public void checkoutProcedure(Member member, Employee employee) {
		boolean isCompleted = false;
		do {
			listCart(member);
			System.out.print("\nPlease input (0) for proceed to checkout\n");
			System.out.print("Please input (1) for remove products\n");
			System.out.print("Please input (2) for modify quantities of products\n");
			System.out.print("Please input (3) to cancel the checkout process\n");
			int input = Tools.sc.nextInt();
			switch (input) {
			case 0:
				checkout(member, employee);
				isCompleted = true;
				break;
			case 1:
				removeProductInCart(member);
				break;
			case 2:
				modifyProductInCart(member);
				break;
			case 3:
				System.out.println("Exiting the checkout process...");
				isCompleted = true;
				break;
			}
		} while (isCompleted == false);

	}

	public void modifyProductInCart(Member member) {
		if (member.isCartEmpty() == false) {
			boolean complete = false;
			do {
				System.out.println("Please input which number of products customers would like to modify from their cart.");
				int input2 = Tools.sc.nextInt() - 1;
				System.out.println("Please input the quantity that they would like to change.");
				int input3 = Tools.sc.nextInt();
				checkoutFunction.updateCart(input2, input3, member);
				System.out.println("Task Completed.");
				System.out.println("Do you have any other actions to continue ?");
				System.out.println("Please input (0) to exit");
				System.out.println("Please input (1) to continue");
				complete = tools.continuationValidator(Tools.sc.nextInt());
			} while (complete == false);
		} else {
			System.out.println("Sorry, there is no products in cart");
		}
	}


	public void removeProductInCart(Member member) {
		boolean complete = false;
		do {
			System.out.println("Please input which number of products customers would like to remove from their cart.");
			int input2 = Tools.sc.nextInt() - 1;
			checkoutFunction.removeProductInCart(member, input2);
			System.out.println("Task Completed.");
			System.out.println("Do you have any other actions to continue ?");
			System.out.println("Please input (0) to exit");
			System.out.println("Please input (1) to continue");
			complete = tools.continuationValidator(Tools.sc.nextInt());
		} while (complete == false);
	}

	public void checkout(Member member, Employee employee) {
		try {
			double total = 0;
			total = checkoutFunction.countFinalPrice(member);
			System.out.printf("The total amount is: $%.2f\n", total);
			System.out.println("Please Input the amount of dollars that the customers paid");
			double cash = Tools.sc.nextDouble();
			if (checkoutFunction.changeForthePayment(total, cash)) {
				System.out.printf("Total changes is $%.2f\n", cash - total);
				Sales s = checkoutFunction.checkout(member, employee);
				membershipFunction.upgradeMembership(member);
				System.out.printf("The Order Reference Number is %s\n", s.getOrderRefNo());
			} else {
				throw new ExInsufficientPayment();
			}
		} catch (ExInsufficientPayment e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void confirmSales() {
		
	}
	public void listCart(Member member) {
		int temp = 1;
		System.out.printf("%-20s%-20s%-20s%-20s", "No", "Description", "Quantity", "Price($)");
		for (Cart c : member.getCart()) {
			System.out.printf("\n%-20d%-20s%-20s%-20s", temp, c.getProductName(), c.getQuantity(), c.getAllPrice());
			temp++;
		}
	}
	
}
