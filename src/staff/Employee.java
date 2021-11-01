package staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import transactions.Sales;
import user.Cart;
import user.Member;

public class Employee {
	private String name;
	private String sex;
	private String email;
	private String phonenum;
	private String workerid;
	private static AtomicInteger uniqueId =new AtomicInteger();
	private static ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
	
	public Employee(String name, String sex, String email, String phonenum) 
	{
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = String.valueOf(uniqueId.getAndIncrement());
		EmployeeList.add(this);
	}
	public Employee(String name, String sex, String email, String phonenum, String test) 
	{
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = test;
		EmployeeList.add(this);
	}

	public Sales confirmSales(Cart c, Member member, String orderRefNo){
		Sales s = new Sales(c.getProductName(), c.getProductCode(), c.getQuantity(), LocalDate.now(), this, c.getAllPrice(), member.applyDiscount(c.getAllPrice()));
		member.addProductToCompletedCart(c, orderRefNo , s.getSalesCode());
		return s;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getWorkerid() {
		return workerid;
	}
	
	public static Employee searchEmployee(String uid) 
	{ 
		for(Employee employee: EmployeeList) 
		{
			if(employee.getWorkerid().equals(uid)) 
			{
				return employee;
			}
		}
		return null;	
	}
	
}
