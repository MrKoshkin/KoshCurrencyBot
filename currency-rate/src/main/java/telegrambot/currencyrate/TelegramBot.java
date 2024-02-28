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
    private final CurrencyService currencyService;

    private static final String VALID_CURRENCY_PAIR_FORMAT = "^[A-Z]{3}(KZT|RUB)$";


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

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().replaceAll("[^a-zA-Z]", "").toUpperCase();
            long chatId = update.getMessage().getChatId();

            try {
                if (update.getMessage().getText().equals("/start")) {
                    log.info("[chatId: " + chatId + "] " + "Bot started for user: " + update.getMessage().getChat().getUserName());
                    handleStartCommand(chatId, update.getMessage().getChat().getFirstName());
                } else if (isCurrencyPairFormatValid(messageText)) {
                    log.info("[chatId: " + chatId + "] " + "Rate request: " + messageText);
                    handleCurrencyPair(chatId, messageText);
                } else {
                    log.info("[chatId: " + chatId + "] " + "Invalid request: " + messageText );
                    handleInvalidCommand(chatId);
                }
            } catch (Exception e) {
                handleError(chatId, e);
            }

        }

    }

    private void handleStartCommand(Long chatId, String name) {
        String answer = "Привет, " + name + "!" + "\n" +
                "Введите валютную пару, курс который вы хотите узнать" + "\n" +
                "К примеру: \"USDRUB\"";
        sendMessage(chatId, answer);
    }

    private void handleCurrencyPair(Long chatId, String messageText) {
        String baseCurrency = messageText.substring(3);
        String requiredCurrency = messageText.substring(0, 3);

        try {
            String answer;
            if (baseCurrency.equals("RUB")) {
                answer = currencyService.getCurrencyRatesRus().get(requiredCurrency).toString();    // Получение курса с сайта ЦБ РФ
            } else {
                answer = currencyService.getCurrencyRatesKz().get(requiredCurrency).toString();     // Получение курса с сайта нац банка РК
            }
            sendMessage(chatId, answer);
        } catch (Exception e) {
            log.info("[chatId: " + chatId + "] " + "Invalid request: " + messageText );
            handleInvalidCommand(chatId);
        }
    }

    private void handleInvalidCommand(Long chatId) {
        String answer = "Невалидная валютная пара!" + "\n" +
                "Введи валютную пару в формате \"USDRUB\"";
        sendMessage(chatId, answer);
    }

    private void handleError(Long chatId, Exception e) {
        String errorMessage = "Произошла ошибка при обработке запроса";
        log.error("Error: {}", e.getMessage());

        sendMessage(chatId, errorMessage);
    }

    private boolean isCurrencyPairFormatValid(String messageText) {
        return messageText.matches(VALID_CURRENCY_PAIR_FORMAT);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            handleError(chatId, e);
        }
    }
}
