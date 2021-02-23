package climatechange;

import java.util.Arrays;
/**
 * This is the Temperature class which implements the ITemperature and Comparable interface with all the necessary methods.
 * @author Rashmi Boddukuri
 * @version 4/21/2020
 */
public class Temperature implements ITemperature, Comparable<ITemperature>{

	private String country;
	private String country3LetterCode;
	private String month;
	private int year;
	private double temp;
	
	/**
	 * This is the constructor which instantiates the instance variables.
	 * @param temp the input temperature
	 * @param year the input year
	 * @param month the input month
	 * @param country the input country
	 * @param letterCode the country code
	 */
	public Temperature(double temp, int year, String month, String country, String letterCode) 
	{
		this.country = country;
		this.country3LetterCode = letterCode;
		this.month = month;
		this.year = year;
		this.temp = temp;
	}
	
	/**
	 * This method gets the country.
	 * @return country the country
	 */
	@Override
	public String getCountry() { 
		return country;
	}
	
	/**
	 * This method gets the country code.
	 * @return country3LetterCode the country code
	 */
	@Override
	public String getCountry3LetterCode() {
		return country3LetterCode;
	}

	/**
	 * This method gets the month.
	 * @return month the month
	 */
	@Override
	public String getMonth() {
		return month;
	}

	/**
	 * This method gets the year.
	 * @return year the year
	 */
	@Override
	public int getYear() {
		return year;
	}

	/**
	 * This method gets the temperature.
	 * @return farhrenheit if boolean is true else returns celsius temperature
	 */
	@Override
	public double getTemperature(boolean getFahrenheit) {
		if(getFahrenheit)
		{
			double farhrenheit = ((9.0/5.0)*temp) + 32; //if boolean condition is true, conversion will happen and be returned.
			return farhrenheit;
		}
		return temp;
	}
	
	/**
	 * This method overrides toString to a more useful form.
	 * @return getTemperature(false) + " " + getYear()+ " " + getMonth()+ " " + getCountry()+ " " + getCountry3LetterCode() the toString form
	 */
	@Override
	public String toString()
	{
		return getTemperature(false) + " " + getYear()+ " " + getMonth()+ " " + getCountry()+ " " + getCountry3LetterCode();
	}
	
	/**
	 * This method compares two Temperature objects first by temperature, then country, then year, and finally month.
	 * @param x the ITemperature object
	 */
	public int compareTo(ITemperature x)
	{
		Temperature data = (Temperature)x;
		if(this.getTemperature(false) != data.getTemperature(false)) //if the two temperatures are different
		{
			return Double.compare(this.getTemperature(false), data.getTemperature(false));
		}
		else if(this.getCountry().toUpperCase() != data.getCountry().toUpperCase()) //if the temperatures are the same but countries are not
		{
			return this.getCountry().compareTo(data.getCountry());
		}
		else if(this.getYear() != data.getYear()) //if the temperatures and countries are the same but year is not
		{
			return Integer.compare(this.getYear(), data.getYear());
		}
		else //if all above conditions are the same, compare by month
		{
			String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
			int theMonthOne = Arrays.asList(months).indexOf(this.getMonth()); //both these lines find index of the position of the months in the array so that it can be compared.
			int theMonthTwo = Arrays.asList(months).indexOf(data.getMonth());
			return Integer.compare(theMonthOne, theMonthTwo);
		}
	}
	
	/**
	 * This method checks whether two objects are equal to each other.
	 * @param b the object of comparison
	 */
	public boolean equals(Object b)
	{
		Temperature other = (Temperature)b;
		if(this.compareTo(other) == 0) //uses compareTo method above to compare the objects
		{
			return true; //return true if both are the same
		}
		return false; //return false if both are different
	}
	
	/**
	 * This method creates the hashCode specific to the temperature object properties.
	 * @return (getTemperature(false) + getYear() + getMonth().hashCode() + getCountry().hashCode() + getCountry3LetterCode().hashCode()) the hash code
	 */
	public int hashCode()
	{
		return (int) (getTemperature(false) + getYear() + getMonth().hashCode() + getCountry().hashCode() + getCountry3LetterCode().hashCode());
	}
}
