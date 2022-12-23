package com.expenses.holder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExpensesHolderApplication {

    public static void main(String[] args) {
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class, defaultWebhookInstance);
        SpringApplication.run(ExpensesHolderApplication.class, args);
    }

}
