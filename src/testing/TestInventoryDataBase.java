package testing;

import ageGroup.Adult;
import ageGroup.AgeGroup;
import ageGroup.Teenagers;
import db.InventoryDataBase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductFactory;
import staff.Employee;
import transactions.MemberSale;
import transactions.Sales;
import user.Member;

public class TestInventoryDataBase {
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();

	private ProductFactory productFactory = ProductFactory.getInstance();

	PrintStream oldPrintStream;

	ByteArrayOutputStream bos;

	ByteArrayInputStream testIn;

	@Test
	void testSearchProduct_True() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Assertions.assertEquals(Coke, this.invenDB.searchProduct("Coca Cola", "Drinks"));
	}

	@Test
	void testSearchProduct_NameFalse() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Assertions.assertEquals(null, this.invenDB.searchProduct("Pepsi Cola", "Drinks"));
	}

	@Test
	void testSearchProduct_TypeFalse() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Assertions.assertEquals(null, this.invenDB.searchProduct("Coca Cola", "Food"));
	}

	@Test
	void testCountSales_0() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(2, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, Coke));
	}

	@Test
	void testCountSales_1() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-12-21"), 1, Coke));
	}

	@Test
	void testCountSales_1a() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-12-23"), 1, Coke));
	}

	@Test
	void testCountSales_1b() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(2, this.invenDB.countSales(LocalDate.parse("2021-12-23"), 1, Coke));
	}

	@Test
	void testCountSales_2() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(2, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, Coke));
	}

	@Test
	void testCountSales_3() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale3);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), -1, Coke));
	}

	@Test
	void testCountSalesAge_0() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale3);
		Assertions.assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_1() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale3);
		Assertions.assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-11-21"), 1, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_1a() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale3);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-11-21"), -1, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_2() {
		setup();
		Teenagers teens = new Teenagers();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-01-21"), 1, (AgeGroup) teens, Coke));
	}

	@Test
	void testCountSalesAge_2a() {
		setup();
		Teenagers teens = new Teenagers();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2022-12-21"), 1, (AgeGroup) teens, Coke));
	}

	@Test
	void testCountSalesAge_2b() {
		setup();
		Adult adult = new Adult();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 1, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_3() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-11-21"), emp, 20.0D, 20.0D, "0001", mem2);
		Coke.addSales((Sales) sale3);
		Assertions.assertEquals(1, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_4() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 0, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_5() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 1, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_6() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, (AgeGroup) adult, Coke));
	}

	@Test
	void testCountSalesAge_7() {
		setup();
		Adult adult = new Adult();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Assertions.assertEquals(0, this.invenDB.countSales(LocalDate.parse("2021-12-21"), 2, (AgeGroup) adult, Coke));
	}

	@Test
	void testCheckInventoryIsEmpty() {
		setup();
		Assertions.assertEquals(Boolean.valueOf(true), Boolean.valueOf(this.invenDB.checkInventoryIsEmpty()));
	}

	@Test
	void TestPrintHighestSalesProduct_0() {
		setup();
		Product product = this.invenDB.printHighestSalesProduct(-1, false, 0);
		Assertions.assertEquals(null, product);
	}

	@Test
	void TestPrintHighestSalesProduct_1() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.now(), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Product product = this.invenDB.printHighestSalesProduct(0, false, 0);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_2() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002");
		Cokeb.addSales(sale2);
		Product product = this.invenDB.printHighestSalesProduct(0, false, 0);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_3() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002");
		Cokeb.addSales(sale2);
		Product product = this.invenDB.printHighestSalesProduct(1, false, 0);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_4() {
		setup();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002");
		Cokeb.addSales(sale2);
		Product product = this.invenDB.printHighestSalesProduct(2, false, 0);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_5() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002", mem);
		Cokeb.addSales((Sales) sale2);
		Product product = this.invenDB.printHighestSalesProduct(0, true, 1);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_7() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002", mem);
		Cokeb.addSales((Sales) sale2);
		Product product = this.invenDB.printHighestSalesProduct(1, true, 1);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestPrintHighestSalesProduct_8() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		Product Cokeb = new Product("Coca Cola (without sugar)", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.add(Cokeb);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		MemberSale sale2 = new MemberSale(Cokeb, 2, LocalDate.now(), emp, 20.0D, 20.0D, "0002", mem);
		Cokeb.addSales((Sales) sale2);
		Product product = this.invenDB.printHighestSalesProduct(2, true, 1);
		Assertions.assertEquals(Coke, product);
	}

	@Test
	void TestgetSalesPercentageForProduct_0() {
		setup();
		double rslt = this.invenDB.getSalesPercentageForProduct(-1, null, false, 0, 0);
		Assertions.assertEquals(0.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_1() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(0, Coke, false, 0, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_2() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(1, Coke, false, 0, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_3() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(2, Coke, false, 0, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_4() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(0, Coke, true, 1, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_5() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(1, Coke, true, 1, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestgetSalesPercentageForProduct_6() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Product Coke = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(Coke);
		MemberSale sale = new MemberSale(Coke, 3, LocalDate.now(), emp, 20.0D, 20.0D, "0001", mem);
		Coke.addSales((Sales) sale);
		double rslt = this.invenDB.getSalesPercentageForProduct(2, Coke, true, 1, 3);
		Assertions.assertEquals(100.0D, rslt, 0.0D);
	}

	@Test
	void TestdeductInventoryofProducts_0() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100,
				LocalDate.parse("2021-12-20"), "Coca Cola");
		this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-19"), "Coca Cola");
		this.invenDB.add(Coke);
		this.invenDB.deductInventoryofProductsFromQueue(100, Coke);
		Assertions.assertEquals(100, Coke.getInventory());
	}

	@Test
	void TestdeductInventoryofProducts_1() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100,
				LocalDate.parse("2021-12-20"), "Coca Cola");
		this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-19"), "Coca Cola");
		this.invenDB.add(Coke);
		this.invenDB.deductInventoryofProductsFromQueue(101, Coke);
		Assertions.assertEquals(99, Coke.getInventory());
	}

	@Test
	void TestaddProducttoQueue() throws ParseException {
		setup();
		Product Coke = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100,
				LocalDate.parse("2021-12-20"), "Coca Cola");
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.addProductstoInventory(Coke, CokeClone);
		Assertions.assertEquals(Boolean.valueOf(false), Boolean.valueOf(Coke.getProductQueue().isEmpty()));
	}

	@Test
	void TestaddProduct() throws ParseException {
		setup();
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(CokeClone);
		Assertions.assertEquals(Boolean.valueOf(false), Boolean.valueOf(this.invenDB.checkInventoryIsEmpty()));
	}

	@Test
	void TestRemoveProduct() throws ParseException {
		setup();
		Product CokeClone = new Product("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.add(CokeClone);
		this.invenDB.remove(CokeClone);
		Assertions.assertEquals(Boolean.valueOf(true), Boolean.valueOf(this.invenDB.checkInventoryIsEmpty()));
	}

	@Test
	void TestSortProduct() throws ParseException {
		setup();
		this.productFactory.createProduct("Coca Cola Sugar", "Food", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		Product CokeClone = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100,
				LocalDate.parse("2021-12-20"), "Coca Cola");
		this.invenDB.sortProduct();
		Assertions.assertEquals(CokeClone, this.invenDB.searchProductByPosition(0));
	}

	@Test
	void testListInventory_0() throws Exception {
		setup();
		setOutput();
		this.invenDB.listInventory();
		Assertions.assertEquals("The inventory is empty.\n", getOutput());
	}

	@Test
	void testListInventory_1() throws Exception {
		setOutput();
		setup();
		Product p = this.productFactory.createProduct("Coca Cola", "Drinks", 7.5D, 100, LocalDate.parse("2021-12-20"),
				"Coca Cola");
		this.invenDB.listInventory();
		String rslt = String.format("%-30s%-20s%-10s%-10s\n",
				new Object[] { "Type", "Product Name", "Quantity", "Marked Price($)/unit" });
		String rslt2 = String.format("%-30s%-20s%-10d%-10.2f\n", new Object[] { p.getType(), p.getName(),
				Integer.valueOf(p.getInventory()), Double.valueOf(p.getPrice()) });
		String finalrslt = String.valueOf(rslt) + rslt2;
		Assertions.assertEquals(finalrslt, getOutput());
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
