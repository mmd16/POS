package controller;

import java.util.Scanner;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.User;

public class SalesController implements Controller, Staff, CurrentCustomer{
	private User user;
	private Employee employee;
	private String uid = "-1";
	@Override
	public void execute() {		
		boolean end = false;
		do 
		{
			System.out.println(user.getUsername());
			System.out.println("Input (1) for checkout");
			System.out.println("Input (2) for refund");
			System.out.println("Input (3) for checking Total Revenue");
			System.out.println("Input (4) to leave");
			int digits = MainController.sc.nextInt();
			switch (digits) {
			case 1:
				user.printOrders();
				//not yet implemented
				break;
			case 2:
				refund();
				break;
			case 3:
				Sales.getTotalRevenue();
				break;
			case 4:
				System.out.println("Leaving Sales System...");
				end = true;
				break;
			}
			
		}while(end == false);
	}
	
	public void inputofUID() // on9 function
	{
		if(uid == "-1") 
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Please ask our customer to input their id, leave it as 0 if they are not the members.");
			uid = d.next();
			user = User.searchUser(uid);
			d.close();
		}
	}
	
	public boolean validator() 
	{
		if(employee instanceof Manager)
		{
			return true;
		}
		return false;
	}
	
	public void refund() 
	{
		if(validator()) 
		{
//			tobeimplemented
		}
		System.out.println("Please ask for your managers for help.");
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
