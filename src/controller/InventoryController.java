package controller;

import staff.Employee;
import user.User;

public class InventoryController implements Controller {

	private static InventoryController instance;
	private static User user;
	private Employee employee;
	private String uid = "-1";

	private InventoryController(Employee employee) {
		this.employee = employee;
		this.user = user;
	}

	public static void setInstance(Employee employee) {
		if (instance == null)
			instance = new InventoryController(employee);
		else {
			// throw exception
		}
	}
	
	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {

	}
	

	public static void setUser(User u) {
		user = u;
		
	}

}
