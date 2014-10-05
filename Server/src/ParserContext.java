import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.w3c.dom.Document;

public class ParserContext 
{
	private XMLParser parser;
	private String xmlRequest;
	public void setParser(XMLParser p)
	{
		parser = p;
	}
	public void setXML(String xml)
	{
		xmlRequest = xml;
	}
	public String getXmlRequest()
	{
		return xmlRequest;
	}
	public String parseXML(Document xmlDoc) throws NumberFormatException, SQLException, IOException
	{
		return parser.parseXML(xmlDoc);
	}
	public void assignParsingStrategy() throws SQLException, UnknownHostException, IOException
	{
		if( xmlRequest.contains("process_qr"))
		{
			parser = new QRParser();		
		}
//		if(xmlRequest.contains("recognize_patient"))
//		{
//			parser = new RecognizeParser();
//		}
//		if(xmlRequest.contains("upload_image"))
//		{
//			parser = new ImageUploadParser();
//		}
	}
}
