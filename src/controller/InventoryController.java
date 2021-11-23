package controller;

import java.text.ParseException;

import staff.Employee;
import system.InventorySystem;
import tool.Tools;
import user.Member;

public class InventoryController implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static InventoryController instance;
	private InventorySystem inventory = InventorySystem.getInstance();
	private Tools tools = Tools.getInstance();

	// private function because it is singleton
	private InventoryController() {

	}

	public static InventoryController getInstance() {
		if (instance == null) {
			instance = new InventoryController();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() throws ParseException {
		System.out.printf("---Hi %s, Welcome to Inventory Management System.---\n", employee.getName());
		boolean end = false;
		do {
			System.out.println("---Input commands for further management---");
			System.out.println("Input (1) for addProducts");
			System.out.println("Input (2) for deleteProducts");
			System.out.println("Input (3) for check inventory");
			System.out.println("Input (4) for exit");
			int input = Tools.sc.nextInt();
			if(!tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				inventory.addProducts();
				break;
			case 2:
				inventory.deleteProduct();
				break;
			case 3:
				inventory.listInventory();
				break;
			case 4:
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
