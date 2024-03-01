package telegrambot.currencyrate.entities;

import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class CurrencyRateRus {

    private static String date;
    private String charCode;
    private String name;
    private double value;

    private final static DecimalFormat df = new DecimalFormat("#.##");
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public static void setDate(String date) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        String monthName = localDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru"));
        CurrencyRateRus.date = localDate.getDayOfMonth() + " " + monthName.toLowerCase();
    }


    @Override
    public String toString() {
        return "Курс " + charCode + "/RUB на " + date + " " + df.format(value) + " руб.";
    }
}
