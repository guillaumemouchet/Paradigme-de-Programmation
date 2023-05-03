
package ch.hearc.SA.labo2.BlockingQueue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ch.hearc.SA.labo2.BlockingQueue.Performance.CalculatorPerformance;
import ch.hearc.SA.labo2.BlockingQueue.Use.UseMain;

public class Main
	{

	//JConsole C:\Soft\java\jdk\bin\jconsole.exe
	public static void main(String[] args)
		{
		ArrayList<Double> importantValues;
		StringBuilder sb = new StringBuilder();
		sb.append("NbProducteurs;  NbConsommateurs; Queue Size; Elapsed time [s]; NbActions ; D�bit Sort [a/s] ;D�bit Create [a/s];Latency Sort [ms]; Latency Create [ms];\n");

		// Define arrays for testing values
		int[] nbProducteursArray = { 1, 3, 5 };
		int[] nbConsommateursArray = { 3, 5, 9 };
		int[] queueSizeArray = { 1, 2, 4, 8 };
		int[] executionTimeArray = { 10000, 15000, 20000 };
		//							 10s,	15s,    20s
		// Loop through all possible combinations of values
		for(int nbProducteurs:nbProducteursArray)
			{
			for(int nbConsommateurs:nbConsommateursArray)
				{
				for(int queueSize:queueSizeArray)
					{
					for(int executionTime:executionTimeArray)
						{

						UseMain usemain = new UseMain();
						usemain.setValues(nbProducteurs, nbConsommateurs, queueSize, executionTime);
						System.out.println("--------------------------------------------------------------------------------");

						System.out.println("Settings : " + nbProducteurs + " " + nbConsommateurs + " " + queueSize + " " + executionTime);
						usemain.start();
						try
							{
							usemain.join();
							}
						catch (InterruptedException e)
							{
							// TODO Auto-generated catch block
							e.printStackTrace();
							}

						//add them in reverse order
						importantValues = CalculatorPerformance.getImportantValues();
						importantValues.add(0, (double)executionTime / 1000);
						importantValues.add(0, (double)queueSize);
						importantValues.add(0, (double)nbConsommateurs);
						importantValues.add(0, (double)nbProducteurs);

						// Add the values to the CSV string
						for(Double value:importantValues)
							{
							String val = value.toString();
							val = val.replace('.', ',');
							sb.append(val).append(";");
							}
						sb.deleteCharAt(sb.length() - 1);
						sb.append("\n");
						}
					}
				}
			}
		writeToFile(sb);
		}

	private static void writeToFile(StringBuilder sb)
		{
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Writing in a file");

		try
			{
			File file = new File(".\\Results.csv");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(sb.toString());
			fileWriter.close();
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		System.out.println("Writing ended, see results in Results.csv");

		}
	}
