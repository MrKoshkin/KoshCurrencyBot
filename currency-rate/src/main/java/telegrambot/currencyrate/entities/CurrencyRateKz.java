package telegrambot.currencyrate.entities;

import lombok.Data;

@Data
public class CurrencyRateKz {
    private String date;
    private String charCode;
    private double value;
    private String change;

    @Override
    public String toString() {
        return "CurrencyRateKz{" +
                "date='" + date + '\'' +
                ", charCode='" + charCode + '\'' +
                ", value=" + value +
                ", change='" + change + '\'' +
                '}';
    }
}
