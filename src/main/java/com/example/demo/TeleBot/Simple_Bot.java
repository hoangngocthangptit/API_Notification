package com.example.demo.TeleBot;

import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Simple_Bot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
			message.setChatId(update.getMessage().getChatId().toString());
			message.setText("");

			try {
				execute(message); // Call method to send the message
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			System.out.println(update.getMessage().getText());
		}

	}
	@Override
	public String getBotUsername() {
		// TODO
		return "ThangNgaoBot";
	}
	@Override
	public String getBotToken() {
		// TODO
		return "5329448013:AAEMQOqgsqUs_xMPDPy_hgTBPP3773TjQr4";
	}

}
