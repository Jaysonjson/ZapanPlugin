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
        switch (s) {
            case "gmc":
                commandString = "gamemode creative";
                break;
            case "gms":
                commandString = "gamemode survival";
                break;
            case "gmsp":
                commandString = "gamemode spectator";
                break;
            case "gma":
                commandString = "gamemode adventure";
                break;
        }
        Bukkit.dispatchCommand(commandSender, commandString);
        return true;
    }
}
