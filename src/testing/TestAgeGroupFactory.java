package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ageGroup.AgeGroupFactory;

class TestAgeGroupFactory {

	@Test
	void testAllocation_1() {
		int year = 2023;
		assertEquals(null , AgeGroupFactory.Allocation(year));
	}
	
	@Test
	void testAllocation_2() {
		int year = 2002;
		assertEquals("Teenagers" , AgeGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_3() {
		int year = 2001;
		assertEquals("Adult" , AgeGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_4() {
		int year = 1971;
		assertEquals("Elderly" , AgeGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_4a() {
		int year = 1000;
		assertEquals(null , AgeGroupFactory.Allocation(year));
	}
	
	@Test
	void testAllocation_5() {
		int year = 0;
		assertEquals(null , AgeGroupFactory.Allocation(year));
	}
	
	@Test
	void testintegerToAgeGroup_1() {
		int digit = 1;
		assertEquals("Teenagers" , AgeGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_2() {
		int digit = 2;
		assertEquals("Adult" , AgeGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_3() {
		int digit = 3;
		assertEquals("Elderly" , AgeGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_4() {
		int digit = 4;
		assertEquals(null , AgeGroupFactory.integerToAgeGroup(digit));
	}
}
