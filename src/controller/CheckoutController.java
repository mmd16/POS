package controller;

import exception.ExCartIsEmpty;
import exception.ExFailInRefund;
import exception.ExInsufficientPayment;
import exception.ExProductNotExist;
import function.CheckoutFunctions;
import function.MembershipFunctions;
import membership.NonMembership;
import tool.Tools;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Employee;
import user.Manager;
import user.Member;

public class CheckoutController implements Controller {
	private Employee employee;
	private Member member;
	private static CheckoutController instance;
	private CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	private MembershipFunctions membershipFunction = new MembershipFunctions();
	private Tools tools = Tools.getInstance();

	// private function because it is singleton
	private CheckoutController() {

	}

	public static CheckoutController getInstance() {
		if (instance == null) {
			instance = new CheckoutController();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() {
		System.out.printf("Welcome to Checkout System, %s\n", employee.getName());
		boolean end = false;
		do {
			System.out.println("Input commands for further management:");
			System.out.println("Input (1) for Checkout");
			System.out.println("Input (2) for refund");
			System.out.println("Input (3) for exit");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input commands for further management:");
				System.out.println("Input (1) for Checkout");
				System.out.println("Input (2) for refund");
				System.out.println("Input (3) for exit");
				Tools.sc.next();
			}
			int input = Tools.sc.nextInt();
			Tools.sc.nextLine();
			if (!tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				checkoutProcedure(member, employee);
				break;
			case 2:
				refund(member, employee);
				break;
			case 3:
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

	public void refund(Member member, Employee employee) {

		if (checkoutFunction.validator(employee)) {
			boolean isTrue = false;
			do {
				try {
					System.out.println("Please input the Order Reference Number:");
					String orderRefNo = Tools.sc.next();
					System.out.println("Please input the Product Name:");
					String productName = Tools.sc.next();
					System.out.println("Please input the Product Type:");
					String productType = Tools.sc.next();
					System.out.println("Please input the quantity that the customers would like to return:");
					while (!Tools.sc.hasNextInt()) {
						System.out.println("*** Input Error! ***");
						System.out.println("Please input the quantity that the customers would like to return:");
						Tools.sc.next();
					}
					int quantity = Tools.sc.nextInt();
					if (member.getMembership() instanceof NonMembership) {
						checkoutFunction.refund(orderRefNo, quantity, (Manager) employee);
					} else {
						CompletedCart completedCart = checkoutFunction.searchHistoryForRefund(orderRefNo, productName,
								productType, quantity, member);
						if (completedCart == null) {
							throw new ExFailInRefund();
						} else {
							checkoutFunction.refund(completedCart, quantity, (Manager) employee, member);
							System.out.println("*** Task Completed ***");
							System.out.println("Do you have any other actions to continue ?");
							System.out.println("Please input (0) to exit");
							System.out.println("Please input (1) to continue");
							isTrue = tools.continuationValidator(Tools.sc.nextInt());
						}
					}
				} catch (ExFailInRefund e) {
					System.out.print(e.getMessage());
					isTrue = true;
				} catch (Exception e) {
					System.out.println("*** Input Error! ***");
				}
			} while (isTrue == false);
		} else {
			System.out.println("Please ask your manager to handle.");
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
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.print("Please input (0) for proceed to checkout\n");
				System.out.print("Please input (1) for remove products\n");
				System.out.print("Please input (2) for modify quantities of products\n");
				System.out.print("Please input (3) to cancel the checkout process\n");
				Tools.sc.next();
			}
			int input = Tools.sc.nextInt();
			Tools.sc.nextLine();
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
		try {
			if (member.getCart().isEmpty() == false) {
				boolean complete = false;
				do {
					System.out.println(
							"Please input the name of products customers would like to modify from their cart.");
					String name = Tools.sc.nextLine();
					System.out.println("Please input the quantity that they would like to change.");
					while (!Tools.sc.hasNextInt()) {
						System.out.println("That's not a number!");
						System.out.println("Please input the quantity that they would like to change.");
						Tools.sc.next();
					}
					int numbers = Tools.sc.nextInt();
					Cart c = checkoutFunction.SearchCart(member, name);
					if (c == null) {
						throw new ExProductNotExist();
					} else {
						checkoutFunction.updateCart(c, numbers);
						System.out.println("*** Task Completed ***");
						System.out.println("Do you have any other actions to continue ?");
						System.out.println("Please input (0) to exit");
						System.out.println("Please input (1) to continue");
						while (!Tools.sc.hasNextInt()) {
							System.out.println("*** Input Error! ***");
							System.out.println("Do you have any other actions to continue ?");
							System.out.print("Please input (1) to continue \nPlease input (0) to exit\n");
							Tools.sc.next();
						}
						complete = tools.continuationValidator(Tools.sc.nextInt());
						Tools.sc.nextLine();
					}
				} while (complete == false);
			} else {
				System.out.println("Sorry, there is no products in cart");
			}
		} catch (ExProductNotExist e) {
			System.out.print(e.getMessage());
		}

	}

	public void removeProductInCart(Member member) {
		try {
			boolean complete = false;
			do {
				System.out.println("Please input the name of products customers would like to remove from their cart.");
				String name = Tools.sc.nextLine();
				Cart c = checkoutFunction.SearchCart(member, name);
				if (c == null) {
					throw new ExProductNotExist();
				} else {
					checkoutFunction.removeProductInCart(member, c);
					System.out.println("Task Completed.");
					System.out.println("Do you have any other actions to continue ?");
					System.out.println("Please input (0) to exit");
					System.out.println("Please input (1) to continue");
					while (!Tools.sc.hasNextInt()) {
						System.out.println("*** Input Error! ***");
						System.out.println("Do you have any other actions to continue ?");
						System.out.print("Please input (1) to continue \nPlease input (0) to exit\n");
						Tools.sc.next();
					}
					complete = tools.continuationValidator(Tools.sc.nextInt());
					Tools.sc.nextLine();
				}
			} while (complete == false);
		} catch (ExProductNotExist e) {
			System.out.print(e.getMessage());
		}

	}

	public void checkout(Member member, Employee employee) {
		try {
			if (member.getCart().isEmpty()) {
				throw new ExCartIsEmpty();
			} else {
				double total = 0;
				total = checkoutFunction.countFinalPrice(member);
				System.out.printf("The total amount is: $%.2f\n", total);
				System.out.println("Please Input the amount of dollars that the customers paid");
				while (!Tools.sc.hasNextDouble()) {
					System.out.println("*** Input Error! ***");
					System.out.println("Please Input the amount of dollars that the customers paid");
					Tools.sc.next();
				}
				double cash = Tools.sc.nextDouble();
				if (checkoutFunction.changeForthePayment(total, cash)) {
					System.out.printf("Total changes is $%.2f\n", cash - total);
					Sales s = checkoutFunction.checkout(member, employee);
					membershipFunction.upgradeMembership(member);
					System.out.printf("The Order Reference Number is %s\n", s.getOrderRefNo());
				} else {
					throw new ExInsufficientPayment();
				}
			}
		} catch (ExInsufficientPayment e) {
			System.out.println(e.getMessage());
		} catch (ExCartIsEmpty e) {
			System.out.println(e.getMessage());
		}
	}

	public void listCart(Member member) {
		if (member.getCart().isEmpty()) {
			System.out.print("The Cart is Empty\n");
		} else {
			int temp = 1;
			System.out.printf("%-20s%-20s%-20s%-20s", "No", "Description", "Quantity", "Price($)");
			for (Cart c : member.getCart()) {
				System.out.printf("\n%-20d%-20s%-20s%-20s", temp, c.getProductName(), c.getQuantity(), c.getAllPrice());
				temp++;
			}
		}
	}

}
