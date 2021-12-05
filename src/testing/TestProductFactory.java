package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import db.InventoryDataBase;
import product.Product;
import product.ProductFactory;

public class TestProductFactory {

	private ProductFactory productFactory = ProductFactory.getInstance();

	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	PrintStream oldPrintStream;

	ByteArrayOutputStream bos;

	ByteArrayInputStream testIn;

	@Test
	void testCreateProduct_1() throws Exception {
		setOutput();
		productFactory.createProduct("cocaCola", "Drinks", 10.0, 100, LocalDate.parse("2019-12-14"), "Coca");
		assertEquals("*** Date is invalid! ***\n", getOutput());
	}

	@Test
	void testCreateProduct_2() throws Exception {
		setOutput();
		productFactory.createProduct("cocaCola", "Drinks", -1.0, 100, LocalDate.parse("2022-12-14"), "Coca");
		assertEquals("*** The number you inserted should not be zero or negative. ***\n", getOutput());
	}

	@Test
	void testCreateProduct_3() throws Exception {
		setOutput();
		productFactory.createProduct("cocaColaSugar", "Drinks", 10.0, -1, LocalDate.parse("2022-12-14"), "Coca");
		assertEquals("*** The number you inserted should not be zero or negative. ***\n", getOutput());
	}

	@Test
	void testCreateProduct_4() throws Exception {
		Product p = productFactory.createProduct("cocaColaSugar", "Food", 10.0, 100, LocalDate.parse("2022-12-14"),
				"Coca");
		Product p1 = invenDB.searchProduct("cocaColaSugar", "Food");
		assertEquals(p, p1);
	}

	@Test
	void testCreateProduct_5() throws Exception {
		Product mainProduct = productFactory.createProduct("cocaColaSugar", "Food", 10.0, 100,
				LocalDate.parse("2022-12-14"), "Coca");
		Product subProduct = productFactory.createProduct("cocaColaSugar", "Food", 10.0, 100,
				LocalDate.parse("2022-12-30"), "Coca");
		mainProduct.addProductToQueue(subProduct);
		Product p1 = invenDB.searchProduct("cocaColaSugar", "Food");
		assertEquals(false, p1.getProductQueue().isEmpty());
	}

	@Test
	void testCreateProduct_6() throws Exception {
		setOutput();
		Product p = productFactory.createProduct("cocaCola", "Drinks", 10.0, 100, LocalDate.parse("2022-12-14"),
				"Coca");
		Product p1 = invenDB.searchProduct("cocaCola", "Drinks");
		assertEquals(p, p1);
	}

	@Test
	void testCreateProduct_7() throws Exception {
		Product mainProduct = productFactory.createProduct("cocaCola", "Drinks", 10.0, 100,
				LocalDate.parse("2022-12-14"), "Coca");
		Product subProduct = productFactory.createProduct("cocaCola", "Drinks", 10.0, 100,
				LocalDate.parse("2022-12-30"), "Coca");
		mainProduct.addProductToQueue(subProduct);
		Product p1 = invenDB.searchProduct("cocaCola", "Drinks");
		assertEquals(false, p1.getProductQueue().isEmpty());
	}

	@Test
	void testCreateProduct_8() throws Exception {
		Product p = productFactory.createProduct("Calcium Block", "Pharamacy", 10.0, 100, LocalDate.parse("2022-12-14"),
				"Coca");
		Product p1 = invenDB.searchProduct("Calcium Block", "Pharamacy");
		assertEquals(p, p1);
	}

	@Test
	void testCreateProduct_9() throws Exception {
		Product mainProduct = productFactory.createProduct("Calcium Block", "Pharamacy", 10.0, 100,
				LocalDate.parse("2022-12-14"), "Coca");
		Product subProduct = productFactory.createProduct("Calcium Block", "Pharamacy", 10.0, 100,
				LocalDate.parse("2022-12-30"), "Coca");
		mainProduct.addProductToQueue(subProduct);
		Product p1 = invenDB.searchProduct("Calcium Block", "Pharamacy");
		assertEquals(false, p1.getProductQueue().isEmpty());
	}

	@Test
	void testCreateProduct_10() throws Exception {
		Product p = productFactory.createProduct("Salt", "CookingIngredients", 10.0, 100, LocalDate.parse("2022-12-14"),
				"Coca");
		Product p1 = invenDB.searchProduct("Salt", "CookingIngredients");
		assertEquals(p, p1);
	}

	@Test
	void testCreateProduct_11() throws Exception {
		Product mainProduct = productFactory.createProduct("Salt", "CookingIngredients", 10.0, 100,
				LocalDate.parse("2022-12-14"), "Coca");
		Product subProduct = productFactory.createProduct("Salt", "CookingIngredients", 10.0, 100,
				LocalDate.parse("2022-12-30"), "Coca");
		mainProduct.addProductToQueue(subProduct);
		Product p1 = invenDB.searchProduct("Salt", "CookingIngredients");
		assertEquals(false, p1.getProductQueue().isEmpty());
	}

	@Test
	void testCreateProduct_12() throws Exception {
		setOutput();
		productFactory.createProduct("Salt", "idk", 10.0, 100, LocalDate.parse("2022-12-14"),
				"Coca");
		assertEquals("*** Please input the correct product type! ***\n", getOutput());
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
