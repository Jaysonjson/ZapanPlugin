package jayson.json.fuchs.commands;

import jayson.json.fuchs.objects.zRegistry;
import jayson.json.fuchs.objects.items.interfaces.IzItemRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        StringBuilder content = new StringBuilder();
        int i = 0;
        for (IzItemRegistry item : zRegistry.items) {
        	i++;
        	String suffix = ", ";
        	if(i >= zRegistry.items.size()) {
        		suffix = "";
        	}
            content.append(item.getAbstractItem().getId()).append(suffix);
        }
        commandSender.sendMessage(content.toString());
        return true;
    }
}
