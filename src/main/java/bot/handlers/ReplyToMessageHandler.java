package bot.handlers;

import java.io.IOException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import ado.modal.WorkItem;
import ado.testManagement.WorkItemsDetails;
import bot.utils.UtilityResponses;

public class ReplyToMessageHandler implements EventHandler {
	UtilityResponses responses;

	public ReplyToMessageHandler() throws IOException {
		responses = new UtilityResponses();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		if (update.getMessage().getReplyToMessage() != null) {

			String text = update.getMessage().getReplyToMessage().getText();
			if (text.equals("Please enter workitem id")) {
				WorkItemsDetails details = new WorkItemsDetails();
				try {
					WorkItem workItemDetails = details.getWorkItemDetails(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId(), workItemDetails.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return message;

	}

}
