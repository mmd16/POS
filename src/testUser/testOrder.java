package testUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commodity.Food;
import commodity.Commodity;
import user.Order;
import user.User;

//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testOrder {
    private User user;
    private Commodity c1;

    @BeforeEach
    public void setUp() throws Exception {
    	LocalDate date = LocalDate.now();
        user = new User("testUser1", "TestUser1", "F", "testuser1@test.com", date);
        c1 = new Food("Potato Chips", "Lay's Ruffles Munchos", "Spicy and sweet tastes", date, 28, 0.95);
    }

    @Test
    public void test_addOrder() throws ParseException {
        Order o = new Order(c1, user.getMembership().getDiscountRate());
        user.addOrder(o);
        boolean actual = user.getOrderList().contains(o);
        assertEquals(true, actual);
    }

    @Test
    public void test_removeOrder() throws ParseException {
        Order o = new Order(c1, user.getMembership().getDiscountRate());
        user.addOrder(o);
        user.removeOrder(o);

        boolean actual = user.getOrderList().contains(c1);
        assertEquals(false, actual);
    }

    @Test
    public void test_trackOrderDate() throws ParseException {
        Order o = new Order(c1, user.getMembership().getDiscountRate());
        //Date formatter = new SimpleDateFormat("ddMMyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate date =  LocalDate.parse("15/10/2021", formatter);
        o.setOrderDate(date);
        user.addOrder(o);

        String date_actual = o.getStrDate();
        assertEquals("15/10/2021", date_actual);
    }
    
    
    @Test
    public void test_orderRequestHandling() throws ParseException {
        ArrayList<Order> orderList = user.getOrderList();
        orderList.clear();
    	Order o = new Order(c1, user.getMembership().getDiscountRate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate date =  LocalDate.parse("15/10/2021", formatter);
        o.setOrderDate(date);
        user.addOrder(o);
        LocalDate compareDate =  LocalDate.parse("20/10/2021", formatter);
        
        String message = "";
        int dispatchDate = compareDate.getDayOfMonth();
        int orderDate = o.getOrderDate().getDayOfMonth();
        int DatesOfDelay = dispatchDate - orderDate;
        int refund = 50 * (DatesOfDelay - 3);
        if (compareDate.equals(o.getOrderDate()))
        	message = String.format("%s will his or her packages as soon as possible", user.getUsername());
        else if (dispatchDate > orderDate && DatesOfDelay <= 3)
        	message = String.format("Inform %s that he or she will get his packages in %d days", user.getUsername(), DatesOfDelay);
        else if (dispatchDate > orderDate && DatesOfDelay > 3)
        	message = String.format("Inform %s that he or she will a refund of $%d for %d days of delay after three days",
        			user.getUsername(), refund, DatesOfDelay - 3);
        
        assertEquals("Inform testUser1 that he or she will a refund of $100 for 2 days of delay after three days", message);
    }
    

    @Test
    public void test_getOrderList() throws ParseException {
        ArrayList<Order> orderList = user.getOrderList();

        int size = orderList.size();
        boolean actual = size > 0;
        assertEquals(false, actual);
    }

    @Test
    public void test_getNullOrder() throws ParseException {
        ArrayList<Order> orderList = user.getOrderList();
        orderList.clear();

        boolean actual = orderList.isEmpty();
        assertEquals(true, actual);
    }
    
    @Test
    public void test_getCategory() {
        Order o = new Order(c1, user.getMembership().getDiscountRate());
        String category_actual = o.getCategory();
        assertEquals("Food", category_actual);
    }

    @Test
    public void test_getProductName() {
    	Order o = new Order(c1, user.getMembership().getDiscountRate());
        String productName_actual = o.getProductName();
        assertEquals("Potato Chips", productName_actual);
    }
}
