package ageGroup;

import tool.AgeCalculator;

public class AgeGroupFactory {

	public static AgeGroup Allocation(int year) {
		int age = AgeCalculator.getAge(year);
		if(age < 0)
			return null;
		else if (age < 20)
			return new Teenagers();
		else if (age < 50) 
			return new Adult();
		else if(age > 49)
			return new Elderly();
		else
			return null;
	}
}
