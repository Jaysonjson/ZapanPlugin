package jayson.json.fuchs.commands;

import jayson.json.fuchs.inventories.ItemInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        ItemInventory inventory = new ItemInventory();
        inventory.createPageData(player);
        inventory.openInventory(player, 0);
        return true;
    }
}
