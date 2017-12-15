package service;

import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
// for testing it http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import helpful.Currency;

@Service
public class SoapCurrenciesBrowser {
	@Value(value = "classpath:prepped.msg")
	private Resource preppedResource;
	@Autowired
	private SoapParser parser;

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
			connection.close();
			parser.parse(parser.decode(out));
			return parser.getresult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}