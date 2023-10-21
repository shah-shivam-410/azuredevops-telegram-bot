package bot;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import constants.Proplist;
import utils.ConfigReader;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws TelegramApiException, IOException {
		log.info("Starting the application");
		Properties props = ConfigReader.loadProps();
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		Bot bot = new Bot(props.getProperty(Proplist.BOT_TOKEN));
		botsApi.registerBot(bot);
		log.info("Bot registered successfully");
	}

}
