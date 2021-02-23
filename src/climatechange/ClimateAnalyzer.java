package climatechange;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * This is the ClimateAnalyzer class which implements the IClimateAnalyzer with all the necessary methods.
 * @author Rashmi Boddukuri
 * @version 4/26/2020
 */
public class ClimateAnalyzer implements IClimateAnalyzer {

	public static WeatherIO object;
	public static ArrayList<ITemperature> arrayData;

	/**
	 * This is the constructor which instantiates the weatherIO object and obtains the arrayList data from it.
	 * @param filename the input filename
	 */
	public ClimateAnalyzer(String filename) {
		object = new WeatherIO();
		arrayData = object.readDataFromFile(filename);

	}

	/**
	 * This method finds the lowest temperature for a given month for a specific country
	 * @param country the input country
	 * @param month the integer value of month
	 * @return lowest the ITemperature value which is the lowest temperature
	 */
	public ITemperature getLowestTempByMonth(String country, int month) {
		// TASK A-1
		// for all data that matches the specified month, get the lowest temperature
		// reading
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1]; //finds the equivalent month to the integer value inputed

		ArrayList<ITemperature> smallerArray = new ArrayList<ITemperature>(); // data is added to arrayList if it matches the year and month criteria
		for (ITemperature i : arrayData) { 
			if (i.getCountry().toUpperCase().equals(country) && i.getMonth().equals(theMonth)) {
				smallerArray.add(i);
			}
		}
		if (smallerArray.size() >= 1) { //if narrowed down arrayList is not empty, then the lowest temperature is searched for.
			ITemperature lowest = smallerArray.get(0);
			for (ITemperature j : smallerArray) {
				if (j.getTemperature(false) < lowest.getTemperature(false)) {
					lowest = j;
				}
			}
			return lowest;
		}
		return null;

	}
	
	/**
	 * This method finds the highest temperature for a given month for a specific country
	 * @param country the input country
	 * @param month the integer value of month
	 * @return highest the ITemperature value which is the highest temperature
	 */
	public ITemperature getHighestTempByMonth(String country, int month) {
		// TASK A-1
		// for all data that matches the specified month, get the highest temperature
		// reading
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1]; //finds the equivalent month to the integer value inputed

		ArrayList<ITemperature> smallerArray = new ArrayList<ITemperature>();
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country) && i.getMonth().equals(theMonth)) {
				smallerArray.add(i);
			}
		}
		if (smallerArray.size() >= 1) { //finds highest value if arrayList of narrowed down values is not empty
			ITemperature highest = smallerArray.get(0);
			for (ITemperature j : smallerArray) {
				if (j.getTemperature(false) > highest.getTemperature(false)) {
					highest = j;
				}
			}
			return highest;
		}

		return null;
	}
	
	/**
	 * This method finds the data with the lowest temperature for a given year for a given country
	 * @param country the input country
	 * @param year the input year
	 * @return lowest the ITemperature value which is the lowest temperature
	 */
	public ITemperature getLowestTempByYear(String country, int year) {
		// TASK A-2
		// for all data that matches the specified year, get the lowest temperature
		// reading
		ArrayList<ITemperature> lowestArray = new ArrayList<ITemperature>(); //arrayList of values that match country and year
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country) && i.getYear() == year)
				lowestArray.add(i);
		}
		if (lowestArray.size() >= 1) {
			ITemperature lowest = lowestArray.get(0); //by iterating through the arrayList and comparing temperature values, finds the lowest
			for (ITemperature j : lowestArray) {
				if (j.getTemperature(false) < lowest.getTemperature(false))
					lowest = j;
			}
			return lowest;
		}
		return null;

	}

	/**
	 * This method finds the data with the highest temperature for a given year for a given country
	 * @param country the input country
	 * @param year the input year
	 * @return highest the ITemperature value which is the highest temperature
	 */
	public ITemperature getHighestTempByYear(String country, int year) {
		// TASK A-2
		// for all data that matches the specified year, get the highest temperature
		// reading
		ArrayList<ITemperature> highestArray = new ArrayList<ITemperature>(); //arrayList of values that match country and year
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country) && i.getYear() == year)
				highestArray.add(i);
		}
		if (highestArray.size() >= 1) {
			ITemperature highest = highestArray.get(0);//by iterating through the arrayList and comparing temperature values, finds the highest
			for (ITemperature j : highestArray) {
				if (j.getTemperature(false) > highest.getTemperature(false))
					highest = j;
			}

			return highest;
		}
		return null;
	}

	/**
	 * This method finds all the data with two given temperature ranges for a given country
	 * @param country the input country
	 * @param rangeLowTemp the lower range
	 * @param rangeHighTemp the higher range
	 * @return sorted the TreeSet with ordered data of ITemperatures
	 */
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp) {
		// TASK A-3
		// get all temperature data that fall within the given temperature range
		// the set is sorted from lowest to highest temperature
		// input parameter values are in Celsius
		// check: are ranges inclusive?

		ArrayList<ITemperature> countryTempData = new ArrayList<ITemperature>(); //adds ITemperature value if all inputs match
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country) && i.getTemperature(false) >= rangeLowTemp
					&& i.getTemperature(false) <= rangeHighTemp) {
				countryTempData.add(i);
			}
		}
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>(countryTempData); //sorts arraylist
		return sorted;
	}

	/**
	 * This method finds the lowest temperature for the given country
	 * @param country the input country
	 * @return lowestTemp the ITemperature value
	 */
	public ITemperature getLowestTempYearByCountry(String country) {
		// TASK A-4
		// 1. get the lowest temperature reading amongst all data for that country
		ArrayList<ITemperature> specificCountry = new ArrayList<ITemperature>(); //narrows down to only country values
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country))
				specificCountry.add(i);
		}

		if (specificCountry.size() >= 1) { 
			ITemperature lowestTemp = specificCountry.get(0); //iterates through each value to find the one with lowest temperature
			for (ITemperature j : specificCountry) {
				if (j.getTemperature(false) < lowestTemp.getTemperature(false))
					lowestTemp = j;
			}
			return lowestTemp;
		}
		return null;

	}

	/**
	 * This method finds the highest temperature for the given country
	 * @param country the input country
	 * @return highestTemp the ITemperature value
	 */
	public ITemperature getHighestTempYearByCountry(String country) {
		// TASK A-4
		// 1. get the highest temperature reading amongst all data for that country
		ArrayList<ITemperature> specificCountry = new ArrayList<ITemperature>(); //narrows down to only country values
		for (ITemperature i : arrayData) {
			if (i.getCountry().toUpperCase().equals(country))
				specificCountry.add(i);
		}

		if (specificCountry.size() >= 1) {
			ITemperature highestTemp = specificCountry.get(0); //iterates through each value to find the one with highest temperature
			for (ITemperature j : specificCountry) {
				if (j.getTemperature(false) > highestTemp.getTemperature(false))
					highestTemp = j;
			}
			return highestTemp;
		}
		return null;
	}

	/**
	 * This method finds data for the top ten countries with lowest temperatures for a given month.
	 * @param month the number value of the month
	 * @return topLowestCountries ArrayList of ITemperature values
	 */
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) {
		// TASK B-1
		// 1. the return list is sorted from lowest to highest temperature
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1];//finds the equivalent month to the integer value inputed

		ArrayList<ITemperature> specificMonth = new ArrayList<ITemperature>(); //narrows down to values matching the given month
		for (ITemperature t : arrayData) {
			if (t.getMonth().equals(theMonth))
				specificMonth.add(t);
		}
		TreeSet<ITemperature> tree = new TreeSet<ITemperature>(specificMonth); //orders these values

		ArrayList<ITemperature> topLowestCountries = new ArrayList<ITemperature>();//puts these values back into this arrayList to match return type of method
		HashSet<String> countries = new HashSet<String>(); //makes sure that the countries are of unique type
		//in the method 10 signifies that we want only the top ten values
		for (ITemperature w : tree) {
			if (topLowestCountries.size() != 10 && !countries.contains(w.getCountry())) {
				topLowestCountries.add(w);
				countries.add(w.getCountry());
			}

		}
		return topLowestCountries;

	}

	/**
	 * This method finds data for the top ten countries with highest temperatures for a given month.
	 * @param month the number value of the month
	 * @return finaltopHighestCountries the ArrayList of ITemperature values
	 */
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) {
		// TASK B-1
		// 1. the return list is sorted from lowest to highest temperature
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1];//finds the equivalent month to the integer value inputed

		ArrayList<ITemperature> specificMonth = new ArrayList<ITemperature>(); //contains value matching the month
		for (ITemperature t : arrayData) {
			if (t.getMonth().equals(theMonth))
				specificMonth.add(t);
		}
		TreeSet<ITemperature> tree = new TreeSet<ITemperature>(specificMonth); //orders these values
		TreeSet<ITemperature> treeReverse = (TreeSet<ITemperature>) tree.descendingSet(); //reverses the order to be highest to lowest

		ArrayList<ITemperature> topHighestCountries = new ArrayList<ITemperature>();
		HashSet<String> countries = new HashSet<String>();//makes sure that the countries are of unique type

		//in the method 10 signifies that we want only the top ten values
		for (ITemperature w : treeReverse) {
			if (topHighestCountries.size() != 10 && !countries.contains(w.getCountry())) {
				topHighestCountries.add(w);
				countries.add(w.getCountry());
			}

		}
		TreeSet<ITemperature> treeReverseAgain = new TreeSet<ITemperature>(topHighestCountries); //reverses the order again to be lowest to highest
		ArrayList<ITemperature> finaltopHighestCountries = new ArrayList<ITemperature>(treeReverseAgain); //values placed into arrayList to match return type of method
		return finaltopHighestCountries;
	}

	/**
	 * This method returns the top ten countries with lowest temperatures in all the data.
	 * @return topLowestCountries ArrayList of ITemperatures
	 */
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp() {
		// TASK B-2
		// 1. the return list is sorted from lowest to highest temperature
		TreeSet<ITemperature> tree = new TreeSet<ITemperature>(arrayData); //ordered data

		ArrayList<ITemperature> topLowestCountries = new ArrayList<ITemperature>(); //top ten values are added
		HashSet<String> countries = new HashSet<String>(); //makes sure the countries are unique

		for (ITemperature w : tree) {
			if (topLowestCountries.size() != 10 && !countries.contains(w.getCountry())) {
				topLowestCountries.add(w);
				countries.add(w.getCountry());
			}

		}
		return topLowestCountries;
	}

	/**
	 * This method returns the top ten countries with highest temperatures in all the data.
	 * @return finaltopHighestCountries ArrayList of ITemperatures
	 */
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp() {
		// TASK B-2
		// 1. the return list is sorted from lowest to highest temperature
		TreeSet<ITemperature> tree = new TreeSet<ITemperature>(arrayData);//ordered data
		TreeSet<ITemperature> treeReverse = (TreeSet<ITemperature>) tree.descendingSet();//reversed to have highest at the top

		ArrayList<ITemperature> topHighestCountries = new ArrayList<ITemperature>();//top ten values are added
		HashSet<String> countries = new HashSet<String>();//makes sure the countries are unique

		for (ITemperature w : treeReverse) {
			if (topHighestCountries.size() != 10 && !countries.contains(w.getCountry())) {
				topHighestCountries.add(w);
				countries.add(w.getCountry());
			}

		}
		TreeSet<ITemperature> treeReverseAgain = new TreeSet<ITemperature>(topHighestCountries);//Reversed again to be lowest to highest
		ArrayList<ITemperature> finaltopHighestCountries = new ArrayList<ITemperature>(treeReverseAgain); //arraylist to match return type
		return finaltopHighestCountries;
	}

	/**
	 * This method gets the data for all countries that fall within a given temperature range.
	 * @param lowRangeTemp the lower range
	 * @param highRangeTemp the higher range
	 * @return countriesTempData the ArrayList of ITemperature values
	 */
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp) {
		// TASK B-3
		// 1. the return list is sorted from lowest to highest temperature
		ArrayList<ITemperature> countriesTempData = new ArrayList<ITemperature>();
		for (ITemperature i : arrayData) {
			if (i.getTemperature(false) >= lowRangeTemp && i.getTemperature(false) <= highRangeTemp) {
				countriesTempData.add(i);
			}
		}
		Collections.sort(countriesTempData); //sorts the values that satisfy the ranges
		return countriesTempData;
	}

	/**
	 * This method finds data for the top ten countries that have the highest temp delta
	 * @param month the input month
	 * @param year1 the first inputed year
	 * @param year2 the second inputed year
	 * @return finalTopTen the ArrayList of ITemperature values
	 */
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2) {
		// TASK C-1
		// 1. the countries with the largest temperature differences(absolute value)of
		// the same month between 2 given years.
		// 2. the return list is sorted from lowest to highest temperature delta
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1]; //converts integer month value to the corresponding equivalent string

		ArrayList<ITemperature> smallerArray = new ArrayList<ITemperature>(); //values matching month and both years

		for (ITemperature i : arrayData) {
			if (i.getMonth().equals(theMonth) && (i.getYear() == year1 || i.getYear() == year2))
				smallerArray.add(i);
		}

		ArrayList<ITemperature> deltaTemp = new ArrayList<ITemperature>(); //delta temperature values stored
		for (int i = 0; i < smallerArray.size(); i = i + 2) { //iterates by two because the countries are grouped together already
			ITemperature nextTemp = smallerArray.get(i + 1); //the values next to i is stored
			double temp1 = smallerArray.get(i).getTemperature(false);  //temperature of i
			double temp2 = nextTemp.getTemperature(false);//temperature of the value next to i

			//new temperature value created based on delta values
			deltaTemp.add(new Temperature(Math.abs(temp1 - temp2), Math.abs(year1 - year2), smallerArray.get(i).getMonth(), smallerArray.get(i).getCountry(), smallerArray.get(i).getCountry3LetterCode()));
		}

		TreeSet<ITemperature> orderedDeltaTemp = new TreeSet<ITemperature>(deltaTemp);//orders all delta temperature objects
		TreeSet<ITemperature> orderedDeltaTempReverse = (TreeSet<ITemperature>) orderedDeltaTemp.descendingSet(); //reverses data to be highest to lowest

		HashSet<String> countries = new HashSet<String>(); //makes sure the countries are unique
		ArrayList<ITemperature> topTen = new ArrayList<ITemperature>();
		for (ITemperature s : orderedDeltaTempReverse) {
			if (!countries.contains(s.getCountry()) && topTen.size() < 10) {
				topTen.add(s); //only added if the arrayList does not contain 10 objects yet
				countries.add(s.getCountry());
			}
		}
		TreeSet<ITemperature> topTenOrdered = new TreeSet<ITemperature>(topTen); //ordered from lowest to highest
		ArrayList<ITemperature> finalTopTen = new ArrayList<ITemperature>(topTenOrdered); //placed into arrayList to match return type
		return finalTopTen;
	}

	/**
	 * This method puts all the other necessary methods into action.
	 */
	public void runClimateAnalyzer() {
		// 1. This method starts the climate-change task activities
		// 2. The ClimateChange methods must be called in the order as listed in
		// the[description section], (first with the Task A
		// methods, second are the Task B methods, and third are the Task C methods)
		// 3. For each of the ClimateChange methods that require input parameters, this
		// method must ask the user to
		// enter the required information for each of the tasks.
		// 4. Each ClimateAnalyzer method returns data, so the data results must be
		// written to data files

		Scanner in = new Scanner(System.in);

		ArrayList<String> countries = new ArrayList<String>();
		for (ITemperature t : arrayData) {
			countries.add(t.getCountry().toUpperCase()); //to upper case to account for any differences in comparing capital or lower case user input
		}
		
		sop("Welcome!");

		// A1-lowest temp
		sop("Task A1: Data for the Lowest Temperature Reading for a given month for a specific country.");
		sop("Enter the country you want to find the lowest temperature by month of: ");
		String country = in.nextLine().toUpperCase();
		while (!countries.contains(country)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country = in.nextLine().toUpperCase();
		}
		int month;
		sop("What month do you want to find the lowest temperature of? "); 
		do { 
			sop("Enter a valid numerical value of a month: "); //asks the user to enter a month until a valid input is achieved
			while (!in.hasNextInt()) { 
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			month = in.nextInt();
		} while (month <= 0 || month > 12);

		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth = months[month - 1];

		ArrayList<ITemperature> lowestArray = new ArrayList<ITemperature>();
		lowestArray.add(getLowestTempByMonth(country, month));

		object.writeSubjectHeaderInFile("A1",
				"Task A1: Lowest Temperature for " + country + " for the Month of " + theMonth);
		object.writeDataToFile("A1", "Temperature, Year, Month_Avg, Country, Country_Code", lowestArray);

		// A1-highest temp
		in.nextLine();
		sop("Task A1: Data for the Highest Temperature Reading for a given month for a specific country.");
		sop("Enter the country you want to find the highest temperature by month of: ");
		String country1 = in.nextLine().toUpperCase();
		while (!countries.contains(country1)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country1 = in.nextLine().toUpperCase();
		}
		int month1;
		sop("What month do you want to find the highest temperature of? ");
		do {
			sop("Enter a valid numerical value of a month: "); //asks the user to enter a month until a valid input is achieved
			while (!in.hasNextInt()) {
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			month1 = in.nextInt();
		} while (month1 <= 0 || month1 > 12);

		String[] months1 = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth1 = months1[month1 - 1];

		ArrayList<ITemperature> highestArray = new ArrayList<ITemperature>();
		highestArray.add(getHighestTempByMonth(country1, month1));

		object.writeSubjectHeaderInFile("A1",
				"Task A1: Highest Temperature for " + country1 + " for the Month of " + theMonth1);
		object.writeDataToFile("A1", "Temperature, Year, Month_Avg, Country, Country_Code", highestArray);

		// A2-lowest temp
		in.nextLine();
		sop("Task A2: Data for the month with Lowest Temperature in a given year for a specific country.");
		sop("Enter the country you want to find the lowest temperature by year of: ");
		String country2 = in.nextLine().toUpperCase();
		while (!countries.contains(country2)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country2 = in.nextLine().toUpperCase();
		}
		int year;
		sop("What year do you want to look at? ");
		do {
			sop("Enter a valid year between 2000 and 2016: "); //asks the user to enter a year until a valid input is achieved
			while (!in.hasNextInt()) {
				sop("That is not a valid year. Please input again: ");
				in.next();
			}
			year = in.nextInt();
		} while (year < 2000 || year > 2016);

		ArrayList<ITemperature> lowestArray1 = new ArrayList<ITemperature>();
		lowestArray1.add(getLowestTempByYear(country2, year));
		object.writeSubjectHeaderInFile("A2",
				"Task A2: Lowest Temperature for " + country2 + " for the Year of " + year);
		object.writeDataToFile("A2", "Temperature, Year, Month_Avg, Country, Country_Code", lowestArray1);

		// A2-highest temp
		in.nextLine();
		sop("Task A2: Data for the month with Highest Temperature in a given year for a specific country.");
		sop("Enter the country you want to find the highest temperature by year of: ");
		String country3 = in.nextLine().toUpperCase();
		while (!countries.contains(country3)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country3 = in.nextLine().toUpperCase();
		}
		int year1;
		sop("What year do you want to look at? ");
		do {
			sop("Enter a valid year between 2000 and 2016: "); //asks the user to enter a year until a valid input is achieved
			while (!in.hasNextInt()) {
				sop("That is not a valid year. Please input again: ");
				in.next();
			}
			year1 = in.nextInt();
		} while (year1 < 2000 || year1 > 2016);

		ArrayList<ITemperature> highestArray1 = new ArrayList<ITemperature>();
		highestArray1.add(getHighestTempByYear(country3, year1));
		object.writeSubjectHeaderInFile("A2",
				"Task A2: Highest Temperature for " + country3 + " for the Year of " + year1);
		object.writeDataToFile("A2", "Temperature, Year, Month_Avg, Country, Country_Code", highestArray1);

		// A3-within range
		in.nextLine();
		sop("Task A3: All data that falls between a specific range of temperatures for a specific country.");
		sop("Enter the country you want to find temperatures with range: ");
		String country4 = in.nextLine().toUpperCase();
		while (!countries.contains(country4)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country4 = in.nextLine().toUpperCase();
		}
		double lower;
		sop("Enter the lower range value: ");
		while (!in.hasNextDouble()) { //makes sure input is a number
			sop("That is not a valid number. Please input again: ");
			in.next();
		}
		lower = in.nextDouble();

		double higher;
		sop("Enter the higher range value: ");
		do {
			sop("Remember! The higher range value should be greater than the lower range value!");
			while (!in.hasNextDouble()) { 
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			higher = in.nextDouble();
		} while (lower > higher); //re asks user for input if higher range value is not actually higher than the lower inputted range

		ArrayList<ITemperature> withinRange = new ArrayList<ITemperature>(getTempWithinRange(country4, lower, higher));
		object.writeSubjectHeaderInFile("A3",
				"Task A3: Highest Temperature for " + country4 + " between " + lower + "C and " + higher + "C");
		object.writeDataToFile("A3", "Temperature, Year, Month_Avg, Country, Country_Code", withinRange);

		// A4-lowest temp
		in.nextLine();
		sop("Task A4: Data for the year with Lowest Temperature for a specific country.");
		sop("Enter the country you want to find the lowest temperature of: ");
		String country5 = in.nextLine().toUpperCase();
		while (!countries.contains(country5)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country5 = in.nextLine().toUpperCase();
		}
		ArrayList<ITemperature> lowestArray2 = new ArrayList<ITemperature>();
		lowestArray2.add(getLowestTempYearByCountry(country5));
		object.writeSubjectHeaderInFile("A4", "Task A4: Lowest Temperature for " + country5);
		object.writeDataToFile("A4", "Temperature, Year, Month_Avg, Country, Country_Code", lowestArray2);

		// A4-highest temp
		sop("Task A4: Data for the year with Highest Temperature for a specific country.");
		sop("Enter the country you want to find the highest temperature of: ");
		String country6 = in.nextLine().toUpperCase();
		while (!countries.contains(country6)) { //while input doesn't match the countries, re asks the user to enter a country
			sop("That is not a valid country. Please input again: ");
			country6 = in.nextLine().toUpperCase();
		}
		ArrayList<ITemperature> highestArray2 = new ArrayList<ITemperature>();
		highestArray2.add(getHighestTempYearByCountry(country6));
		object.writeSubjectHeaderInFile("A4", "Task A4: Highest Temperature for " + country6);
		object.writeDataToFile("A4", "Temperature, Year, Month_Avg, Country, Country_Code", highestArray2);

		// B1-top 10 lowest temp
		sop("Task B1: Data for top 10 countries with Lowest Temperature for a given month between 2000 and 2016.");
		sop("Enter the integer value of the month you want to find the top 10 countries of lowest temperatures of: ");
		int month2;
		do {
			sop("Enter a valid numerical value of a month: "); //asks the user to enter a month until a valid input is achieved
			while (!in.hasNextInt()) {
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			month2 = in.nextInt();
		} while (month2 <= 0 || month2 > 12);

		String[] months2 = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth2 = months2[month2 - 1];
		object.writeSubjectHeaderInFile("B1", "Top 10 Lowest Temperatures for the month of " + theMonth2);
		object.writeDataToFile("B1", "Temperature, Year, Month_Avg, Country, Country_Code",
				allCountriesGetTop10LowestTemp(month2));

		// B1-top 10 highest temp
		in.nextLine();
		sop("Task B1: Data for top 10 countries with Highest Temperature for a given month between 2000 and 2016.");
		sop("Enter the integer value of the month you want to find the top 10 highest temperatures of: ");
		int month3;
		do {
			sop("Enter a valid numerical value of a month: "); //asks the user to enter a month until a valid input is achieved
			while (!in.hasNextInt()) {
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			month3 = in.nextInt();
		} while (month3 <= 0 || month3 > 12);

		String[] months3 = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth3 = months3[month3 - 1];
		object.writeSubjectHeaderInFile("B1", "Top 10 Highest Temperatures for the month of " + theMonth3);
		object.writeDataToFile("B1", "Temperature, Year, Month_Avg, Country, Country_Code",
				allCountriesGetTop10HighestTemp(month3));

		// B2-lowest temp
		in.nextLine();
		sop("Task B2: Data for top 10 countries with Lowest Temperatures between 2000 and 2016.");
		sop("I'll compute the top 10 countries with the lowest temperatures in all the data for you!");
		object.writeSubjectHeaderInFile("B2", "Top 10 Countries of Lowest Temperatures"); //automatically calculates the data since no input is needed
		object.writeDataToFile("B2", "Temperature, Year, Month_Avg, Country, Country_Code",
				allCountriesGetTop10LowestTemp());

		// B2-highest temp
		sop("Task B2: Data for top 10 countries with Highest Temperatures between 2000 and 2016.");
		sop("I'll also compute the top 10 countries with the highest temperatures in all the data for you too!");
		object.writeSubjectHeaderInFile("B2", "Top 10 Countries of Highest Temperatures"); //automatically calculates data since input isn't needed
		object.writeDataToFile("B2", "Temperature, Year, Month_Avg, Country, Country_Code",
				allCountriesGetTop10HighestTemp());

		// B3-temp within range
		sop("Task B3: All data that falls between a specific range of temperatures.");
		double lower1;
		sop("Enter the lower range value: ");
		while (!in.hasNextDouble()) { //checks if input is a number
			sop("That is not a valid number. Please input again: ");
			in.next();
		}
		lower1 = in.nextDouble();

		double higher1;
		sop("Enter the higher range value: ");
		do {
			sop("Remember! The higher range value should be greater than the lower range value!");
			while (!in.hasNextDouble()) {
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			higher1 = in.nextDouble();
		} while (lower1 > higher1); //makes sure higher range is higher than lower range

		object.writeSubjectHeaderInFile("B3",
				"Data for all countries within the temperature range from " + lower1 + " and " + higher1);
		object.writeDataToFile("B3", "Temperature, Year, Month_Avg, Country, Country_Code",
				allCountriesGetAllDataWithinTempRange(lower1, higher1));

		// C1-top 10 delta
		sop("Task C1: Data for top 10 countries with the largest temperature differences of the same month between 2 given years");
		int month4;
		do {
			sop("Enter a valid numerical value of a month: "); //asks the user to enter a month until a valid input is achieved
			while (!in.hasNextInt()) { 
				sop("That is not a valid number. Please input again: ");
				in.next();
			}
			month4 = in.nextInt();
		} while (month4 <= 0 || month4 > 12);

		String[] months4 = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String theMonth4 = months4[month4 - 1];
		int year2;
		sop("What is the first year you want to look at? "); //asks the user to enter a year until a valid input is achieved
		do {
			sop("Enter a valid year between 2000 and 2016: ");
			while (!in.hasNextInt()) {
				sop("That is not a valid year. Please input again: ");
				in.next();
			}
			year2 = in.nextInt();
		} while (year2 < 2000 || year2 > 2016);

		int year3;
		sop("What is the second year you want to look at? "); //asks the user to enter a year until a valid input is achieved
		do {
			sop("Enter a valid year between 2000 and 2016: ");
			while (!in.hasNextInt()) {
				sop("That is not a valid year. Please input again: ");
				in.next();
			}
			year3 = in.nextInt();
		} while (year3 < 2000 || year3 > 2016);

		object.writeSubjectHeaderInFile("C1", "Top 10 countries with largest temperature differences for the month of "
				+ theMonth4 + " between " + year2 + " and " + year3);
		object.writeDataToFile("C1", "Temperature Delta, Year, Month_Avg, Country, Country_Code",
				allCountriesTop10TempDelta(month4, year2, year3));

		sop("Okay, that's it! Enjoy your data files!");
		
		in.close(); //scanner is closed for good practice and making sure info is protected
	}

	public static void main(String[] args) {
		// HARD CODING THE FILENAME:
		ClimateAnalyzer ca = new ClimateAnalyzer("world_temp_2000-2016.csv");
		ca.runClimateAnalyzer();

		// GATHERING USER INPUT FOR FILE NAME:
		// Scanner scan = new Scanner(System.in);
		// sop("Welcome!");
		// sop("Please enter the filename you want to look at: ");
		// String filename = scan.nextLine();
		// ClimateAnalyzer ca = new ClimateAnalyzer(filename);
		// ca.runClimateAnalyzer();

	}

	static void sop(Object x) {
		System.out.println(x);
	}
}
