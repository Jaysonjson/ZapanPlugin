package jayson.json.zapan.commands;

import jayson.json.zapan.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCustomItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender.isOp() && commandSender instanceof Player) {
            if(args.length >= 1) {
                if(Utility.itemIDExists(args[0])) {
                    Utility.spawnCustomItem(Utility.getItemByID(args[0]), ((Player) commandSender).getWorld(), ((Player) commandSender).getLocation());
                } else {
                    commandSender.sendMessage("Item exisitert nicht!");
                    return true;
                }
            } else {
                commandSender.sendMessage("Nicht genügend Argumente!");
                return false;
            }
        }
        return false;
    }

}
