package command;

public interface Command {
	public void execute(String[] cmdParts) throws Exception;
}