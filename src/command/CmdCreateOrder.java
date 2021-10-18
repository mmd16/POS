package command;

import customerData.Record;
import exception.ExCustomerNotFound;
import exception.ExInsufficientArguments;
import exception.ExProductNotFound;

public class CmdCreateOrder implements Command {
    private Record r;

    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        if(cmdParts.length != 5)
            throw new ExInsufficientArguments();
        try {
            int days = Integer.parseInt(cmdParts[4]);
            r = Record.getInstance();
            r.createOrder(cmdParts[1], cmdParts[2], r.ConvertStrToDate(cmdParts[3]), days);
            System.out.println("Done.");
        }
        catch (ExCustomerNotFound e) {
        	System.out.println(e.getMessage());
        }
        catch (ExProductNotFound e) {
        	System.out.println(e.getMessage());
        }
    }
}
