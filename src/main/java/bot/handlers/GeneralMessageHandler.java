package bot.handlers;

import java.io.IOException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
		if(update.getMessage().getText().equals("Active work items asssigned to person")) {
			message = responses.sendReplyMarkupMessage(update.getMessage().getChatId(), "Please enter email id", "Please enter email id");
		}
		if(update.getMessage().getText().equals("Run pipeline")) {
			message = responses.sendReplyMarkupMessage(update.getMessage().getChatId(), "Please enter pipeline id", "Please enter pipeline id");
		}
		
		return message;
	}
	

}
