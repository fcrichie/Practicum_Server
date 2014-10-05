import java.io.IOException;
import java.sql.SQLException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class XMLParser 
{
	public abstract String parseXML(Document xml) throws NumberFormatException, SQLException, IOException;
	/**
	 * Retrieves value from input element element
	 */
	public String getCharacterDataFromElement(Element e) 
	{
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) 
		{
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
