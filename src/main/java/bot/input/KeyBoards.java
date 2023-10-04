package bot.input;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyBoards {
	
	private KeyBoards() {}

	public static ReplyKeyboardMarkup releaseNameKb;
	public static ReplyKeyboardMarkup domainListKb;
	public static ReplyKeyboardMarkup defectOperationListKb;
	public static ReplyKeyboardMarkup testcaseOperationListKb;
	public static ReplyKeyboardMarkup teamNameKb;
	public static ReplyKeyboardMarkup defectStatusKb;
	public static ReplyKeyboardMarkup executKb;

	public static ReplyKeyboardMarkup releaseNameKeyboard() {
		if (releaseNameKb == null) {
			KeyboardRow row1 = new KeyboardRow();
			row1.add(KeyboardButton.builder().text("23.9").build());
			KeyboardRow row2 = new KeyboardRow();
			row2.add(KeyboardButton.builder().text("23.10A").build());
			releaseNameKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true).resizeKeyboard(true)
					.keyboard(List.of(row1, row2)).build();
		}
		return releaseNameKb;
	}

	public static ReplyKeyboardMarkup domainListKeyboard() {
		if (domainListKb == null) {
			KeyboardRow row1 = new KeyboardRow();
			row1.add(KeyboardButton.builder().text("Defects").build());
			KeyboardRow row2 = new KeyboardRow();
			row2.add(KeyboardButton.builder().text("Test-cases").build());
			domainListKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true).resizeKeyboard(true)
					.keyboard(List.of(row1, row2)).build();
		}
		return domainListKb;
	}

	public static ReplyKeyboardMarkup defectOperationListKeyBoard() {
		if (defectOperationListKb == null) {
			KeyboardRow row1 = new KeyboardRow();
			row1.add(KeyboardButton.builder().text("Defect details by ID").build());
			KeyboardRow row2 = new KeyboardRow();
			row2.add(KeyboardButton.builder().text("Defect details by status").build());
			KeyboardRow row3 = new KeyboardRow();
			row3.add(KeyboardButton.builder().text("Defects details by assigned team").build());
			KeyboardRow row4 = new KeyboardRow();
			row4.add(KeyboardButton.builder().text("Defects details by CR").build());
			defectOperationListKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true)
					.resizeKeyboard(true).keyboard(List.of(row1, row2, row3, row4)).build();
		}
		return defectOperationListKb;
	}

	public static ReplyKeyboardMarkup testcaseOperationListKeyBoard() {
		if (testcaseOperationListKb == null) {
			KeyboardRow row1 = new KeyboardRow();
			row1.add(KeyboardButton.builder().text("Testcase details by ID").build());
			KeyboardRow row2 = new KeyboardRow();
			row2.add(KeyboardButton.builder().text("Testcase details by status").build());
			KeyboardRow row3 = new KeyboardRow();
			row3.add(KeyboardButton.builder().text("Testcase details by CR").build());
			testcaseOperationListKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true)
					.resizeKeyboard(true).keyboard(List.of(row1, row2, row3)).build();
		}
		return testcaseOperationListKb;
	}

	public static ReplyKeyboardMarkup teamNameKeyBoard() {
		if (teamNameKb == null) {
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
			teamNameKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true).resizeKeyboard(true)
					.keyboard(List.of(row1, row2, row3, row4, row5, row6)).build();
		}
		return teamNameKb;
	}

	public static ReplyKeyboardMarkup defectStatusKeyBoard() {
		if (defectStatusKb == null) {
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
			defectStatusKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true).resizeKeyboard(true)
					.keyboard(List.of(row1, row2, row3, row4, row5)).build();
		}
		return defectStatusKb;
	}

	public static ReplyKeyboardMarkup executKeyBoard() {
		if (executKb == null) {
			KeyboardRow row1 = new KeyboardRow();
			row1.add(KeyboardButton.builder().text("Execute").build());
			executKb = ReplyKeyboardMarkup.builder().oneTimeKeyboard(true).selective(true).resizeKeyboard(true)
					.keyboard(List.of(row1)).build();
		}
		return executKb;
	}

}
