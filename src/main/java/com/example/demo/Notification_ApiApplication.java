package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.demo.TeleBot.Simple_Bot;

@SpringBootApplication
public class Notification_ApiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(Notification_ApiApplication.class, args);
		TelegramBotsApi Bot;
		try {
			Bot = new TelegramBotsApi(DefaultBotSession.class);
			Bot.registerBot(new Simple_Bot());
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
