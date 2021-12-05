package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ageGroup.Adult;
import ageGroup.Teenagers;
import membership.PlatinumMembership;
import membership.SilverMembership;
import product.Product;
import user.Cart;
import user.CompletedCart;
import user.Member;

class TestMember {

	@Test
	void testMemberGetAge() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals((new Adult()).getClass(), mem.getAgeGroup().getClass());
	}

	@Test
	void testMemberSetAge() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		Teenagers teen = new Teenagers();
		mem.setAgeGroup(teen);
		assertEquals("Teenagers", mem.getAgeGroup().getAgeGroup());
	}

	@Test
	void testMemberGetAgeString() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		Teenagers teen = new Teenagers();
		mem.setAgeGroup(teen);
		assertEquals("Teenagers", mem.getAgeGroup().getAgeGroup());
	}

	@Test
	void testgetMembership() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals("Silver", mem.getMembership().getMembershipLevel());
	}

	@Test
	void testGetAccumulatedSpending() {
		Member mem = new Member("John", "M");
		assertEquals(0, mem.getAccumulatedSpending());
	}

	@Test
	void testSetAccumulatedSpending() {
		Member mem = new Member("John", "M");
		mem.setAccumulatedSpending(5000);
		assertEquals(5000, mem.getAccumulatedSpending());
	}

	@Test
	void testAddAccumulatedSpending() {
		Member mem = new Member("John", "M");
		mem.setAccumulatedSpending(5000);
		mem.addAccumulatedSpending(100);
		assertEquals(5100, mem.getAccumulatedSpending());
	}

	@Test
	void testDeductAccumulatedSpending() {
		Member mem = new Member("John", "M");
		mem.setAccumulatedSpending(5000);
		mem.deductAccumulatedSpending(100);
		assertEquals(4900, mem.getAccumulatedSpending());
	}

	@Test
	void testGetBirthOfYear() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals("2001", mem.getBirthOfYear());
	}

	@Test
	void testSetBirthOfYear() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setBirthOfYear("2002");
		assertEquals("2002", mem.getBirthOfYear());
	}

	@Test
	void testGetPoints() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals(0, mem.getPoints());
	}

	@Test
	void testSetPoints() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setPoints(100);
		assertEquals(100, mem.getPoints());
	}

	@Test
	void testAddPoints() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setPoints(100);
		mem.addPoints(50);
		assertEquals(150, mem.getPoints());
	}

	@Test
	void testDeductPoints() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setPoints(100);
		mem.deductPoints(50);
		assertEquals(50, mem.getPoints());
	}

	@Test
	void testGetUserName() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals("John", mem.getUserName());
	}

	@Test
	void testSetUserName() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setUserName("Thomas");
		assertEquals("Thomas", mem.getUserName());
	}

	@Test
	void testGetSex() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals("M", mem.getSex());
	}

	@Test
	void testSetSex() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setSex("F");
		assertEquals("F", mem.getSex());
	}

	@Test
	void testGetEmail() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals("JohnDoe@gmail.com", mem.getEmail());
	}

	@Test
	void testSetEmail() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setEmail("Thomas@gmail.com");
		assertEquals("Thomas@gmail.com", mem.getEmail());
	}

	@Test
	void testGetUserId() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals("1111", mem.getUserid());
	}

	@Test
	void testSetUserId() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.setUserid("1000");
		assertEquals("1000", mem.getUserid());
	}

	@Test
	void testGetMembership() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		boolean rslt = mem.getMembership() instanceof SilverMembership;
		assertEquals(true, rslt);
	}

	@Test
	void testSetMembership() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		boolean rslt = mem.getMembership() instanceof PlatinumMembership;
		assertEquals(true, rslt);
	}

	@Test
	void testGetDiscountRate() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		assertEquals(0.75, mem.getDiscountRate());
	}

	@Test
	void testGetCart() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals(true, mem.getCart().isEmpty());
	}

	@Test
	void testSetCart() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		bam.addProductToCart(prod, 10, LocalDate.now());
		mem.setCart(bam.getCart());
		assertEquals(false, mem.getCart().isEmpty());
	}

	@Test
	void testGetCompletedCart() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals(true, mem.getCompletedCart().isEmpty());
	}

	@Test
	void testSetCompletedCart() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		bam.addProductToCompletedCart(c, "123", "123");
		mem.setCompletedCart(bam.getCompletedCart());
		assertEquals(false, mem.getCompletedCart().isEmpty());
	}

	@Test
	void testRemoveProductInCart_Digit() {
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = bam.addProductToCart(prod, 10, LocalDate.now());
		bam.removeProductInCart(c);
		assertEquals(true, bam.getCart().isEmpty());
	}

	@Test
	void testRemovePurchaseHistory() {
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart compl = bam.addProductToCompletedCart(c, "123", "123");
		bam.removePurchaseHistory(compl);
		assertEquals(true, bam.getCompletedCart().isEmpty());
	}

	@Test
	void testGetCartForSpecificProduct() {
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart cc = bam.addProductToCart(prod, 10, LocalDate.now());
		assertEquals(cc.getProduct(), bam.getCartForSpecificProduct(0));
	}

	@Test
	void testAddProductToCart() {
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		bam.addProductToCart(prod, 10, LocalDate.now());
		assertEquals(false, bam.getCart().isEmpty());
	}

	@Test
	void testAddProductToCompletedCart() {
		Member bam = new Member("Johnny", "2001", "M", "JohnDoe@gmail.com", "1112");
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		bam.addProductToCompletedCart(c, "123", "123");
		assertEquals(false, bam.getCompletedCart().isEmpty());
	}
}
