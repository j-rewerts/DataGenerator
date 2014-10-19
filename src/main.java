import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;


public class main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Error: must be of the form EXE outputfile numberOfGeneratedValues");
			return;
		}
		
		String outputFileName = args[0];
		int genVals = 0;
		
		try {
			genVals = Integer.parseInt(args[1]);
		} catch (NumberFormatException nfe) {
			System.out.println("Error: numberOfGeneratedValues parse to int failed.");
			return;
		}
		
		if (genVals < 0) {
			System.out.println("Error: numberOfGeneratedValues must be positive.");
			return;
		}
		
		// Generate generatedVals random values.		
		ArrayList<Integer> randomInts = getRandomIntegers(genVals, 1000000);
		
		// Convert to a string
		String randomString = new String();
		for (Integer i : randomInts) {
			randomString += i.toString() + "\n";
		}
		
		// Write the random ints to file.
		writeToFile(outputFileName, randomString);
	}
	
	/**
	 * 
	 * @param number The number of random values to generate.
	 * @param range the range from 0 to n-1 where n = range.
	 * @return An ArrayList of random Integers
	 */
	private static ArrayList<Integer> getRandomIntegers(int number, int range) {
		ArrayList<Integer> randomInts = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			Integer randomInt = new Integer(random.nextInt(range));
			randomInts.add(randomInt);
		}
		return randomInts;
	}
	
	/**
	 * Writes a String to an output file.
	 * @param outputFile
	 * @param output
	 */
	private static void writeToFile(String outputFile, String output) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
			writer.write(output);
		} catch (IOException ex) {
			System.out.println("Error: failed writing to file.");
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				System.out.println("Error: failed closing output file.");
			}
		}
	}

}
