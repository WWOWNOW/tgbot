package com.expenses.holder.tgbot.keyboards;

import com.expenses.holder.tgbot.tgbotEnums.ButtonsNameEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Основная клавиатура, расположенная под строкой ввода текста в Telegram
 */


@Component
@NoArgsConstructor
public class ReplyKeyboardMaker {
    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonsNameEnum.POST_INCOME_BUTTON.getButtonName()));
        row1.add(new KeyboardButton(ButtonsNameEnum.POST_EXPENSE_BUTTON.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonsNameEnum.ADD_TABLE_BUTTON.getButtonName()));
        row2.add(new KeyboardButton(ButtonsNameEnum.REPORTS_BUTTON.getButtonName()));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(ButtonsNameEnum.HELP_BUTTON.getButtonName()));


        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}