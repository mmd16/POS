package command;

import customerData.Record;

public class CmdSuggestMsgSentToCustomer implements Command {
	private Record r;
	
    public void execute(String[] cmdParts) {
        r = Record.getInstance();
        r.suggestMsgToSend();
    }
}
