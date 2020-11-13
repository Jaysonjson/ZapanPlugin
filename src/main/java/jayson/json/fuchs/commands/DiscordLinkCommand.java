package jayson.json.fuchs.commands;

import jayson.json.fuchs.data.discord.data.zDiscord;
import jayson.json.fuchs.data.io.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DiscordLinkCommand implements CommandExecutor {

    int codeTries = 0;
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
        if(codeTries > 74) {
            code += "_" + new Random().nextInt(10000);
        }
        if(discord.linkerUUIDS.containsKey(code)) {
            codeTries++;
            return generateCode(discord);
        }
        return code;
    }
}
