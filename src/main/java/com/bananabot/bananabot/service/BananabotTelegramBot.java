package com.bananabot.bananabot.service;

import com.bananabot.bananabot.common.Currency;
import com.bananabot.bananabot.config.BotConfig;
import com.bananabot.bananabot.dto.openApi.info.allPairs.AllPairsRs;
import com.bananabot.bananabot.webclient.impl.PublicWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bananabot.bananabot.dto.openApi.info.allPairs.AllPairsRs.PairInfo;

@Component
public class BananabotTelegramBot extends TelegramLongPollingBot {
    private PublicWebClient publicWebClient = new PublicWebClient();;

    @Autowired
    private BotConfig botConfig;

    private long chatId;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());break;
                case "/allPairs":
                    allPairsCommandReceived(chatId);break;
                case "/pair":
                    try {
                        execute(showCurrencyKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default: sendMessage(chatId, "Такой команды я не знаю!");
            }
        }
        if(update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.contains(Currency.valueOf(call_data).toString())) {
                AllPairsRs result = publicWebClient.callPostInfo(call_data);
                PairInfo pairInfo= result.getPairs().get(call_data);
                sendMessage(chat_id, String.format("Минимальная цена ордера: %s", pairInfo.getMinPrice()));
            }
        }
    }

    public SendMessage showCurrencyKeyboard(long chat_id) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText("Выбери валюту");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<Currency> btcCurrencies = Arrays.asList(Currency.BTC_USD, Currency.BTC_RUB, Currency.BTC_EUR, Currency.BTC_USDT);
        List<Currency> ethCurrencies1 = Arrays.asList(Currency.ETH_USD, Currency.ETH_RUB, Currency.ETH_EUR);
        List<Currency> ethCurrencies2 = Arrays.asList(Currency.ETH_BTC, Currency.ETH_USDT);
        List<Currency> bchCurrencies1 = Arrays.asList(Currency.BCH_USD, Currency.BCH_RUB, Currency.BCH_EUR, Currency.BCH_BTC);
        List<Currency> bchCurrencies2 = Arrays.asList(Currency.BCH_ETH, Currency.BCH_DASH, Currency.BCH_USDT, Currency.BCH_XRP);
        List<Currency> dashCurrencies1 = Arrays.asList(Currency.DASH_USD, Currency.DASH_RUB, Currency.DASH_EUR);
        List<Currency> dashCurrencies2 = Arrays.asList(Currency.DASH_BTC, Currency.DASH_ETH, Currency.DASH_LTC);
        List<Currency> dashCurrencies3 = Arrays.asList(Currency.DASH_USDT, Currency.DASH_XRP);
        List<Currency> ltcCurrencies1 = Arrays.asList(Currency.LTC_USD, Currency.LTC_RUB, Currency.LTC_EUR, Currency.LTC_BTC);
        List<Currency> ltcCurrencies2 = Arrays.asList(Currency.LTC_ETH, Currency.LTC_BCH, Currency.LTC_USDT);
        List<Currency> xrpCurrencies1 = Arrays.asList(Currency.XRP_USD, Currency.XRP_RUB, Currency.XRP_EUR, Currency.XRP_BTC);
        List<Currency> xrpCurrencies2 = Arrays.asList(Currency.XRP_USDT, Currency.XRP_ETH, Currency.XRP_LTC);
        List<Currency> eurCurrencies = Arrays.asList(Currency.EUR_USD, Currency.EUR_USDT, Currency.EUR_RUB);
        List<Currency> usdCurrencies = Arrays.asList(Currency.USD_RUB, Currency.USDT_USD, Currency.USDT_RUB);
        List<Currency> dogeCurrencies1 = Arrays.asList(Currency.DOGE_USD, Currency.DOGE_USDT, Currency.DOGE_RUB);
        List<Currency> dogeCurrencies2 = Arrays.asList(Currency.DOGE_EUR, Currency.DOGE_BTC);
        List<Currency> trxCurrencies1 = Arrays.asList(Currency.TRX_USD, Currency.TRX_USDT, Currency.TRX_RUB);
        List<Currency> trxCurrencies2 = Arrays.asList(Currency.TRX_EUR, Currency.TRX_BTC);
        List<Currency> bnbCurrencies1 = Arrays.asList(Currency.BNB_USD, Currency.BNB_USDT, Currency.BNB_RUB);
        List<Currency> bnbCurrencies2 = Arrays.asList(Currency.BNB_EUR, Currency.BNB_BTC);
        List<Currency> dotCurrencies1 = Arrays.asList(Currency.DOT_USDT, Currency.DOT_RUB, Currency.DOT_EUR);
        List<Currency> dotCurrencies2 = Arrays.asList(Currency.DOT_BTC, Currency.DAI_USDT);
        List<Currency> maticCurrencies1 = Arrays.asList(Currency.MATIC_USDT, Currency.MATIC_RUB, Currency.MATIC_EUR);
        List<Currency> maticCurrencies2 = Arrays.asList(Currency.MATIC_BTC, Currency.USDC_USDT);

        createRow(btcCurrencies, rowList);
        createRow(ethCurrencies1, rowList);
        createRow(ethCurrencies2, rowList);
        createRow(bchCurrencies1, rowList);
        createRow(bchCurrencies2, rowList);
        createRow(dashCurrencies1, rowList);
        createRow(dashCurrencies2, rowList);
        createRow(dashCurrencies3, rowList);
        createRow(ltcCurrencies1, rowList);
        createRow(ltcCurrencies2, rowList);
        createRow(xrpCurrencies1, rowList);
        createRow(xrpCurrencies2, rowList);
        createRow(eurCurrencies, rowList);
        createRow(usdCurrencies, rowList);
        createRow(dogeCurrencies1, rowList);
        createRow(dogeCurrencies2, rowList);
        createRow(trxCurrencies1, rowList);
        createRow(trxCurrencies2, rowList);
        createRow(bnbCurrencies1, rowList);
        createRow(bnbCurrencies2, rowList);
        createRow(dotCurrencies1, rowList);
        createRow(dotCurrencies2, rowList);
        createRow(maticCurrencies1, rowList);
        createRow(maticCurrencies2, rowList);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }

    InlineKeyboardButton createButton(Currency currency) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(currency.toString());
        button.setCallbackData(currency.name());
        return button;
    }

    void createRow(List<Currency> currencies, List<List<InlineKeyboardButton>> rowList) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (Currency currency : currencies) {
            row.add(createButton(currency));
        }
        rowList.add(row);
    }

    private void startCommandReceived(long chatId, String firstName) {
        String answer = "Привет, " + firstName + ", чо каво?";
        sendMessage(chatId, answer);
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try{
            execute(message);
        }
        catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }

    private void allPairsCommandReceived(long chatId){
        String result = publicWebClient.call("/info");
        while(result.length() > 4096){
            String substring = result.substring(0, 4096);
            result = result.substring(4096);
            sendMessage(chatId, substring);
        }

    }
}
