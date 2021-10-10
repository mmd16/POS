package controller;

import staff.Employee;

public class InventoryController implements Controller, Staff{
	
	private Employee employee;
	
	@Override
	public void execute() {
		
		
	}

	@Override
	public void setStaff(Employee employee) {
		this.employee = employee;
		
	}

}
