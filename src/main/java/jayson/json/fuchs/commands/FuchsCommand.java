package jayson.json.fuchs.commands;

import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import jayson.json.fuchs.Fuchs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FuchsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("Fuchs Plugin Version: " + Fuchs.version + " - Jayson.json");
        commandSender.sendMessage("ProtocolLib Version: 4.5.1 [" + ProtocolLibrary.MAXIMUM_MINECRAFT_VERSION + "] - dmulloy2");
        return true;
    }
}
