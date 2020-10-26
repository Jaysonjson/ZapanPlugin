package jayson.json.zapan.commands;

import jayson.json.zapan.inventories.AreaListInventory;
import jayson.json.zapan.inventories.ItemInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AreaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp()) {
                AreaListInventory inventory = new AreaListInventory();
                inventory.createPageData(player);
                inventory.openInventory(player, 0);
            }
        }
        return false;
    }

}
