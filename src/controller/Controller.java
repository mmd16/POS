package controller;

import staff.Employee;
import user.Member;

public interface Controller {
	public void execute();
	public void setMemberAndEmployee(Employee employee,Member member);
}
