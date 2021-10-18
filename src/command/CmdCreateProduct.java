package command;

import customerData.Record;
import exception.ExInsufficientArguments;

public class CmdCreateProduct implements Command {
    private Record r;

    public void execute(String[] cmdParts) throws ExInsufficientArguments {
    	if (cmdParts[3].equals("Empty")) {
            if (cmdParts.length != 6)
                throw new ExInsufficientArguments();
    	}
    	else {
            if (cmdParts.length != 4)
                throw new ExInsufficientArguments();
    	}
        double fee = Double.parseDouble(cmdParts[2]);
        int totalImport = Integer.parseInt(cmdParts[5]);
        r = Record.getInstance();
        //if (p.inventoryIsEmpty(cmdParts[3])) 		should set method in r class rather than Product class, otherwise null occurs. Bug.
        if (r.inventoryIsEmpty(cmdParts[3])) 
        	r.createOnlineProduct(cmdParts[1], fee, cmdParts[3], r.ConvertStrToDate(cmdParts[4]), totalImport);     	
        else
        	r.createPhysicalProduct(cmdParts[1], fee, cmdParts[3]);
        System.out.println("Done.");
    }
}
