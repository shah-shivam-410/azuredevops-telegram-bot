package botutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ado.modal.WorkItem;
import ado.testManagement.WorkItemsDetails;

public class Bot extends TelegramLongPollingBot {

	private Properties props = new Properties();

	InlineKeyboardButton button1;
	InlineKeyboardButton button2;
	InlineKeyboardButton button3;
	InlineKeyboardMarkup keyboard1;
	InlineKeyboardMarkup keyboard2;

	KeyboardButton keyButton1;
	KeyboardButton keyButton2;
	KeyboardButton keyButton3;
	KeyboardButton keyButton4;
	ReplyKeyboardMarkup replyKeyBoard1;

	public Bot() throws IOException {
		FileInputStream fis = new FileInputStream(new File(".//src/main//resources//botconfig.properties"));
		props.load(fis);
		fis.close();

		button1 = InlineKeyboardButton.builder().text("button1").callbackData("button1").build();
		button2 = InlineKeyboardButton.builder().text("button2").callbackData("button2").build();
		button3 = InlineKeyboardButton.builder().text("button3").callbackData("button3").build();
		keyboard1 = InlineKeyboardMarkup.builder().keyboardRow(List.of(button1, button2)).build();
		keyboard2 = InlineKeyboardMarkup.builder().keyboardRow(List.of(button3)).build();

		keyButton1 = KeyboardButton.builder().text("Get workitem detais").build();
		keyButton2 = KeyboardButton.builder().text("Second choice").build();
		keyButton3 = KeyboardButton.builder().text("Third choice").build();
		keyButton4 = KeyboardButton.builder().text("Fourth choice").build();
		KeyboardRow row1 = new KeyboardRow();
		row1.add(keyButton1);
		KeyboardRow row2 = new KeyboardRow();
		row2.add(keyButton2);
		KeyboardRow row3 = new KeyboardRow();
		row3.add(keyButton3);
		KeyboardRow row4 = new KeyboardRow();
		row4.add(keyButton4);
		replyKeyBoard1 = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true)
				.keyboard(List.of(row1, row2, row3, row4)).build();

	}

	@Override
	public void onUpdateReceived(Update update) {
		
		if(update.getMessage().getReplyToMessage() != null) {
			String text = update.getMessage().getReplyToMessage().getText();
			if(text.equals("Enter workitem id")) {
				WorkItemsDetails details = new WorkItemsDetails();
				try {
					WorkItem workItemDetails = details.getWorkItemDetails(update.getMessage().getText());
					sendText(update.getMessage().getFrom().getId(), workItemDetails.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		if (update.hasCallbackQuery()) {
			try {
				buttonTapHandle(update.getCallbackQuery().getFrom().getId().toString(),
						update.getCallbackQuery().getId(), update.getCallbackQuery().getData());
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (update.getMessage().isCommand()) {
			if (update.getMessage().getText().equals("/start")) {
				sendKeyBoard(update.getMessage().getChatId().toString(), "Please select option", replyKeyBoard1);
			}
		} else {
			Long userId = update.getMessage().getFrom().getId();
			String[] msg = update.getMessage().getText().toLowerCase().replaceAll("[\\s,.?]", "").split("[:]");
			
			if(update.getMessage().getText().equals("Get workitem detais")) {
				ForceReplyKeyboard forceReplyKeyboard = ForceReplyKeyboard.builder()
						.inputFieldPlaceholder("Please enter workitem id")
						.forceReply(true)
						.build();
				SendMessage sm = SendMessage.builder()
						.chatId(update.getMessage().getChatId())
						.text("Enter workitem id").replyMarkup(forceReplyKeyboard)
						.build();
				try {
					execute(sm);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public String getBotUsername() {
		return props.getProperty("BOTNAME");
	}

	@Override
	public String getBotToken() {
		return props.getProperty("BOTTOKEN");
	}

	public void sendText(Long who, String what) {
		SendMessage sm = SendMessage.builder().chatId(who.toString())
				.parseMode("HTML").text(what).build();
		try {
			execute(sm);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendDice(Long who) {
		SendDice ss = SendDice.builder().chatId(who.toString()).build();
		try {
			execute(ss);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendMenu(String chatId, String txt, InlineKeyboardMarkup kb) {
		SendMessage sm = SendMessage.builder().chatId(chatId).parseMode("HTML").text(txt).replyMarkup(kb).build();
		try {
			execute(sm);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendKeyBoard(String chatId, String txt, ReplyKeyboardMarkup kb) {
		SendMessage sm = SendMessage.builder().chatId(chatId).parseMode("HTML").text(txt).replyMarkup(kb).build();
		try {
			execute(sm);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	public void buttonTapHandle(String chatId, String queryId, String data) throws TelegramApiException {
		if (data.equals("button1")) {
			AnswerCallbackQuery close = AnswerCallbackQuery.builder().callbackQueryId(queryId).build();
			sendMenu(chatId, "Please select choice", keyboard2);
			execute(close);
		}

	}

}
