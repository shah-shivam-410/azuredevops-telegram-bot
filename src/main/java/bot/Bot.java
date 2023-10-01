package bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import bot.handlers.CommandHandlers;
import bot.handlers.EventHandler;
import bot.handlers.GeneralMessageHandler;
import bot.handlers.ReplyToMessageHandler;
import constants.Proplist;
import utils.ConfigReader;

public class Bot extends TelegramLongPollingBot {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	private Properties props;

	public EventHandler handler;

	public Bot() throws IOException {
		props = ConfigReader.loadProps();
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if(update.hasCallbackQuery()) {
				log.debug("Callback received from {}", update.getCallbackQuery().getFrom().getFirstName());
				// Handle callback queries.
			}
			else if (update.getMessage().isCommand()) {
				log.debug("Command received from {}", update.getMessage().getFrom().getFirstName());
				handler = new CommandHandlers();
				execute(handler.handleEvent(update));
			}
			else if (update.getMessage().getReplyToMessage() != null) {
				log.debug("ReplyTo message received from {}", update.getMessage().getFrom().getFirstName());
				handler = new ReplyToMessageHandler();
				execute(handler.handleEvent(update));
			}
			else {
				log.debug("General message received from {}", update.getMessage().getFrom().getFirstName());
				handler = new GeneralMessageHandler();
				execute(handler.handleEvent(update));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getBotUsername() {
		return props.getProperty(Proplist.BOT_USERNAME);
	}

	@Override
	public String getBotToken() {
		return props.getProperty(Proplist.BOT_TOKEN);
	}

}
