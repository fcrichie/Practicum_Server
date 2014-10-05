

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QRDatabaseAdapter 
{
	String csvFile = "C:/Users/rnunez7/Desktop/DB_File.txt";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	String last = "";
	String first = "";
	public String getPatientInformation(String id)
	{
		String patientInfoXML = "<patient>\n";
		int found = 0;
		String output = "";
		try 
		{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{
				String[] patient = line.split(cvsSplitBy);
				if(patient[0].trim().equals(id))
				{
					patientInfoXML += "    <id>" + patient[0] + "</id>\n";
					patientInfoXML += "    <lastName>" + patient[1] + "</lastName>\n";
					patientInfoXML += "    <firstName>" + patient[2] + "</firstName>\n";
					output = "ID=" + patient[0] + ", Last=" + patient[1] + ", First=" + patient[2];
					found = 1;
				}
			}
			if(output == "")
			{
				output = "Patient not found.";
			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println(output);
		patientInfoXML += "</patient>";
		System.out.println("This shit is sent to the client: " + patientInfoXML);
		return patientInfoXML;
	}
}
