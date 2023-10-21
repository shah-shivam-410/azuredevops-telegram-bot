package bot;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bot.auth.TempAuthSolution;
import bot.auth.User;
import bot.handlers.CommandHandlers;
import bot.handlers.EventHandler;
import bot.handlers.GeneralMessageHandler;
import bot.handlers.ReplyToMessageHandler;
import constants.Proplist;
import exceptions.UnAuthorizedAccessException;
import utils.ConfigReader;
import utils.UtilityResponses;

public class Bot extends TelegramLongPollingBot {

	private static final Logger log = LoggerFactory.getLogger(Bot.class);

	private Properties props;
	private Map<Long, User> usermap;

	public EventHandler handler;

	public Bot(String token) throws IOException {
		super(token);
		props = ConfigReader.loadProps();
		usermap = TempAuthSolution.getUserMap();
	}

	@Override
	public void onUpdateReceived(Update update) {

		try {
			validateUser(update);
			if (update.hasCallbackQuery()) {
				// handle callback query
			} else if (update.getMessage().isCommand()) {
				handler = new CommandHandlers();
				execute(handler.handleEvent(update));
			} else if (update.getMessage().getReplyToMessage() != null) {
				handler = new ReplyToMessageHandler();
				execute(handler.handleEvent(update));
			} else {
				handler = new GeneralMessageHandler();
				execute(handler.handleEvent(update));
			}

		} catch (Exception e) {
			e.printStackTrace();

			try {
				SendMessage text = new UtilityResponses().sendText(update.getMessage().getChatId().toString(),
						e.getMessage());
				execute(text);
			} catch (IOException | TelegramApiException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public String getBotUsername() {
		return props.getProperty(Proplist.BOT_USERNAME);
	}

	@Deprecated(forRemoval = true)
	@Override
	public String getBotToken() {
		return props.getProperty(Proplist.BOT_TOKEN);
	}

	public void validateUser(Update update) throws UnAuthorizedAccessException {
		if (update.hasCallbackQuery()) {
			if (!usermap.containsKey(update.getCallbackQuery().getFrom().getId())) {
				log.warn("Unauthorized access from: [id: {}, firstname: {}, lastname: {}]",
						update.getCallbackQuery().getFrom().getId(), 
						update.getCallbackQuery().getFrom().getFirstName(),
						update.getCallbackQuery().getFrom().getLastName()
						);
				throw new UnAuthorizedAccessException(
						update.getCallbackQuery().getFrom().getId(),
						update.getMessage().getFrom().getFirstName(), 
						update.getMessage().getFrom().getLastName()
						);
			}
		} else {
			if (!usermap.containsKey(update.getMessage().getFrom().getId())) {
				log.warn("Unauthorized access from: [id: {}, firstname: {}, lastname: {}]",
						update.getMessage().getFrom().getId(), 
						update.getMessage().getFrom().getFirstName(),
						update.getMessage().getFrom().getLastName()
						);
				throw new UnAuthorizedAccessException(
						update.getMessage().getFrom().getId(),
						update.getMessage().getFrom().getFirstName(), 
						update.getMessage().getFrom().getLastName()
						);
			}
		}

	}

}
