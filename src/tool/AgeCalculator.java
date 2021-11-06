package tool;

import java.time.LocalDate;

public class AgeCalculator {
	
	public static int getAge(int birthYear) 
	{
		int year = LocalDate.now().getYear();
		return year - birthYear;
	}
}
