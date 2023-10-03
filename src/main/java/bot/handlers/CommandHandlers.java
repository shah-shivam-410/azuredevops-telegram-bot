package bot.handlers;

import java.io.IOException;
import java.util.List;

import javax.swing.text.JTextComponent.KeyBinding;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import bot.input.KeyBoards;
import utils.UtilityResponses;

public class CommandHandlers implements EventHandler {

	UtilityResponses responses;

	public CommandHandlers() throws IOException {
		responses = new UtilityResponses();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		if (update.getMessage().getText().equals("/start")) {
			message = responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Welcome! Select release name.",
					KeyBoards.releaseNameKeyboard());
		}
		return message;
	}

}
