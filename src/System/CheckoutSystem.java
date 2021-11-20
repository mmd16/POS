package System;

import exception.ExFailInRefund;
import exception.ExInsufficientPayment;
import staff.Employee;
import staff.Manager;
import tool.Tools;
import user.CompletedCart;
import user.Member;

public class CheckoutSystem {

	public static void refund(Member member, Employee employee) {
		try {
			if (validator(employee)) {
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
					CompletedCart completedCart = searchHistoryForrefund(orderRefNo, productName, productType, quantity,
							member);
					if (completedCart == null) {
						throw new ExFailInRefund();
					} else {
						refund(completedCart, quantity, (Manager) employee, member);
						System.out.println("---Task Completed.---");
						System.out.println("Do you have any other actions to continue ?");
						System.out.println("Please input (0) to exit");
						System.out.println("Please input (1) to continue");
						isTrue = Tools.continuationValidator(Tools.sc.nextInt());
					}
				} while (isTrue == false);
			} else {
				System.out.println("Please ask your manager to handle.");
			}
		} catch (ExFailInRefund e) {
			System.out.println(e.getMessage());
		}
	}

	public static void refund(CompletedCart c, int quantity, Manager manager, Member member) {
		member.refund(c, quantity, manager);
	}

	public static CompletedCart searchHistoryForrefund(String orderRefNo, String productName, String productType,
			int quantity, Member member) {
		return member.searchHistoryForrefund(orderRefNo, productName, productType, quantity);
	}

	public static void checkoutProcedure(Member member, Employee employee) {
		boolean isCompleted = false;
		do {
			member.listCart();
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

	public static void modifyProductInCart(Member member) {
		if (member.isEmpty() == false) {
			boolean complete = false;
			do {
				System.out.println("Please input which products customers would like to modify from their cart.");
				int input2 = Tools.sc.nextInt() - 1;
				System.out.println("Please input the quantity that they would like to change.");
				int input3 = Tools.sc.nextInt();
				member.updateCart(input2, input3);
				System.out.println("Task Completed.");
				System.out.println("Do you have any other actions to continue ?");
				System.out.println("Please input (0) to exit");
				System.out.println("Please input (1) to continue");
				complete = Tools.continuationValidator(Tools.sc.nextInt());
			} while (complete == false);
		} else {
			System.out.println("Sorry, there is no products in cart");
		}
	}

	public static boolean validator(Employee employee) {
		if (employee instanceof Manager) {
			return true;
		}
		return false;
	}

	public static void removeProductInCart(Member member) {
		boolean complete = false;
		do {
			System.out.println("Please input which products customers would like to remove from their cart.");
			int input2 = Tools.sc.nextInt() - 1;
			member.removeProductInCart(input2);
			System.out.println("Task Completed.");
			System.out.println("Do you have any other actions to continue ?");
			System.out.println("Please input (0) to exit");
			System.out.println("Please input (1) to continue");
			complete = Tools.continuationValidator(Tools.sc.nextInt());
		} while (complete == false);
	}

	public static void checkout(Member member, Employee employee) {
		try {
			double total = 0;
			total = member.countFinalPrice();
			System.out.printf("The total amount is: $%.2f\n", total);
			System.out.println("Please Input the amount of dollars that the customers paid");
			double cash = Tools.sc.nextDouble();
			if (changeForthePayment(total, cash)) {
				System.out.printf("Total changes is $%.2f\n", cash - total);
				member.checkout(employee);
			} else {
				throw new ExInsufficientPayment();
			}
		} catch (ExInsufficientPayment e) {
			System.out.println(e.getMessage());
		}

	}

	public static boolean changeForthePayment(double total, double cash) {
		boolean enough = true;
		enough = (cash >= total) ? true : false;
		return enough;
	}
}
