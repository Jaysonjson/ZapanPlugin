package jayson.json.fuchs.commands;

import org.bukkit.command.CommandExecutor;

public class SpigotCommand {

	String command; 
	CommandExecutor commandExecutor;
	public SpigotCommand(String command, CommandExecutor commandExecutor) {
		this.command = command;
		this.commandExecutor = commandExecutor;
	}
	
	public String getCommand() {
		return command;
	}
	
	public CommandExecutor getCommandExecutor() {
		return commandExecutor;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public void setCommandExecutor(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}
}
