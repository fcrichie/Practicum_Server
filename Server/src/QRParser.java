import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class QRParser extends XMLParser
{
	public QRDatabaseAdapter qrDB = new QRDatabaseAdapter();
	
	@Override
	public String parseXML(Document xmlDoc) throws UnknownHostException, SQLException, IOException 
	{
		NodeList nodes = xmlDoc.getElementsByTagName("request");
		Element element = (Element) nodes.item(0);
		NodeList qrRequest = element.getElementsByTagName("type");
		Element line = (Element) qrRequest.item(0);
		String qrRequestValue = getCharacterDataFromElement(line);
		System.out.println("Request:" + qrRequestValue);
		NodeList patientId = element.getElementsByTagName("param");
		line = (Element) patientId.item(0); 
		String patientIdValue = getCharacterDataFromElement(line);
		System.out.println("Patient ID:" + patientIdValue);
		
		return qrDB.getPatientInformation(patientIdValue);
	}
}
