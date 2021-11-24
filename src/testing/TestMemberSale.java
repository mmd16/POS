package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import product.Product;
import staff.Employee;
import transactions.MemberSale;
import user.Member;

public class TestMemberSale {
	@Test
	void testGetMember() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001",mem);
		assertEquals(mem, sale.getMember());
	}
	
	@Test
	void testSetMember() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001",mem);
		sale.setMember(bam);
		assertEquals(bam, sale.getMember());
	}
}
