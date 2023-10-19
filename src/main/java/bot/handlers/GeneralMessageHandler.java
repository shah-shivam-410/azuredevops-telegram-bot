package bot.handlers;

import java.io.IOException;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import ado.defects.DefectOperations;
import ado.pipelines.PipelineOperations;
import bot.input.KeyBoards;
import constants.DataList;
import utils.UtilityResponses;

public class GeneralMessageHandler implements EventHandler {
	UtilityResponses responses;
	DefectOperations defectOperations;
	
	public GeneralMessageHandler() throws IOException {
		responses = new UtilityResponses();
		defectOperations = new DefectOperations();
	}

	@Override
	public SendMessage handleEvent(Update update) {
		SendMessage message = null;
		
		if(DataList.RELEASE_LIST.contains(update.getMessage().getText())) {
			DataList.release = update.getMessage().getText();
			return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Please select domain.", KeyBoards.domainListKeyboard());
		}
		
		if(DataList.DOMAIN_LIST.contains(update.getMessage().getText())) {
			DataList.domain = update.getMessage().getText();
			if(DataList.domain.equalsIgnoreCase("Defects"))
				return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Please select operation.", KeyBoards.defectOperationListKeyBoard());
			if(DataList.domain.equalsIgnoreCase("Test-cases"))
				return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Please select operation.", KeyBoards.testcaseOperationListKeyBoard());
		}
		
		if(DataList.TEAM_LIST.contains(update.getMessage().getText())) {
			DataList.team = update.getMessage().getText();
			return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Click on Execute.", KeyBoards.executKeyBoard());
		}
		
		if(DataList.STATUS_LIST.contains(update.getMessage().getText())) {
			DataList.status = update.getMessage().getText();
			return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Click on Execute.", KeyBoards.executKeyBoard());
		}
		
		if(update.getMessage().getText().toLowerCase().contains("by status")) {
			return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Select the status.", KeyBoards.defectStatusKeyBoard());
		}
		
		if(update.getMessage().getText().toLowerCase().contains("by assigned team")) {
			return responses.sendKeyBoard(update.getMessage().getChatId().toString(), "Select the team name.", KeyBoards.teamNameKeyBoard());
		}
		
		if(update.getMessage().getText().toLowerCase().contains("by id")) {
			return responses.sendReplyMarkupMessage(update.getMessage().getChatId().toString(), "Please enter workitem id", "Please enter workitem id");
		}
		
		if(update.getMessage().getText().toLowerCase().contains("by cr")) {
			return responses.sendReplyMarkupMessage(update.getMessage().getChatId().toString(), "Enter CR name", "Enter CR name");
		}
		
		if(update.getMessage().getText().contains("Execute")) {
			try {
			if(DataList.domain.equals("Defects")) {
				if(DataList.team != null) {
					List<Integer> list = defectOperations.getListOfDefectsByTeam(DataList.release, DataList.team);
					String respmsg = defectOperations.getWorkItemListDetails(list);
					return responses.sendText(update.getMessage().getChatId().toString(), respmsg);
				}
				else if(DataList.status != null) {
					List<Integer> list = defectOperations.getListOfDefectsByStatus(DataList.release, DataList.status);
					String respmsg = defectOperations.getWorkItemListDetails(list);
					return responses.sendText(update.getMessage().getChatId().toString(), respmsg);
				}
				else if(DataList.crname != null) {
					List<Integer> list = defectOperations.getListOfDefectsByCR(DataList.release, DataList.crname);
					String respmsg = defectOperations.getWorkItemListDetails(list);
					return responses.sendText(update.getMessage().getChatId().toString(), respmsg);
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				return responses.sendText(update.getMessage().getChatId().toString(), "Error occured: " + e.getLocalizedMessage());
			} finally {
				DataList.nullifyData();
			}
		
		}
		
		if(update.getMessage().getText().equals("Run pipeline")) {
			message = responses.sendReplyMarkupMessage(update.getMessage().getChatId().toString(), "Please enter pipeline id", "Please enter pipeline id");
		}
		
		if(update.getMessage().getText().equals("List all pipelines")) {
			PipelineOperations details;
			try {
				details = new PipelineOperations();
				String alljobslist = details.getListofJobs();
				message = responses.sendText(update.getMessage().getFrom().getId().toString(), alljobslist);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return message;
		
	}
	

}
