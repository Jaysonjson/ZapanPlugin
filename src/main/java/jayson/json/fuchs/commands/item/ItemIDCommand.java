package jayson.json.fuchs.commands.item;

import jayson.json.fuchs.objects.gas.interfaces.IzGasRegistry;
import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;
import jayson.json.fuchs.objects.zRegistry;
import jayson.json.fuchs.objects.items.interfaces.IzItemRegistry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        StringBuilder content = new StringBuilder();
        int i = 0;
        content.append(ChatColor.BOLD + "Items: \n" + ChatColor.RESET);
        for (IzItemRegistry item : zRegistry.items) {
        	i++;
        	String suffix = ", ";
        	if(i >= zRegistry.items.size()) {
        		suffix = "\n";
        	}
            content.append(item.getAbstractItem().getId()).append(suffix);
        }

        i = 0;
        content.append(ChatColor.BOLD + "Flüssigkeiten: \n" + ChatColor.RESET);
        for (IzLiquidRegistry liquid : zRegistry.liquids) {
            i++;
            String suffix = ", ";
            if(i >= zRegistry.liquids.size()) {
                suffix = "\n";
            }
            content.append(liquid.getLiquid().getId()).append(suffix);
        }

        i = 0;
        content.append(ChatColor.BOLD + "Gase: \n" + ChatColor.RESET);
        for (IzGasRegistry gas : zRegistry.gasses) {
            i++;
            String suffix = ", ";
            if(i >= zRegistry.gasses.size()) {
                suffix = "";
            }
            content.append(gas.getGas().getId()).append(suffix);
        }
        
        commandSender.sendMessage(content.toString());
        return true;
    }
}
