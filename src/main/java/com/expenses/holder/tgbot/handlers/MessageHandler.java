package com.expenses.holder.tgbot.handlers;

import com.expenses.holder.entity.Expense;
import com.expenses.holder.repository.ExpenseRepo;
import com.expenses.holder.tgbot.keyboards.InlineKeyboardMaker;
import com.expenses.holder.tgbot.keyboards.ReplyKeyboardMaker;
import com.expenses.holder.tgbot.tgbotEnums.BotMessageEnum;
import com.expenses.holder.tgbot.tgbotEnums.ButtonsNameEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class MessageHandler {
    private static final String CREATE_BUTTON = "CREATE";
    private static final String BACK_BUTTON = "BACK";

    ReplyKeyboardMaker replyKeyboardMaker;
    InlineKeyboardMaker inlineKeyboardMaker;

    ExpenseRepo expenseRepo;

    public BotApiMethod<?> answerMessage(Message message) {

        String chatId = message.getChatId().toString();

        String inputText = message.getText();
        Expense expense = new Expense();
        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        } else if (inputText.equals(ButtonsNameEnum.POST_EXPENSE_BUTTON.getButtonName())) {
            return getExpenseMessage(chatId, message);
        } else if (inputText.equals(ButtonsNameEnum.POST_INCOME_BUTTON.getButtonName())) {
            return getIncomeMessage(chatId);
        } else if (inputText.equals(ButtonsNameEnum.REPORTS_BUTTON.getButtonName())) {
            return getReportsMessage(chatId);
        } else if (inputText.equals(ButtonsNameEnum.ADD_TABLE_BUTTON.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.EXPENSE_START_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonsNameEnum.HELP_BUTTON.getButtonName())) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            return sendMessage;
        } else if (inputText.contains("категория")) {
            expense.setCategorie(inputText.substring(9));
            return new SendMessage(chatId, "Вы выбрали категорию: "
            + inputText.substring(9) +  ", Выберете магазин: \n" );
        }else if (inputText.contains("магазин")) {
            expense.setUserId(message.getChatId());
            expense.setDate(java.time.LocalDateTime.now());
            expense.setShop_name(inputText.substring(7));
            expenseRepo.save(expense);
            return new SendMessage(chatId, "Вы выбрали магазин: "
            + inputText.substring(7) +  ", Введите сумму покупки: \n" );
        } else if (inputText.matches("\\d+\\.\\d+") || inputText.matches("\\d+")) {
            expense.setAmount(Double.valueOf(inputText));
            SendMessage sendMessage = new SendMessage(chatId, "Сумма покупки " + inputText +" Выберете валюту : \n");
            sendMessage.setReplyMarkup(inlineKeyboardMaker.getCurrencyKeyboard());
            return sendMessage;

        }else if (inputText.equals("GEL")) {
            expense.setCurrency(inputText);
            expenseRepo.save(expense);
            SendMessage sendMessage = new SendMessage(chatId, "Ваша покупка :" + expense.toString());

            return sendMessage;

        } else {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            return new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
        }
    }

    private SendMessage getStartMessage(String chatId) {

        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
//        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }


    private BotApiMethod<?> getReportsMessage(String chatId) {
        return null;
    }

    private BotApiMethod<?> getIncomeMessage(String chatId) {
        return null;
    }

    private BotApiMethod<?> getExpenseMessage(String chatId, Message message) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.EXPENSE_START_MESSAGE.getMessage());
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton yesButton = new InlineKeyboardButton();
        yesButton.setText("CREATE");
        yesButton.setCallbackData(CREATE_BUTTON);
        InlineKeyboardButton noButton = new InlineKeyboardButton();
        noButton.setText("BACK");
        noButton.setCallbackData(BACK_BUTTON);
        rowInLine.add(yesButton);
        rowInLine.add(noButton);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInLine);
        return sendMessage;
    }





//    private void prepareAndSendMessage(long chatId, String textToSend){
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(textToSend);
//        executeMessage(message);
//    }
//    private void executeMessage(SendMessage message){
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.getMessage();
//        }
//    }

/*        switch (sendMessage.getText()) {
        case "CREATE":
            Expense expense = new Expense();
            sendMessage.setText("Create record");
            expense.setDate(java.time.LocalDateTime.now());
            expense.setUserId(Long.valueOf(sendMessage.getChatId()));
            InlineKeyboardButton enterShop = new InlineKeyboardButton();
            enterShop.setText("Enter Shop NAME");
            enterShop.setCallbackData(ENTER_SHOP_BUTTON);
            break;
        case "BACK":
            sendMessage.setText("/start");
            break;
        default:
            sendMessage.setText(BotMessageEnum.HELP_MESSAGE.getMessage());
            break;
    }*/


}