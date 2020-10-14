package jayson.json.zapan.commands;

import jayson.json.zapan.data.zDiscord;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DiscordLinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(commandSender instanceof Player) {
            if(s.equalsIgnoreCase("discordlink")) {
                zDiscord discord = DataHandler.loadDiscord();
                String code = generateCode(discord);
                discord.linkerUUIDS.put(code, ((Player) commandSender).getUniqueId());
                commandSender.sendMessage("Dein Discord-Code: " + code + "!");
                DataHandler.saveDiscord(discord);
                return true;
            }
        }
        return false;
    }

    private String generateCode(zDiscord discord) {
        String code = new Random().nextInt(10000) + "";
        if(discord.linkerUUIDS.containsKey(code)) {
            return generateCode(discord);
        }
        return code;
    }
}
