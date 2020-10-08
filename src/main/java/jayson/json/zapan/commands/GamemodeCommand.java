package jayson.json.zapan.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String commandString = "";
        if(s.equalsIgnoreCase("gmc")) {
            commandString = "gamemode creative";
        }
        if(s.equalsIgnoreCase("gms")) {
            commandString = "gamemode survival";
        }
        if(s.equalsIgnoreCase("gmsp")) {
            commandString = "gamemode spectator";
        }
        if(s.equalsIgnoreCase("gma")) {
            commandString = "gamemode adventure";
        }
        Bukkit.dispatchCommand(commandSender, commandString);
        return true;
    }
}
