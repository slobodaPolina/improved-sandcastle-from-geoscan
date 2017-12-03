package helpful;

import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
// for testing it http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL

public class SoapCurrenciesBrowser {
	private ArrayList<Currency> list;

	public SoapCurrenciesBrowser() {
		try {
			// —оздаем соединение
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			// «атем создаем сообщение из файла
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
			SOAPPart soapPart = message.getSOAPPart();

			StreamSource preppedMsgSrc = new StreamSource(new FileInputStream(
					//"C:\\Users\\ѕолина\\git\\myproj\\src\\main\\resources\\prepped.msg"));
					"WEB-INF\\classes\\prepped.msg"));
			soapPart.setContent(preppedMsgSrc);
			message.saveChanges();

			// ”становка адресата
			String destination = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
			// ќтправка
			SOAPMessage response = connection.call(message, destination);

			// получение в answer строки с ответом
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.writeTo(out);
			String answer = out.toString();// кодировку сюда нормальную! при прописанной utf-8 пуста€ строка. русские буквы не читаютс€
			System.out.println("Ok, i posted soap message and got the answer: ");
			System.out.println(answer);
			connection.close();

			// знаю, надо бы сделать встроенными средствами, но чет сложно такое нагуглить
			SoapParser parser = new SoapParser(answer);
			list = parser.getresult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Currency> getresult() {
		return list;
	}
}