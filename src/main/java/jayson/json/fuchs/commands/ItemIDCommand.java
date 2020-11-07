package jayson.json.fuchs.commands;

import jayson.json.fuchs.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.items.lists.ItemRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        StringBuilder content = new StringBuilder();
        int i = 0;
        for (IzItemRegistry item : ItemRegistry.items) {
        	i++;
        	String suffix = ", ";
        	if(i >= ItemRegistry.items.size()) {
        		suffix = "";
        	}
            content.append(item.getAbstractItem().getId()).append(suffix);
        }
        commandSender.sendMessage(content.toString());
        return true;
    }
}
