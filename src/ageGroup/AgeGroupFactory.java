package ageGroup;

import tool.Tools;

public class AgeGroupFactory {
	private Tools tools = Tools.getInstance();
	public static AgeGroupFactory instance = new AgeGroupFactory();

	public static AgeGroupFactory getInstance() {
		return instance;
	}

	private AgeGroupFactory() {

	}

	public AgeGroup Allocation(int year) {
		int age = tools.getAge(year);
		if (age < 0)
			return null;
		else if (age < 20)
			return new Teenagers();
		else if (age < 50)
			return new Adult();
		else if (age < 150)
			return new Elderly();
		else
			return null;
	}

	public AgeGroup integerToAgeGroup(int digit) {
		switch (digit) {
		case 1:
			return new Teenagers();
		case 2:
			return new Adult();
		case 3:
			return new Elderly();
		}
		return null;
	}
}
