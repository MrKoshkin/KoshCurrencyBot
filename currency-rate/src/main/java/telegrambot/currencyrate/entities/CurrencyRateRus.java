package telegrambot.currencyrate.entities;

import lombok.Data;


@Data
public class CurrencyRateRus {

    private static String date;
    private String charCode;
    private String name;
    private double value;

    public static void setDate(String date) {
        CurrencyRateRus.date = date;
    }

    public static String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CurrencyRateRus{" +
                "charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
