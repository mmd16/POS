package tool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import exception.ExCartIsEmpty;
import exception.ExEmployeeIdNotExists;
import exception.ExInvalidInput;
import exception.ExMemberIdNotExists;
import staff.Employee;
import user.Cart;
import user.Member;

public class Tools {
	public final static Scanner sc = new Scanner(System.in);

	public static int findMax(int[] array) {
		int maxIndex = 0;
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			if (temp < array[i]) {
				temp = array[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public static LocalDate localDateFormatter(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

	public static boolean inputValidator(int min, int max, int input) {
		try {
			if (input > max || input < min)
				throw new ExInvalidInput();
			return true;
		} catch (ExInvalidInput e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean idValidator(String type, String uid) {
		try {
			switch (type) {
			case "Employee":
				if (Employee.searchEmployee(uid) == null) 
				{
					throw new ExEmployeeIdNotExists();
				}
				return true;
			case "Member":
				if (Member.getMember(uid) == null)
				{
					throw new ExMemberIdNotExists();
				}
				return true;
			default:
				return false;
			}
		} catch (ExEmployeeIdNotExists e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ExMemberIdNotExists e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean checkCartisEmpty(ArrayList<Cart> cart) 
	{
		try 
		{
			if(cart.isEmpty()) 
			{
				throw new ExCartIsEmpty();
			}
			return false;
		}catch (ExCartIsEmpty e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
}
