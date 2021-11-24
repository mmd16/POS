package testing;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import function.InventoryFunctions;

class TestInventoryFunction {

	@Test
	void testCreateProduct() throws ParseException {
		InventoryFunctions IDB = new InventoryFunctions();
		IDB.createProduct("Coca Cola", "Drinks", 7.5, 100, "2021-12-20", "Coca Cola");
		IDB.listInventory();
	}
	
	@Test
	void testRemoveProduct() throws ParseException {
		InventoryFunctions IDB = new InventoryFunctions();
		IDB.createProduct("Coca Cola", "Drink", 7.5, 100, "2021-12-20", "Coca Cola");
		IDB.removeProduct("Coca Cola", "Drink");
		IDB.listInventory();
	}


}
