import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
/**
 * Servlet implementation class WebServer
 */
@WebServlet("/WebServer")
public class WebServer extends HttpServlet 
{
	public ParserContext myParserContext = new ParserContext();
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor. 
	 */
	public WebServer() 
	{
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Glass Webserver</title></head><body><h3>Glass Web Server</h3></body><html>");
		out.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = null;
		try 
		{
			out = response.getWriter();
			//read input string
			StringBuilder xmlPosted = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) 
			{
				xmlPosted.append(line);
			}
			reader.close();
			/*************** Strategy Pattern *************/
			System.out.println(xmlPosted.toString());
			myParserContext.setXML(xmlPosted.toString());
			myParserContext.assignParsingStrategy();
			DocumentBuilder db = null;
			Document doc = null;
			try 
			{
				db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(xmlPosted.toString()));
				doc = db.parse(is);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			out.println(myParserContext.parseXML(doc));
			/************************************************/
		} 
		catch (Exception ex1) 
		{

		}
		finally
		{
			if (out != null) out.close();
		}
	}
}