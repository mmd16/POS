package staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import membership.NonMembership;
import product.Product;
import transactions.MemberSale;
import transactions.Sales;
import user.Cart;
import user.Member;

public class Employee {
	private String name;
	private String sex;
	private String email;
	private String phonenum;
	private String workerid;
	private static AtomicInteger uniqueId = new AtomicInteger();
	private static ArrayList<Employee> EmployeeList = new ArrayList<Employee>();

	public Employee(String name, String sex, String email, String phonenum) {
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = String.valueOf(uniqueId.getAndIncrement());
		EmployeeList.add(this);
	}

	public Employee(String name, String sex, String email, String phonenum, String test) {
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = test;
		EmployeeList.add(this);
	}
/**
 * During each transactions, it might have some modifications, it is to confirm the sales is finished, so it can be added to sales static array
 * @param c
 * @param member
 * @param orderRefNo
 * @return sales object
 */
	public Sales confirmSales(Cart c, Member member, String orderRefNo) {
		double sellingPrice = member.applyDiscount(c.getAllPrice());
		if(member.getMembership() instanceof NonMembership) 
		{
			Sales s = new Sales(c.getProduct(), c.getQuantity(), LocalDate.now(), this,
					c.getAllPrice(), sellingPrice, orderRefNo);
			member.addAccumulatedSpending(sellingPrice);
			Product p = c.getProduct();
			p.deductInventoryofProductsFromQueue(c.getQuantity());
			p.addSales(s);
			return s;
		}
		else 
		{
			MemberSale s = new MemberSale(c.getProduct(), c.getQuantity(), LocalDate.now(), this,
				c.getAllPrice(), member.applyDiscount(c.getAllPrice()), orderRefNo, member);
			member.addAccumulatedSpending(sellingPrice);
			member.addProductToCompletedCart(c, orderRefNo, s.getSalesCode());
			Product p = c.getProduct();
			p.deductInventoryofProductsFromQueue(c.getQuantity());
			p.addSales(s);
			return s;
		}
	}
/**
 * 
 * @param uid
 * @return employee
 */
	public static Employee searchEmployee(String uid) {
		for (Employee employee : EmployeeList) {
			if (employee.getWorkerid().equals(uid)) {
				return employee;
			}
		}
		return null;
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

	public void setWorkerid(String workerid) {
		this.workerid = workerid;
	}

	public static AtomicInteger getUniqueId() {
		return uniqueId;
	}

	public static void setUniqueId(AtomicInteger uniqueId) {
		Employee.uniqueId = uniqueId;
	}

	public static ArrayList<Employee> getEmployeeList() {
		return EmployeeList;
	}

	public static void setEmployeeList(ArrayList<Employee> employeeList) {
		EmployeeList = employeeList;
	}

}
