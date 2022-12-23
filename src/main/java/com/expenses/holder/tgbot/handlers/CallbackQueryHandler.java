package com.expenses.holder.tgbot.handlers;

import com.expenses.holder.tgbot.keyboards.InlineKeyboardMaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {
    InlineKeyboardMaker inlineKeyboardMaker;
    public BotApiMethod<?> processCallbackQuery(@NotNull CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        String data = buttonQuery.getData();

        SendMessage sendMessage = new SendMessage(chatId, "Let`s Create record here");
        if (data.equals("CREATE")){
            sendMessage.setReplyMarkup(inlineKeyboardMaker.getWayOfExpense());
        } else if (data.equals("MANUAL")) {
            return new SendMessage(chatId, "*Enter Category:*");
        } else if (data.equals("GEL")|| data.equals("RUB")|| data.equals("USD")|| data.equals("EUR")) {

            return new SendMessage(chatId, "*Информация Получена:*");
        }
        return sendMessage;
    }
    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}