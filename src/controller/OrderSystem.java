package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import staff.Employee;
import user.Member;
import user.Order;

public class OrderSystem{
//	private Member user;
//	private Employee employee; // thinking of separate general staff apart from manager
//	private static OrderController instance;
//	private static ArrayList<Member> orderUserList = new ArrayList<Member>();
//	
//	private OrderController (Member user) {
//		this.user = user;
//		// adding up all the ordering customers' data.
//		orderUserList.add(user);
//	}
//
//	public static void setInstance(Member user) {
//		if (instance == null)
//			instance = new OrderController (user);
//		else {
//			// throw exception
//		}
//	}
//	
//	public static Controller getInstance() {
//		return instance;
//	}
//
//	@Override
//	public void execute() {
//		boolean end = false;
//		System.out.println("Welcome to Order System!");
//		do {
//			System.out.println("Please input client's name.");
//			// input aero or karina for testing.
//			String name = MainController.sc.next();		
//			System.out.println("Input commands for further membership management:");
//			String cmd = MainController.sc.next();
//			switch (cmd) {		
//			case "suggest":	
//				//input the product produced date.
//				String pDate = MainController.sc.next(); 
//				LocalDate d1 = user.ConvertStrToDate(pDate);
//				Order.suggestMsgToSend(name, d1);            
//				break;
//			case "list": 
//				// later do.
//				user.listOrder();
//				break;
//			case "exit":
//				System.out.println("Leaving Order System...");
//				end = true;
//				break;
//			}
//		} while (end == false);
//	}
}
