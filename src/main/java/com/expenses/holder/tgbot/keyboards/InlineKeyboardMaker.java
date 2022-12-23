package com.expenses.holder.tgbot.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Клавиатуры, формируемые в ленте Telegram для получения файлов
 */
@Component
public class InlineKeyboardMaker {




    public ReplyKeyboard getCurrencyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton GEL = new InlineKeyboardButton();
        InlineKeyboardButton RUB = new InlineKeyboardButton();
        InlineKeyboardButton USD = new InlineKeyboardButton();
        InlineKeyboardButton EUR = new InlineKeyboardButton();
        GEL.setText("GEL");
        GEL.setCallbackData("GEL");
        RUB.setText("RUB");
        RUB.setCallbackData("RUB");
        USD.setText("USD");
        USD.setCallbackData("USD");
        EUR.setText("EUR");
        EUR.setCallbackData("EUR");
        rowInLine.add(GEL);
        rowInLine.add(RUB);
        rowInLine.add(USD);
        rowInLine.add(EUR);
        rowsInLine.add(rowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboard getWayOfExpense() {
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton manualButton = new InlineKeyboardButton();
        manualButton.setText("MANUAL");
        manualButton.setCallbackData("MANUAL");
        InlineKeyboardButton autoButton = new InlineKeyboardButton();
        autoButton.setText("AUTO");
        autoButton.setCallbackData("AUTO");
        rowInLine.add(manualButton);
        rowInLine.add(autoButton);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        return markupInLine;

    }
}