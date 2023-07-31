package bot.handlers;

import java.io.IOException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bot.utils.UtilityResponses;

public class GeneralMessageHandler implements EventHandler {
	UtilityResponses responses;

	public GeneralMessageHandler() throws IOException {
		responses = new UtilityResponses();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		
		if(update.getMessage().getText().equals("Get workitem detais")) {
			message = responses.sendReplyMarkupMessage(update.getMessage().getChatId(), "Please enter workitem id", "Please enter workitem id");
		}
		return message;
	}
	

}
