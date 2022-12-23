package com.expenses.holder.config;

import com.expenses.holder.tgbot.TelegramBot;
import com.expenses.holder.tgbot.handlers.CallbackQueryHandler;
import com.expenses.holder.tgbot.handlers.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
@Configuration
@AllArgsConstructor
public class AppConfig {
    private  TgBotConfig tgBotConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(tgBotConfig.getWebhookPath()).build();
    }

    @Bean
    public TelegramBot springTgBot(SetWebhook setWebhook,
                                        MessageHandler messageHandler,
                                        CallbackQueryHandler callbackQueryHandler) {
        TelegramBot bot = new TelegramBot(setWebhook, messageHandler, callbackQueryHandler);

        bot.setWebHookPath(tgBotConfig.getWebhookPath());
        bot.setBotUserName(tgBotConfig.getBotName());
        bot.setBotToken(tgBotConfig.getBotToken());

        return bot;
    }
}
