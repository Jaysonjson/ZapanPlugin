package jayson.json.fuchs.commands;

import com.comphenix.protocol.ProtocolLibrary;
import jayson.json.fuchs.Fuchs;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FuchsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("Fuchs Plugin Version: " + ChatColor.AQUA + "" + Fuchs.version + ChatColor.RESET + " [" + ChatColor.GREEN + "1.16.3"+ ChatColor.RESET +"] - " + ChatColor.LIGHT_PURPLE + "Jayson.json");
        commandSender.sendMessage("ProtocolLib Version: " + ChatColor.AQUA + "4.5.1" + ChatColor.RESET + " [" + ChatColor.RED + "" + ProtocolLibrary.MAXIMUM_MINECRAFT_VERSION + ChatColor.RESET + "] - " + ChatColor.LIGHT_PURPLE + "dmulloy2");
        return true;
    }
}
