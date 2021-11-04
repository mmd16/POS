package controller;

import java.text.ParseException;

import staff.Employee;
import user.Member;

public interface Controller {
	public void execute() throws ParseException;
	public void setMemberAndEmployee(Employee employee,Member member);
}
