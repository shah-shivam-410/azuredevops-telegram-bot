package bot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventHandler {

	SendMessage handleEvent(Update update);
	
}
