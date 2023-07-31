package bot.handlers;

import java.io.IOException;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import bot.utils.UtilityResponses;

public class CommandHandlers implements EventHandler {

	UtilityResponses responses;

	public CommandHandlers() throws IOException {
		responses = new UtilityResponses();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		if (update.getMessage().getText().equals("/start")) {
			KeyboardButton keyButton1 = KeyboardButton.builder().text("Get workitem detais").build();
			KeyboardButton keyButton2 = KeyboardButton.builder().text("Second choice").build();
			KeyboardButton keyButton3 = KeyboardButton.builder().text("Third choice").build();
			KeyboardButton keyButton4 = KeyboardButton.builder().text("Fourth choice").build();
			KeyboardRow row1 = new KeyboardRow();
			row1.add(keyButton1);
			KeyboardRow row2 = new KeyboardRow();
			row2.add(keyButton2);
			KeyboardRow row3 = new KeyboardRow();
			row3.add(keyButton3);
			KeyboardRow row4 = new KeyboardRow();
			row4.add(keyButton4);
			ReplyKeyboardMarkup replyKeyBoard1 = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true)
					.keyboard(List.of(row1, row2, row3, row4)).build();
			message = responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Please select option",
					replyKeyBoard1);
		}
		return message;
	}

}
