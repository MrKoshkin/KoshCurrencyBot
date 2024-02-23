package telegrambot.currencyrate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import telegrambot.currencyrate.entities.CurrencyRateKz;
import telegrambot.currencyrate.entities.CurrencyRateRus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.springframework.web.client.RestTemplate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class CurrencyService {

    private static final String URL_RUS = "https://www.cbr.ru/scripts/XML_daily.asp";
    private static final String URL_KZ = "https://nationalbank.kz/rss/rates_all.xml";

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  Map<String, CurrencyRateRus> getCurrencyRatesRus() {
        Map<String, CurrencyRateRus> currencyRateMap = new HashMap<>();

        try {
            String xmlData = restTemplate.getForObject(URL_RUS, String.class);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            doc.getDocumentElement().normalize();
            CurrencyRateRus.setDate(doc.getDocumentElement().getAttribute("Date"));
            NodeList nodeList = doc.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CurrencyRateRus currencyRate = new CurrencyRateRus();
                currencyRate.setCharCode(element.getElementsByTagName("CharCode").item(0).getTextContent());
                currencyRate.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                currencyRate.setValue(Double.parseDouble(element.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.')));

                currencyRateMap.put(currencyRate.getCharCode(), currencyRate);
            }
            log.debug("Data from cbr.ru has been successfully downloaded");

        } catch (Exception e) {
            log.error("Error occurred while fetching currency rates from cbr.ru: {}", e.getMessage());
        }

        return currencyRateMap;
    }

    public  Map<String, CurrencyRateKz> getCurrencyRatesKz() {
        Map<String, CurrencyRateKz> currencyRateMap = new HashMap<>();

        try {
            String xmlData = restTemplate.getForObject(URL_KZ, String.class);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                CurrencyRateKz currencyRate = new CurrencyRateKz();
                currencyRate.setDate(element.getElementsByTagName("pubDate").item(0).getTextContent());
                currencyRate.setCharCode(element.getElementsByTagName("title").item(0).getTextContent());
                currencyRate.setValue(Double.parseDouble(element.getElementsByTagName("description").item(0).getTextContent()));
                currencyRate.setChange(element.getElementsByTagName("change").item(0).getTextContent());

                currencyRateMap.put(currencyRate.getCharCode(), currencyRate);
            }

        } catch (Exception e) {
            log.error("Error occurred while fetching currency rates from nationalbank.kz: {}", e.getMessage());
        }

        return currencyRateMap;
    }
}