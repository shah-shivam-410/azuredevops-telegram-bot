package bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import bot.handlers.CommandHandlers;
import bot.handlers.EventHandler;
import bot.handlers.GeneralMessageHandler;
import bot.handlers.ReplyToMessageHandler;

public class Bot extends TelegramLongPollingBot {

	private Properties props = new Properties();

	public EventHandler handler;

	public Bot() throws IOException {
		FileInputStream fis = new FileInputStream(new File(".//src/main//resources//botconfig.properties"));
		props.load(fis);
		fis.close();
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.getMessage().isCommand()) {
				handler = new CommandHandlers();
				execute(handler.handleEvent(update));
			}
			else if (update.getMessage().getReplyToMessage() != null) {
				handler = new ReplyToMessageHandler();
				execute(handler.handleEvent(update));
			}
			else {
				handler = new GeneralMessageHandler();
				execute(handler.handleEvent(update));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getBotUsername() {
		return props.getProperty("BOTNAME");
	}

	@Override
	public String getBotToken() {
		return props.getProperty("BOTTOKEN");
	}

}
