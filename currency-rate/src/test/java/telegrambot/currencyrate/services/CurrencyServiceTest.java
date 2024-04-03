package telegrambot.currencyrate.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import telegrambot.currencyrate.entities.CurrencyRateKz;
import telegrambot.currencyrate.entities.CurrencyRateRus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private CurrencyService currencyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        currencyService = new CurrencyService(restTemplate);
    }

    @Test
    public void testGetCurrencyRatesRus() {
        // Mocking the response from the restTemplate
        String xmlResponse = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>" +
                "<ValCurs Date=\"04.04.2024\" name=\"Foreign Currency Market\">" +
                "<Valute ID=\"R01235\">" +
                "<NumCode>840</NumCode>" +
                "<CharCode>USD</CharCode>" +
                "<Nominal>1</Nominal>" +
                "<Name>Доллар США</Name>" +
                "<Value>100,0000</Value>" +
                "</Valute>" +
                "<Valute ID=\"R01239\">" +
                "<NumCode>978</NumCode>" +
                "<CharCode>EUR</CharCode>" +
                "<Nominal>1</Nominal>" +
                "<Name>Евро</Name>" +
                "<Value>120,0000</Value>" +
                "</Valute>" +
                "</ValCurs>";

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(xmlResponse);

        // Invoke the method under test
        Map<String, CurrencyRateRus> result = currencyService.getCurrencyRatesRus();

        // Assert that the result contains expected currency rates
        assertEquals(2, result.size());
        assertEquals("USD", result.get("USD").getCharCode());
        assertEquals("Доллар США", result.get("USD").getName());
        assertEquals(100.0, result.get("USD").getValue());
        assertEquals("EUR", result.get("EUR").getCharCode());
        assertEquals("Евро", result.get("EUR").getName());
        assertEquals(120.0, result.get("EUR").getValue());
    }

    @Test
    public void testGetCurrencyRatesKz() {
        // Mocking the response from the restTemplate
        String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<rss version=\"2.0\">" +
                "<channel>" +
                "<generator>Alternate RSS Builder</generator>" +
                "<title>Official exchange rates of National Bank of Republic Kazakhstan</title>" +
                "<link>www.nationalbank.kz</link>" +
                "<description>Official exchange rates of National Bank of Republic Kazakhstan</description>" +
                "<language>ru</language>" +
                "<copyright>www.nationalbank.kz</copyright>" +
                "<item>" +
                "<title>USD</title>" +
                "<pubDate>03.04.2024</pubDate>" +
                "<description>419.16</description>" +
                "<quant>1</quant>" +
                "<index>UP</index>" +
                "<change>0.00</change>" +
                "<link/>" +
                "</item>" +
                "<item>" +
                "<title>EUR</title>" +
                "<pubDate>03.04.2024</pubDate>" +
                "<description>476.15</description>" +
                "<quant>1</quant>" +
                "<index>DOWN</index>" +
                "<change>-0.01</change>" +
                "<link/>" +
                "</item>" +
                "</channel>" +
                "</rss>";

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(xmlResponse);

        // Invoke the method under test
        Map<String, CurrencyRateKz> result = currencyService.getCurrencyRatesKz();

        // Assert that the result contains expected currency rates
        assertEquals(2, result.size());
        assertEquals("USD", result.get("USD").getCharCode());
        assertEquals(419.16, result.get("USD").getValue(), 0.001);
        assertEquals("EUR", result.get("EUR").getCharCode());
        assertEquals(476.15, result.get("EUR").getValue(), 0.001);
    }

}
