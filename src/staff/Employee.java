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

	public Employee(String name, String sex, String email, String phonenum) {
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = String.valueOf(uniqueId.getAndIncrement());
	}

	public Employee(String name, String sex, String email, String phonenum, String test) {
		this.name = name;
		this.sex = sex;
		this.phonenum = phonenum;
		this.workerid = test;
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

}
