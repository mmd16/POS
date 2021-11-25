package testing;

import db.SalesDataBase;
import function.SalesFunction;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductFactory;
import staff.Employee;

import transactions.Sales;

public class TestSalesFunction {
  private SalesFunction sales = new SalesFunction();
  
  private ProductFactory product = ProductFactory.getInstance();
  
  private SalesDataBase salesDB = SalesDataBase.getInstance();
  
  PrintStream oldPrintStream;
  
  ByteArrayOutputStream bos;
  
  ByteArrayInputStream testIn;
  
  @Test
  void testGetTotalSalesNum() {
    setup();
    int rslt = this.sales.getTotalSalesNum();
    Assertions.assertEquals(0, rslt);
  }
  
  @Test
  void testCheckForTotalIncome() {
    setup();
    Product prod = new Product("Chips", "Food", 20.0D, 50, LocalDate.parse("2021-12-12"), "Lais");
    Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
    Sales sale = new Sales(prod, 3, LocalDate.parse("2021-11-01"), emp, 60.0D, 60.0D, "0001");
    this.salesDB.add(sale);
    double rslt = this.sales.getTotalRevenue(LocalDate.now(), 1);
    Assertions.assertEquals(60.0D, rslt, 0.0D);
  }
  
  @Test
  void testCheckIsSalesIsEmpty() {
    setup();
    boolean rslt = this.sales.checkSalesIsEmpty();
    Assertions.assertEquals(Boolean.valueOf(true), Boolean.valueOf(rslt));
  }
  
  @Test
  void testSalesPercentageForProduct_1() throws Exception {
    setOutput();
    setup();
    Product prod = new Product("Chips", "Food", 20.0D, 50, LocalDate.parse("2021-12-12"), "Lais");
    double rslt = this.sales.getSalesPercentageForProduct(0, prod, false, 0, 10);
    Assertions.assertEquals("*** No Sales Exists! ***\n", getOutput());
    Assertions.assertEquals(0.0D, rslt);
  }
  
  @Test
  void testSalesPercentageForProduct_2() throws Exception {
    setOutput();
    setup();
    Product prod = this.product.createProduct("Chips", "Food", 20.0D, 50, LocalDate.parse("2021-12-12"), "Lais");
    Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
    Sales sale = new Sales(prod, 3, LocalDate.parse("2021-11-01"), emp, 60.0D, 60.0D, "0001");
    this.salesDB.add(sale);
    prod.addSales(sale);
    double rslt = this.sales.getSalesPercentageForProduct(1, prod, false, 0, 3);
    Assertions.assertEquals(100.0D, rslt);
  }
  
  @Test
  void testPrintHighestSalesProduct_1() throws Exception {
    setOutput();
    setup();
    Product rslt = this.sales.printHighestSalesProduct(0, false, 0);
    Assertions.assertEquals("*** No Sales Exists! ***\n", getOutput());
    Assertions.assertEquals(null, rslt);
  }
  
  @Test
  void testPrintHighestSalesProduct_2() throws Exception {
    setOutput();
    setup();
    Product prod = this.product.createProduct("Chips(Sour)", "Food", 20.0D, 50, LocalDate.parse("2021-12-24"), "Lais");
    Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
    Sales sale = new Sales(prod, 3, LocalDate.parse("2021-11-02"), emp, 60.0D, 60.0D, "0002");
    this.salesDB.add(sale);
    prod.addSales(sale);
    Product rslt = this.sales.printHighestSalesProduct(1, false, 0);
    Assertions.assertEquals(prod, rslt);
  }
  
  @Test
  void testlistSales() throws Exception {
    setOutput();
    setup();
    this.sales.listSales();
    Assertions.assertEquals("There is no sales yet.\n", getOutput());
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
  
  @Before
  public void setup() {
    this.salesDB.clearSalesList();
  }
}
