package jayson.json.zapan.commands;

import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.ItemRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        StringBuilder content = new StringBuilder();
        for (IzItemRegistry item : ItemRegistry.items) {
            content.append(item.getAbstractItem().getId()).append(", ");
        }
        commandSender.sendMessage(content.toString());
        return false;
    }
}
