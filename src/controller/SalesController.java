package controller;

import java.time.LocalDate;
import java.util.Scanner;

import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.Trolley;
import user.User;
import product.Product;

public class SalesController implements Controller {
	private Employee employee;
	private static SalesController instance;

	private SalesController(Employee employee) {
		this.employee = employee;
	}
	
	public static void setInstance(Employee employee) {
		if (instance == null) {
			instance = new SalesController(employee);
		}
		else {
			// throw exception
		}
	}

	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		boolean end = false;			
		do {
			System.out.println("Input commands for further sales management:");
			String cmd = MainController.sc.next();
			switch (cmd) {
			case "listSales":
				if (!Sales.getSalesList().isEmpty()) {
					Sales.printList();
					System.out.println("The total revenue is " + Sales.getTotalRevenue() + ".");
				}
				else 
					System.out.println("Sorry, you haven't marked any sale records yet!");
				break;
			case "suggestSales":
				if (!Sales.getSalesList().isEmpty())
					// later do.
					System.out.println("");
				else
					System.out.println("Sorry, you haven't marked any sale records yet!");
				break;
			case "Exit":
				System.out.println("Leaving Sales System...");
				end = true;
				break;
			}
		} while (end == false);
	}
	
	public boolean validator() {
		if (employee instanceof Manager) {
			return true;
		}
		return false;
	}
}
