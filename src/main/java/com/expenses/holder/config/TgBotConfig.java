package com.expenses.holder.config;

import com.expenses.holder.tgbot.TelegramBot;
import com.expenses.holder.tgbot.handlers.CallbackQueryHandler;
import com.expenses.holder.tgbot.handlers.MessageHandler;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;


@Configuration
@Setter
@Getter
@NoArgsConstructor
public class TgBotConfig {

    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.wh}")
    private String webhookPath;
}

