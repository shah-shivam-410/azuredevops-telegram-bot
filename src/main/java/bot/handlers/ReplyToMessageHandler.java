package bot.handlers;

import java.io.IOException;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import ado.defects.DefectOperations;
import ado.modal.WorkItem;
import ado.pipelines.PipelineOperations;
import constants.DataList;
import utils.UtilityResponses;

public class ReplyToMessageHandler implements EventHandler {
	UtilityResponses responses;
	DefectOperations defectOperations;
	PipelineOperations pipelineOperations;

	public ReplyToMessageHandler() throws IOException {
		responses = new UtilityResponses();
		defectOperations = new DefectOperations();
		pipelineOperations = new PipelineOperations();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		if (update.getMessage().getReplyToMessage() != null) {

			String text = update.getMessage().getReplyToMessage().getText();
			if (text.equals("Please enter workitem id")) {
				try {
					WorkItem workItemDetails = defectOperations.getWorkItemDetails(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId().toString(),
							workItemDetails.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (text.equals("Enter CR name")) {
				try {
					List<Integer> list = defectOperations.getListOfDefectsByCR(DataList.release, update.getMessage().getText());
					String resp = defectOperations.getWorkItemListDetails(list);
					message = responses.sendText(update.getMessage().getFrom().getId().toString(),
							resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (text.equals("Please enter pipeline id")) {
				try {
					String rundetails = pipelineOperations.runPipeline(update.getMessage().getText());
					message = responses.sendText(update.getMessage().getFrom().getId().toString(), rundetails);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return message;

	}
	
	

}
