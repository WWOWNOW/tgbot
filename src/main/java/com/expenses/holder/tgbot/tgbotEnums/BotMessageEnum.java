//ответы на команды с клавиатуры
//            "В мои словари уже добавлены все слова из программ \"Школа России\" и \"Начальная школа XXI века\", " +
//            "но если в списке Вашего ребёнка есть другие слова, присылайте их моему создателю @taksebe\n\n" +
//            "Обратите внимание, что некоторые слова попадают в словари сразу нескольких классов - это следствие " +
//            "использования списков из разных программ и дополнений от пользователей. Это не страшно, " +
//            "ведь повторение - мать учения\n\n" +
//            "Удачи!\n\n" +
//            "Воспользуйтесь клавиатурой, чтобы начать работу\uD83D\uDC47,
package com.expenses.holder.tgbot.tgbotEnums;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {
    HELP_MESSAGE("\uD83D\uDC4B Hi i`m Expense Holder Bot\n\n" +
            "❗ *What i can do*\n" +
            "✅ Collect an INCOME records\n" +
            "✅ Collect an EXPENSE records\n" +
            "✅ Collect an INCOME records\n" +
            "✅ Collect an INCOME records\n\n" +
            "Воспользуйтесь клавиатурой, чтобы начать работу\uD83D\uDC47"),

    NON_COMMAND_MESSAGE("Use command /start"),

    //EXPENSE
    EXPENSE_START_MESSAGE("НА ЭТОЙ СТРАНИЦЕ ВЫ МОЖЕТЕ ДОБАВИТЬ СОВЕРШЕННУЮ ТРАТУ");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}