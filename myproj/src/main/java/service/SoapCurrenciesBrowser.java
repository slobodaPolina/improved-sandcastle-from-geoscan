package service;

import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
// for testing it http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import helpful.Currency;
import helpful.SoapParser;

@Service
public class SoapCurrenciesBrowser {
	@Value(value = "classpath:prepped.msg")
	private Resource preppedResource;

	public ArrayList<Currency> getCbrInfo() {
		try {
			// Создаем соединение
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			// Затем создаем сообщение из файла
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
			SOAPPart soapPart = message.getSOAPPart();

			StreamSource preppedMsgSrc = new StreamSource(preppedResource.getInputStream());
			soapPart.setContent(preppedMsgSrc);
			message.saveChanges();

			// Установка адресата
			String destination = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
			// Отправка
			SOAPMessage response = connection.call(message, destination);

			// получение в answer строки с ответом
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.writeTo(out);
			String answer = out.toString();// кодировку сюда нормальную! при прописанной utf-8 пустая строка. русские
											// буквы не читаются
			connection.close();
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
			// System.out.println(xmlString);
			// here not the answer, but xmlstring
			SoapParser parser = new SoapParser(answer);
			return parser.getresult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}