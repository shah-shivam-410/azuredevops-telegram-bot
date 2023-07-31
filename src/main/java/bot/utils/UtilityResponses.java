package bot.utils;

import java.io.IOException;
import java.util.Properties;

import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class UtilityResponses {

	Properties props = new Properties();
	
	public UtilityResponses() throws IOException {
		props = ConfigReader.loadProps();
	}

	public SendMessage sendText(Long who, String what) {
		SendMessage sm = SendMessage.builder().chatId(who.toString())
				.parseMode("HTML").text(what).build();
		return sm;
	}

	public SendDice sendDice(Long who) {
		SendDice ss = SendDice.builder().chatId(who.toString()).build();
		return ss;
	}

	public SendMessage sendMenu(String chatId, String txt, InlineKeyboardMarkup kb) {
		SendMessage sm = SendMessage.builder().chatId(chatId).parseMode("HTML").text(txt).replyMarkup(kb).build();
		return sm;
	}

	public SendMessage sendKeyBoard(String chatId, String txt, ReplyKeyboardMarkup kb) {
		SendMessage sm = SendMessage.builder().chatId(chatId).parseMode("HTML").text(txt).replyMarkup(kb).build();
		return sm;
	}

	public SendMessage sendReplyMarkupMessage(Long who, String msgText, String replyText) {
		ForceReplyKeyboard forceReplyKeyboard = ForceReplyKeyboard.builder()
				.inputFieldPlaceholder(replyText)
				.forceReply(true)
				.build();
		SendMessage sm = SendMessage.builder()
				.chatId(who)
				.text(msgText).replyMarkup(forceReplyKeyboard)
				.build();
		return sm;
	}
	
}
