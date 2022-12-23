package com.expenses.holder.tgbot;

import com.expenses.holder.tgbot.handlers.CallbackQueryHandler;
import com.expenses.holder.tgbot.handlers.MessageHandler;
import com.expenses.holder.tgbot.keyboards.ReplyKeyboardMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;

@Slf4j
public class TelegramBot extends SpringWebhookBot {
    private String botToken;
    private String botName;
    private String webhookPath;
    private ReplyKeyboardMaker replyKeyboardMaker;
    MessageHandler messageHandler;
    CallbackQueryHandler callbackQueryHandler;



    public TelegramBot(SetWebhook setWebhook, MessageHandler messageHandler,CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webhookPath;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    e.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                return messageHandler.answerMessage(update.getMessage());
            }
        }
        return null;
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException ex) {
                log.error(String.valueOf(ex));
            }
        }
    }
        public void setWebHookPath (String webHookPath){
            this.webhookPath = webHookPath;
        }

        public void setBotUserName (String botUserName){
            this.botName = botUserName;
        }

        public void setBotToken (String botToken){
            this.botToken = botToken;
        }
    }

//    @Override
//    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        if (message != null && message.hasText()) {
//            String chat_id = String.valueOf(message.getChatId());
//            log.info("New message from User:{}, chatId: {},  with text: {}",
//                    message.getFrom().getUserName(), message.getChatId(), message.getText());
//            try {
//                execute(new SendMessage(chat_id, "Zdarova nahuj! " + message.getText()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

//    @Override
//    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//        var message = update.getMessage();
//        if (message != null && message.hasText()) {
//            log.info("New message from User:{}, chatId: {},  with text: {}",
//                    message.getFrom().getUserName(), message.getChatId(), message.getText());
//            try {
//                var response = new SendMessage();
//                response.setChatId(message.getChatId());
//                response.setText("YYYOooooooouu!!");
////                response.enableMarkdown(true);
////                response.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
//                sendAnswerMessage(response);
//                execute(new SendMessage(String.valueOf(message.getChatId()), "Zdarova nahuj! " + message.getText()));
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return null;
//    }