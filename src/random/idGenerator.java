package random;

public class idGenerator {
	private static int counter1 = 0;
	private static int counter2 = 0;
	
	public static int IDgeneratorStaff() 
	{
		return counter1++; 
	}
	
	public static int IDgenerator() 
	{
		return counter2++; 
	}
}
