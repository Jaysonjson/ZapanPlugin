package jayson.json.fuchs.commands;

import jayson.json.fuchs.inventories.classes.ClassInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClassListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp()) {
                ClassInventory classInventoryST = new ClassInventory();
                classInventoryST.openInventory(player, 0);
            }
        }
        return false;
    }

}
