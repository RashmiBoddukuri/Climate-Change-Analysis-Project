package climatechange;
/**
 * This is the ITemperature interface containing all the methods that will be implemented.
 * @author Rashmi Boddukuri
 * @version 4/19/2020
 */
public interface ITemperature extends Comparable<ITemperature>{

	public String getCountry();// get the name of the country
	public String getCountry3LetterCode();  // get the 3-letter code of the country
	public String getMonth();// get the month
	public int getYear();// get the year
	public double getTemperature(boolean getFahrenheit);// get temperature; input parameter of false = return Celsius value)
	
}
