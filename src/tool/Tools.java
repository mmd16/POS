package tool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import db.InventoryDataBase;
import db.UserDataBase;
import exception.ExCartIsEmpty;
import exception.ExEmployeeIdNotExists;
import exception.ExInvalidInput;
import exception.ExMemberIdNotExists;
import membership.NonMembership;
import user.Cart;
import user.Member;

public class Tools {
	public static Tools instance = new Tools();
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	private static AtomicInteger uniqueId = new AtomicInteger();

	public static Tools getInstance() {
		return instance;
	}

	public Tools() {
	};

	public final static Scanner sc = new Scanner(System.in);
	private UserDataBase userDB = UserDataBase.getInstance();

	public int findMax(int[] array) {
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

	public boolean inputValidator(int min, int max, int input) {
		try {
			if (input > max || input < min)
				throw new ExInvalidInput();
			return true;
		} catch (ExInvalidInput e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean idValidator(String type, String uid) {
		try {
			switch (type) {
			case "Employee":
				if (userDB.getEmployee(uid) == null) {
					throw new ExEmployeeIdNotExists();
				} else {
					return true;
				}

			case "Member":
				if (userDB.getMember(uid) == null) {
					throw new ExMemberIdNotExists();
				}
				return true;
			}
		} catch (ExEmployeeIdNotExists e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ExMemberIdNotExists e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}

	public boolean continuationValidator(int digit) {
		if (digit == 0)
			return true;
		return false;
	}

	public String returnStrforMoments(int digit) {
		try {

			switch (digit) {
			case 0:
				return "Today";
			case 1:
				return "This Month";
			case 2:
				return "This Year";
			default:
				throw new ExInvalidInput();
			}
		} catch (ExInvalidInput e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public boolean checkCartisEmpty(ArrayList<Cart> cart) {
		try {
			if (cart.isEmpty()) {
				throw new ExCartIsEmpty();
			}
			return false;
		} catch (ExCartIsEmpty e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	public int getAge(int birthYear) {
		int year = LocalDate.now().getYear();
		return year - birthYear;
	}

	public String generateProductCode(String name, String type) {
		if (invenDB.searchProduct(name, type) != null) {
			return invenDB.searchProduct(name, type).getProductCode();
		} else {
			int temp = 0;
			String rslt = "";
			temp = uniqueId.getAndIncrement();
			rslt = type + String.valueOf(temp);
			return rslt;
		}

	}

	public String generateOrderRefNo(LocalDate date) {
		int temp = 0;
		String rslt = "";
		String datestr = date.toString();
		temp = uniqueId.getAndIncrement();
		rslt = datestr + String.valueOf(temp);
		return rslt;
	}

	public boolean checkMembership(Member member) {
		if (member.getMembership() instanceof NonMembership)
			return false;
		return true;
	}

}
