package main;

import java.util.*;

import command.CmdCreateCustomer;
import command.CmdCreateOrder;
import command.CmdCreateProduct;
import command.CmdSuggestMsgSentToCustomer;
import exception.ExWrongCommand;

public class Main {
	public static void main(String [] args) throws CloneNotSupportedException {
	    Scanner input = new Scanner(System.in);	
	    System.out.println("Welcome to POS System");
	    boolean exit = false;

        while (!exit) {        
        	try {
        		System.out.println("Please enter your command: ");
            	String cmd = input.nextLine(); 
                if (cmd.equals(""))
                    continue;
                String[] cmdParts = cmd.split(" ");  
                
                if (cmdParts[0].equals("createCustomer")) {
                    (new CmdCreateCustomer()).execute(cmdParts);
                } 
                else if (cmdParts[0].equals("createOrder")) {
                	(new CmdCreateOrder()).execute(cmdParts);
                }
                else if (cmdParts[0].equals("suggestOrder")) {
                	(new CmdSuggestMsgSentToCustomer()).execute(cmdParts);
                }
                else if (cmdParts[0].equals("createProduct")) {
                	(new CmdCreateProduct()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }                               
        	}
        	catch (Exception e) {
        		System.out.println(e.getMessage());
        	}
        }
        input.close();
	}
}
