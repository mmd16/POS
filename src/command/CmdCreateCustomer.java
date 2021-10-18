package command;

import customerData.Record;
import exception.ExInsufficientArguments;

public class CmdCreateCustomer implements Command {
	
    private Record r;

    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        if(cmdParts.length != 4)
            throw new ExInsufficientArguments();

        char sex = cmdParts[2].charAt(0);
        r = Record.getInstance();
        r.createCustomer(cmdParts[1], sex, cmdParts[3]);
        System.out.println("Done.");
    }
}
