package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import ageGroup.Adult;
import ageGroup.AgeGroup;
import ageGroup.Teenagers;
import db.InventoryDataBase;
import product.Product;
import product.ProductFactory;
import transactions.MemberSale;
import transactions.Sales;
import user.Employee;
import user.Member;

public class TestInventoryDataBase {
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();

	private ProductFactory productFactory = ProductFactory.getInstance();

	PrintStream oldPrintStream;

	ByteArrayOutputStream bos;

	ByteArrayInputStream testIn;

	@Test
	void testSearchProduct_0() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		assertEquals(coke, this.invenDB.searchProduct("Coca Cola", "Drinks"));
	}

	@Test
	void testSearchProduct_1() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		assertEquals(null, this.invenDB.searchProduct("Pepsi Cola", "Drinks"));
	}

	@Test
	void testSearchProduct_2() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		assertEquals(null, this.invenDB.searchProduct("Coca Cola", "Food"));
	}

	@Test
	void testCountSales_0() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, coke));
	}

	@Test
	void testCountSales_0a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-19"), 0, coke));
	}

	@Test
	void testCountSales_1() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-30"), 1, coke));
	}

	@Test
	void testCountSales_1a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-11-23"), 1, coke));
	}

	@Test
	void testCountSales_1b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-12-23"), 1, coke));
	}

	@Test
	void testCountSales_2() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0002");
		coke.addSales(sale2);
		Sales sale3 = new Sales(coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0, 20.0, "0003");
		coke.addSales(sale3);
		assertEquals(2, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, coke));
	}

	@Test
	void testCountSales_2a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0002");
		coke.addSales(sale2);
		Sales sale3 = new Sales(coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0, 20.0, "0003");
		coke.addSales(sale3);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2020-12-21"), 2, coke));
	}

	@Test
	void testCountSales_3() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0002");
		coke.addSales(sale2);
		Sales sale3 = new Sales(coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0, 20.0, "0003");
		coke.addSales(sale3);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), -1, coke));
	}

	@Test
	void testCountSalesAge_0() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		MemberSale sale3 = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0003", mem2);
		coke.addSales(sale3);
		assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, adult, coke));
	}

	@Test
	void testCountSalesAge_0a() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		MemberSale sale3 = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0003", mem2);
		coke.addSales(sale3);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-20"), 0, adult, coke));
	}

	@Test
	void testCountSalesAge_0b() {
		setup();
		Teenagers teen = new Teenagers();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, (AgeGroup) teen, coke));
	}

	@Test
	void testCountSalesAge_0c() {
		setup();
		Teenagers teen = new Teenagers();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, (AgeGroup) teen, coke));
	}

	@Test
	void testCountSalesAge_1() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		assertEquals(2, this.invenDB.countSales(LocalDate.parse("2021-12-30"), 1, adult, coke));
	}

	@Test
	void testCountSalesAge_1a() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-11-30"), 1, adult, coke));
	}

	@Test
	void testCountSalesAge_1b() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2020-12-30"), 1, adult, coke));
	}

	@Test
	void testCountSalesAge_1c() {
		setup();
		Teenagers teen = new Teenagers();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0, 20.0, "0002", mem);
		coke.addSales(sale2);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-30"), 1, teen, coke));
	}

	@Test
	void testCountSalesAge_1d() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-30"), 1, adult, coke));
	}

	@Test
	void testCountSalesAge_2() {
		setup();
		Adult adult = new Adult();
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-30"), 2, adult, coke));
	}

	@Test
	void testCountSalesAge_2a() {
		setup();
		Adult adult = new Adult();
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-12-21"), 2, adult, coke));
	}

	@Test
	void testCountSalesAge_2b() {
		setup();
		Teenagers teens = new Teenagers();
		Member mem = new Member("John", "1999", "M", "JohnDoe@gmail.com");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, teens, coke));
	}

	@Test
	void testCountSalesAge_2c() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, adult, coke));
	}

	@Test
	void testCountSalesAge_3() {
		setup();
		Adult adult = new Adult();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0D, 20.0D, "0001", mem2);
		coke.addSales((Sales) sale2);
		MemberSale sale3 = new MemberSale(coke, 1, LocalDate.parse("2021-11-21"), emp, 20.0D, 20.0D, "0001", mem2);
		coke.addSales((Sales) sale3);
		assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), -1, (AgeGroup) adult, coke));
	}

	@Test
	void testCheckInventoryIsEmpty() {
		setup();
		assertEquals(Boolean.valueOf(true), Boolean.valueOf(this.invenDB.checkInventoryIsEmpty()));
	}

	@Test
	void TestPrintHighestSalesProduct_0() {
		setup();
		Product product = this.invenDB.printHighestSalesProduct(0);
		assertEquals(null, product);
	}

	@Test
	void TestPrintHighestSalesProduct_0a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		this.invenDB.printHighestSalesProduct(0);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_0b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002");
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProduct(0);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_0c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product juice = new Product("Juice", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		this.invenDB.add(juice);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale3 = new Sales(juice, 1, LocalDate.now(), emp, 20.0, 20.0, "0003");
		juice.addSales(sale3);
		this.invenDB.printHighestSalesProduct(0);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_1a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		this.invenDB.printHighestSalesProduct(1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_1b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002");
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProduct(1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_1c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product juice = new Product("Juice", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		this.invenDB.add(juice);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale3 = new Sales(juice, 1, LocalDate.now(), emp, 20.0, 20.0, "0003");
		juice.addSales(sale3);
		this.invenDB.printHighestSalesProduct(1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_2a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		this.invenDB.printHighestSalesProduct(2);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_2b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002");
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProduct(2);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_2c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product juice = new Product("Juice", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		this.invenDB.add(juice);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale3 = new Sales(juice, 1, LocalDate.now(), emp, 20.0, 20.0, "0003");
		juice.addSales(sale3);
		this.invenDB.printHighestSalesProduct(2);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProduct_3() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product juice = new Product("Juice", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale3 = new Sales(juice, 1, LocalDate.now(), emp, 20.0, 20.0, "0003");
		juice.addSales(sale3);
		this.invenDB.printHighestSalesProduct(-1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(0, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_0a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(0, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_0b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(0, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_0c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(0, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_1a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(1, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_1b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(1, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_1c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(1, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_2a() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(2, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_2b() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 1, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(2, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(2, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_2c() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(2, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(1, digit);
	}

	@Test
	void TestPrintHighestSalesProductForDifferentAgeGroup_3() {
		setup();
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product spirt = new Product("Spirt", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		this.invenDB.add(coke);
		this.invenDB.add(spirt);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(spirt, 1, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		spirt.addSales(sale2);
		this.invenDB.printHighestSalesProductForDifferentAgeGroups(-1, 1);
		int digit = this.invenDB.checkHighestSalesListSize();
		assertEquals(0, digit);
	}

	@Test
	void TestgetSalesPercentageForProduct_0() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002");
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProduct(0, coke, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProduct_1() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002");
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProduct(1, coke, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProduct_2() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		Sales sale = new Sales(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001");
		coke.addSales(sale);
		Sales sale2 = new Sales(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002");
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProduct(2, coke, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProduct_3() {
		setup();
		double rslt = this.invenDB.getSalesPercentageForProduct(-1, null, 0);
		assertEquals(0.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProductForDifferentAgeGroup_0() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProductForDifferentAgeGroup(0, coke, 1, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProductForDifferentAgeGroup_1() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProductForDifferentAgeGroup(1, coke, 1, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProductForDifferentAgeGroup_2() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Product sprit = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(sprit);
		this.invenDB.add(coke);
		MemberSale sale = new MemberSale(coke, 2, LocalDate.now(), emp, 20.0, 20.0, "0001", mem);
		coke.addSales(sale);
		MemberSale sale2 = new MemberSale(sprit, 8, LocalDate.now(), emp, 20.0, 20.0, "0002", mem);
		sprit.addSales(sale2);
		double rslt = this.invenDB.getSalesPercentageForProductForDifferentAgeGroup(2, coke, 1, 10);
		assertEquals(20.0, rslt, 0.0);
	}

	@Test
	void TestgetSalesPercentageForProductForDifferentAgeGroup_3() {
		setup();
		double rslt = this.invenDB.getSalesPercentageForProductForDifferentAgeGroup(-1, null, 0, 0);
		assertEquals(0.0, rslt, 0.0);
	}

	@Test
	void TestdeductInventoryofProducts_0() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-19"), "Coca Cola");
		this.invenDB.add(Coke);
		this.invenDB.deductInventoryofProductsFromQueue(100, Coke);
		assertEquals(100, Coke.getInventory());
	}

	@Test
	void TestdeductInventoryofProducts_1() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-19"), "Coca Cola");
		this.invenDB.add(Coke);
		this.invenDB.deductInventoryofProductsFromQueue(101, Coke);
		assertEquals(99, Coke.getInventory());
	}

	@Test
	void TestaddProducttoQueue() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.addProductstoInventory(Coke, CokeClone);
		assertEquals(false, Coke.getProductQueue().isEmpty());
	}

	@Test
	void TestaddProduct() throws ParseException {
		setup();
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(CokeClone);
		assertEquals(false, this.invenDB.checkInventoryIsEmpty());
	}

	@Test
	void TestRemoveProduct() throws ParseException {
		setup();
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(CokeClone);
		this.invenDB.remove(CokeClone);
		assertEquals(true, this.invenDB.checkInventoryIsEmpty());
	}

	@Test
	void TestSortProduct() throws ParseException {
		setup();
		this.productFactory.createProduct("Coca Cola Sugar", "Food", 7.5, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		Product CokeClone = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100,
				LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.sortProduct();
		assertEquals(CokeClone, this.invenDB.searchProductByPosition(0));
	}

	@Test
	void testListInventory_0() throws Exception {
		setup();
		setOutput();
		this.invenDB.listInventory();
		assertEquals("The inventory is empty.\n", getOutput());
	}

	@Test
	void testListInventory_1() throws Exception {
		setOutput();
		setup();
		Product p = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.listInventory();
		String rslt = String.format("%-30s%-20s%-10s%-10s\n",
				new Object[] { "Type", "Product Name", "Quantity", "Marked Price($)/unit" });
		String rslt2 = String.format("%-30s%-20s%-10d%-10.2f\n", p.getType(), p.getName(), p.getInventory(),
				p.getPrice());
		String finalrslt = rslt + rslt2;
		assertEquals(finalrslt, getOutput());
	}

	@Test
	void testGetHighestSalesProductList() {
		setup();
		assertEquals(true, this.invenDB.getHighestSalesProductList().isEmpty());
	}

	@Before
	public void setup() {
		this.invenDB.clear();
	}

	private void setOutput() throws Exception {
		this.oldPrintStream = System.out;
		this.bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.bos));
	}

	private String getOutput() {
		System.setOut(this.oldPrintStream);
		return this.bos.toString();
	}
}
