package controller;

import staff.Employee;
import user.User;

public class SalesController implements Controller, CurrentCustomer, Staff{
	private User user;
	private Employee employee;
	
	@Override
	public void execute() {
		System.out.println("Welcome to Sales System");
	}
	
	@Override
	public void setStaff(Employee employee) {
		this.employee = employee;
		
	}
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
}
