package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ageGroup.Adult;
import ageGroup.AgeGroup;
import ageGroup.Teenagers;
import db.InventoryDataBase;
import product.Product;
import staff.Employee;
import transactions.MemberSale;
import transactions.Sales;
import user.Member;

class TestInventoryDataBase {

	@Test
	void testSearchProduct_True() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		assertEquals(Coke , IDB.searchProduct("Coca Cola", "Drinks"));
	}
	
	@Test
	void testSearchProduct_NameFalse() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		assertEquals(null , IDB.searchProduct("Pepsi Cola", "Drinks"));
	}
	
	@Test
	void testSearchProduct_TypeFalse() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		assertEquals(null , IDB.searchProduct("Coca Cola", "Food"));
	}
	
	@Test
	void testCountSales_0() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-12-22"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale3);
		assertEquals(2 , IDB.countSales(LocalDate.parse("2021-12-21"), 0, Coke));
	}
	
	@Test
	void testCountSales_1() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2021-11-05"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale3);
		assertEquals(2 , IDB.countSales(LocalDate.parse("2021-12-21"), 1, Coke));
	}
	
	@Test
	void testCountSales_2() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale3);
		assertEquals(2 , IDB.countSales(LocalDate.parse("2021-12-21"), 2, Coke));
	}
	
	@Test
	void testCountSales_3() {
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale);
		Sales sale2 = new Sales(Coke, 1, LocalDate.parse("2021-12-23"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale2);
		Sales sale3 = new Sales(Coke, 1, LocalDate.parse("2022-11-05"), emp, 20.0, 20.0, "0001");
		Coke.addSales(sale3);
		assertEquals(0 , IDB.countSales(LocalDate.parse("2021-12-21"), -1, Coke));
	}
	
	@Test
	void testCountSalesAge_0() {
		Adult adult = new Adult();
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		Coke.addSales(sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0001", mem);
		Coke.addSales(sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem2);
		Coke.addSales(sale3);
		assertEquals(1 , IDB.countSales(LocalDate.parse("2021-12-21"), 0, adult, Coke));
	}
	
	@Test
	void testCountSalesAge_1() {
		Adult adult = new Adult();
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		Coke.addSales(sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0001", mem2);
		Coke.addSales(sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-11-21"), emp, 20.0, 20.0, "0001", mem2);
		Coke.addSales(sale3);
		assertEquals(1 , IDB.countSales(LocalDate.parse("2021-12-21"), 1, adult, Coke));
	}
	
	@Test
	void testCountSalesAge_2() {
		Adult adult = new Adult();
		InventoryDataBase IDB = InventoryDataBase.getInstance();
		IDB.clear();
		Product Coke = new Product("Coca Cola", "Drinks", 7.5, 100, LocalDate.parse("2021-12-20"), "Coca Cola");
		IDB.add(Coke);
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		Member mem2 = new Member("Tim", "1999", "M", "JohnDoe@gmail.com");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(Coke, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001", mem);
		Coke.addSales(sale);
		MemberSale sale2 = new MemberSale(Coke, 1, LocalDate.parse("2022-12-21"), emp, 20.0, 20.0, "0001", mem2);
		Coke.addSales(sale2);
		MemberSale sale3 = new MemberSale(Coke, 1, LocalDate.parse("2021-11-21"), emp, 20.0, 20.0, "0001", mem2);
		Coke.addSales(sale3);
		assertEquals(1 , IDB.countSales(LocalDate.parse("2021-12-21"), 2, adult, Coke));
	}
}
