package controller;

import java.text.ParseException;

import user.Employee;
import user.Member;

public interface Controller {
	public void execute() throws ParseException;
	public void setMemberAndEmployee(Employee employee,Member member);
}
