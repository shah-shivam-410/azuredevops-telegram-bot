package bot.input;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyBoards {

	public static ReplyKeyboardMarkup releaseNameKeyboard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("23.9").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("23.10A").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false).keyboard(List.of(row1, row2))
				.build();
	}

	public static ReplyKeyboardMarkup domainListKeyboard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Defect").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("Test-cases").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false).keyboard(List.of(row1, row2))
				.build();
	}

	public static ReplyKeyboardMarkup defectOperationListKeyBoard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Defect details by ID").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("Defect details by status").build());
		KeyboardRow row3 = new KeyboardRow();
		row3.add(KeyboardButton.builder().text("Defects details by assigned team").build());
		KeyboardRow row4 = new KeyboardRow();
		row4.add(KeyboardButton.builder().text("Defects details by CR").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false)
				.keyboard(List.of(row1, row2, row3, row4)).build();
	}

	public static ReplyKeyboardMarkup testcaseOperationListKeyBoard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Testcase details by ID").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("Testcase details by status").build());
		KeyboardRow row3 = new KeyboardRow();
		row3.add(KeyboardButton.builder().text("Testcase details by CR").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false).keyboard(List.of(row1, row2, row3))
				.build();
	}

	public static ReplyKeyboardMarkup teamNameKeyBoard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Siebel CRM Dev").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("Siebel CRM TA").build());
		KeyboardRow row3 = new KeyboardRow();
		row3.add(KeyboardButton.builder().text("OSM Dev").build());
		KeyboardRow row4 = new KeyboardRow();
		row4.add(KeyboardButton.builder().text("OSM TA").build());
		KeyboardRow row5 = new KeyboardRow();
		row5.add(KeyboardButton.builder().text("UIM Dev").build());
		KeyboardRow row6 = new KeyboardRow();
		row6.add(KeyboardButton.builder().text("UIM TA").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false)
				.keyboard(List.of(row1, row2, row3, row4, row5, row6)).build();
	}

	public static ReplyKeyboardMarkup defectStatusKeyBoard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Assigned").build());
		KeyboardRow row2 = new KeyboardRow();
		row2.add(KeyboardButton.builder().text("Rejected").build());
		KeyboardRow row3 = new KeyboardRow();
		row3.add(KeyboardButton.builder().text("Rejected Incomplete").build());
		KeyboardRow row4 = new KeyboardRow();
		row4.add(KeyboardButton.builder().text("RTD").build());
		KeyboardRow row5 = new KeyboardRow();
		row5.add(KeyboardButton.builder().text("RFR").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false)
				.keyboard(List.of(row1, row2, row3, row4, row5)).build();
	}

	public static ReplyKeyboardMarkup executKeyBoard() {
		KeyboardRow row1 = new KeyboardRow();
		row1.add(KeyboardButton.builder().text("Execute").build());
		return ReplyKeyboardMarkup.builder().oneTimeKeyboard(false).selective(false).keyboard(List.of(row1)).build();
	}

}
