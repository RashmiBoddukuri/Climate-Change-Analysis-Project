package climatechange;


import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the WeatherIO class which implements the IWeatherIO with all the necessary methods.
 * @author Rashmi Boddukuri
 * @version 4/23/2020
 */
public class WeatherIO implements IWeatherIO {

	/**
	 * This method reads a file and stores its data.
	 * @param filename the string filename input
	 * @return infoArray the arrayList of data containing of the Temperature objects
	 */
	@Override
	public ArrayList<ITemperature> readDataFromFile(String fileName) { // read all data from the weather data file
		File inputData = new File("./data/" + fileName); // use "world_temp_2000-2016.csv" which is the file provided for use in the project
		ArrayList<ITemperature> infoArray = new ArrayList<ITemperature>();

		Scanner scan = null;
		try {
			scan = new Scanner(inputData); //checks to see if the file can actually be read
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.nextLine();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] items = line.split(","); //each line is split into parts based on the commas, since the commas are what separate the columns in the csv file
			double temp = Double.parseDouble(items[0]); //converting the first item in the line into a double which is the temperature value
			int year = Integer.parseInt(items[1].trim()); //converting the second item in the line into an int which is the year value

			// in the form: double temp, int year, String month, String country, String letterCode
			Temperature data = new Temperature(temp, year, items[2].trim(), items[3].trim(), items[4].trim()); //trim all strings to remove empty spaces
			infoArray.add(data);
		}
		return infoArray;
	}

	/**
	 * This method writes the subject into the first line of the file.
	 * @param filename the input filename
	 * @param subject the input subject
	 */
	@Override
	public void writeSubjectHeaderInFile(String filename, String subject) {
		// 1. write the subject header before dumping data returned from each
		// ClimateAnalyzer method
		// 2. a subject header is to be written for each ClimateAnalyzer method call
		// example of subject: "Task A1: Lowest Temperature for Zimbabwe for the Month
		// of Mar"

		// File created = new File("./data/task" + filename + "_climate_info.csv");
		// FileWriter fw = null;
		// BufferedWriter bw = null;
		// PrintWriter pw = null;

		try {
			File created = new File("./data/task" + filename + "_climate_info.csv"); //finds data specified by filename in the data folder
			if (!created.exists())
				created.createNewFile(); //creates new file if it doesn't exist
			FileWriter fw = new FileWriter(created, true); //appends in the mentioned case above
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(subject); //prints the subject in the file

			//System.out.println("Data Successfully appended into file"); used this line to check if data was being appended to the file
			pw.flush();

			pw.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method adds the topic to the file and the data from the provided arrayList to follow that in the file
	 * @param filename the input filename
	 * @param topic the input topic similar to a sub header, in our case serves as the headers for each column
	 * @param theWeatherList the input arrayList of data that is passed through
	 */
	@Override
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList) {
		// 1. file name should be called “taskXX_climate_info.csv”where XX will be
		// replaced by the task id: A1, A2, etc
		// 2. use this method to store the temperature info(for each ClimateAnalyzer
		// task)
		// a) one row for each temperature data object (i.e. all fields in one row (each
		// comma delimited))
		// b) similar to the original input data file)
		// 3. temperature value should be formatted to use a maximum of 2 decimal places
		// 4.temperature field should also show the Fahrenheit value (using decimal
		// rules above)
		// a) the temperature field should look like i.e. 21.34(C) 70.42(F)
		// topic is probably going to be : Temperature, Year, Month_Avg, Country,
		// Country_Code

		try {
			File created = new File("./data/task" + filename + "_climate_info.csv"); //finds data specified by filename in the data folder
			created.createNewFile();

			FileWriter fw = new FileWriter(created, true); //appends in the case of existence
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(topic); //prints the topic first
			for (ITemperature t : theWeatherList) {

				double twoCDecimals = Math.round(t.getTemperature(false) * 100) / 100.0; //cuts off the celsius temperature at max two decimals
				double twoFDecimals = Math.round(t.getTemperature(true) * 100) / 100.0; //cuts off the fahrenheit temperature at max two decimals
				String temp = twoCDecimals + "(C) " + twoFDecimals + "(F)"; //combines two values together to display both celsius and fahrenheit

				//prints the data of the arrayList into the file
				pw.println(temp + "," + t.getYear() + "," + t.getMonth() + "," + t.getCountry() + "," + t.getCountry3LetterCode());
			}
			pw.close();
			bw.close();
			fw.close();

		} catch (IOException x) {
			sop("Error: " + x.getMessage());
		}

	}

	public static void main(String[] args) {
		//USED FOR TESTING PURPOSES:
		//String input = "world_temp_2000-2016.csv";
		//WeatherIO object = new WeatherIO();
		//sop(object.readDataFromFile(input).toString());
		//ArrayList<ITemperature> arrayData = object.readDataFromFile(input);
		//object.writeSubjectHeaderInFile("A1", "Task A1: Lowest Temperature for Zimbabwe for the Month of Mar");
		//object.writeDataToFile("A1", "Temperature, Year, Month_Avg, Country, Country_Code", arrayData);
	}

	static void sop(Object x) { System.out.println(x); } //shorter way to access system.out.println !!!
}
