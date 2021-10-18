package command;

import customerData.Record;
import exception.ExCustomerNotFound;
import exception.ExInsufficientArguments;

public class CmdCreateOrder implements Command {
    private Record r;

    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        if(cmdParts.length != 4)
            throw new ExInsufficientArguments();
        try {
            int days = Integer.parseInt(cmdParts[3]);
            r = Record.getInstance();
            r.createOrder(cmdParts[1], r.ConvertStrToDate(cmdParts[2]), days);
            System.out.println("Done.");
        }
        catch (ExCustomerNotFound e) {
        	System.out.println(e.getMessage());
        }
    }
}
