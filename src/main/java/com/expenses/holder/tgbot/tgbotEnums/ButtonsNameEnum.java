package com.expenses.holder.tgbot.tgbotEnums;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonsNameEnum {
    POST_EXPENSE_BUTTON("EXPENSE"),
    POST_INCOME_BUTTON("INCOME"),
    REPORTS_BUTTON("REPORTS"),
    ADD_TABLE_BUTTON("ADD TABLE"),
    HELP_BUTTON("HELP");

    private final String buttonName;

    ButtonsNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}