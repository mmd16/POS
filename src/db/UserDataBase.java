package db;

import java.util.HashMap;
import java.util.Map;

import user.Employee;
import user.Member;

public class UserDataBase implements Database {

	private Map<String, Member> members;
	private Map<String, Employee> employees;

	private UserDataBase() {
		members = new HashMap<>();
		employees = new HashMap<>();
	};

	private static UserDataBase instance;

	public static UserDataBase getInstance() {
		if (instance == null) {
			instance = new UserDataBase();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public <T> void add(T p) {
		if (p instanceof Employee) {
			employees.put(((Employee) p).getWorkerid(), (Employee) p);
		} else if (p instanceof Member) {
			members.put(((Member) p).getUserid(), (Member) p);
		}else {
			;
		}
	}

	@Override
	public <T> void remove(T p) {
		if (p instanceof Employee) {
			employees.remove(((Employee) p).getWorkerid());
		} else if (p instanceof Member) {
			members.remove(((Member) p).getUserid());
		} else {
			;
		}
	}

	public Member getMember(String userId) {
		return members.get(userId);
	}

	public Employee getEmployee(String userId) {
		return employees.get(userId);
	}

	public boolean checkMemberIsEmpty() {
		return members.isEmpty();
	}

	public boolean checkEmployeeIsEmpty() {
		return employees.isEmpty();
	}

	public void clear() {
		employees.clear();
		members.clear();
	}
}
