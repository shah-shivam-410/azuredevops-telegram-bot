package bot;

import java.io.IOException;
import java.util.Properties;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import constants.Proplist;
import utils.ConfigReader;

public class Main {
	
	public static void main(String[] args) throws TelegramApiException, IOException {
		
		Properties props = ConfigReader.loadProps();
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		Bot bot = new Bot(props.getProperty(Proplist.BOT_TOKEN));
		botsApi.registerBot(bot);
		
	}

}
