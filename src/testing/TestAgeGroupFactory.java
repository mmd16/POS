package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ageGroup.AgeGroupFactory;

class TestAgeGroupFactory {
	AgeGroupFactory ageGroupFactory = AgeGroupFactory.getInstance();
	@Test
	void testAllocation_1() {
		int year = 2023;
		assertEquals(null , ageGroupFactory.Allocation(year));
	}
	
	@Test
	void testAllocation_2() {
		int year = 2002;
		assertEquals("Teenagers" , ageGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_3() {
		int year = 2001;
		assertEquals("Adult" , ageGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_4() {
		int year = 1971;
		assertEquals("Elderly" , ageGroupFactory.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_4a() {
		int year = 1000;
		assertEquals(null , ageGroupFactory.Allocation(year));
	}
	
	@Test
	void testAllocation_5() {
		int year = 0;
		assertEquals(null , ageGroupFactory.Allocation(year));
	}
	
	@Test
	void testintegerToAgeGroup_1() {
		int digit = 1;
		assertEquals("Teenagers" , ageGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_2() {
		int digit = 2;
		assertEquals("Adult" , ageGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_3() {
		int digit = 3;
		assertEquals("Elderly" , ageGroupFactory.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_4() {
		int digit = 4;
		assertEquals(null , ageGroupFactory.integerToAgeGroup(digit));
	}
}
