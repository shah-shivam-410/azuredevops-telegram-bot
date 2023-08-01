package bot.handlers;

import java.io.IOException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import ado.modal.WorkItem;
import ado.testManagement.PipelineDetails;
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
				WorkItemsDetails details;
				try {
					details = new WorkItemsDetails();
					WorkItem workItemDetails = details.getWorkItemDetails(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId(), workItemDetails.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (text.equals("Please enter email id")) {
				WorkItemsDetails details;
				try {
					details = new WorkItemsDetails();
					String itemsAsignedTo = details.getActiveWorkItemsAsignedTo(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId(), itemsAsignedTo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (text.equals("Please enter pipeline id")) {
				PipelineDetails details;
				try {
					details = new PipelineDetails();
					String rundetails = details.runPipeline(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId(), rundetails);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return message;

	}

}
