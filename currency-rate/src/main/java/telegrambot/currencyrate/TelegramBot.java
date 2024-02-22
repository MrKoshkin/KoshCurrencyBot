package telegrambot.currencyrate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.currencyrate.config.BotConfig;
import telegrambot.currencyrate.services.CurrencyService;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {


        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    currencyCodeReceived(chatId, messageText);
            }

        }

    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Привет, " + name + "!" + "\n" +
                "Введи валютную пару, курс который ты хочешь узнать" + "\n" +
                "К примеру: \"USDRUB\"";
        sendMessage(chatId, answer);
    }

    private void currencyCodeReceived(Long chatId, String messageText) {

        messageText = messageText.replaceAll("[^a-zA-Z]", "").toUpperCase(); // Убираем лишние символы и приводим к верхнему регистру
        if (messageText.length()!=6) {
            String answer = "Невалидная валютная пара!" + "\n" +
                    "Введи валютную пару в формате \"USDRUB\"";;
            sendMessage(chatId, answer);
            return;
        }

        String baseCurrency = messageText.substring(3);
        String requiredCurrency = messageText.substring(0,3);

        try {
            if (baseCurrency.equals("RUB")) {
                String answer = CurrencyService.getCurrencyRatesRus().get(requiredCurrency).toString();
                sendMessage(chatId, answer);
            } else if (baseCurrency.equals("KZT")) {
                String answer = CurrencyService.getCurrencyRatesKz().get(requiredCurrency).toString();
                sendMessage(chatId, answer);
            } else {
                String answer = "Указанная валюта не поддерживается." + "\n" +
                        "Доступны валютные пары с рублями и тенге";
                sendMessage(chatId, answer);
            }
        } catch (Exception e) {
            //TODO
        }
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //TODO
        }
    }
}
