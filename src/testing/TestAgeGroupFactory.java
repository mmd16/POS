package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ageGroup.AgeGroupFactory;

class TestAgeGroupFactory {

	@Test
	void testAllocation_1() {
		int year = 2023;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals(null , agf.Allocation(year));
	}
	
	@Test
	void testAllocation_2() {
		int year = 2002;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Teenagers" , agf.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_3() {
		int year = 2001;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Adult" , agf.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_4() {
		int year = 1971;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Elderly" , agf.Allocation(year).getAgeGroup());
	}
	
	@Test
	void testAllocation_5() {
		int year = 0;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals(null , agf.Allocation(year));
	}
	
	@Test
	void testintegerToAgeGroup_1() {
		int digit = 1;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Teenagers" , agf.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_2() {
		int digit = 2;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Adult" , agf.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_3() {
		int digit = 3;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals("Elderly" , agf.integerToAgeGroup(digit).getAgeGroup());
	}
	
	@Test
	void testintegerToAgeGroup_4() {
		int digit = 4;
		AgeGroupFactory agf = new AgeGroupFactory();
		assertEquals(null , agf.integerToAgeGroup(digit));
	}
}
