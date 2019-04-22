package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import helpful.Currency;

@Service
public class SoapParser {

	private ArrayList<Currency> result = new ArrayList<Currency>();

	public void parse(String request) {
		String arr[] = request.split("<EnumValutes>");
		// the first line is empty, so lets start from the second one
		for (int i = 1; i < arr.length; i++) {
			String tmpEngName = "", tmpRusName = "", tmpCharCode = "", tmpNumCode = "";
			tmpEngName = findValue(arr[i], "VEngname");
			tmpRusName = findValue(arr[i], "Vname");
			tmpCharCode = findValue(arr[i], "VcharCode");
			tmpNumCode = findValue(arr[i], "VnumCode");
			result.add(new Currency(tmpEngName, tmpRusName, tmpCharCode, tmpNumCode));
		}
		for (int i = 0; i < result.size() - 1; i++) {
			if (result.get(i).getEngName().equals(result.get(i + 1).getEngName())) {
				result.remove(i);
				i--;
			}
		}
	}

	public ArrayList<Currency> getresult() {
		return result;
	}

	private String findValue(String line, String border) {
		try {
			String result = "";
			int j = 0;
			while (!line.substring(j, j + 2 + border.length()).equals("<" + border + ">")) {
				j++;
			}
			j = j + 2 + border.length();
			while (line.charAt(j) != '<') {
				result += line.charAt(j);
				j++;
			}
			return delspaces(result);
		} catch (Exception e) {
			return ""; // seems there is no such tag))
		}
	}

	private String delspaces(String s) {
		int i = s.length() - 1;
		while (!alnum(s.charAt(i))) {
			s = s.substring(0, i);
			i--;
		}
		return s;
	}

	private boolean alnum(char i) {
		return (i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i <= '9' && i >= '0') || (i <= 'ÿ' && i >= 'à')
				|| (i <= 'ß' && i >= 'À');
	}

	public String decode(ByteArrayOutputStream out) {
		try {
			// Here is the code to change answer to normal String with russian letters
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// use the factory to create a documentbuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// create a new document from input stream
			Document doc = builder.parse(new ByteArrayInputStream(out.toByteArray()));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			String xmlString = result.getWriter().toString();
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
