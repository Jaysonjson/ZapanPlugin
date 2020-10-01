package jayson.json.zapan.commands;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetHealthCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission("zapan.sethealth") || commandSender.isOp()) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                int health = 0;
                if (args.length == 1) {
                    if (StringUtils.isNumeric(args[0])) {
                        health = Integer.parseInt(args[0]);
                    } else {
                        commandSender.sendMessage(args[0] + " ist keine Zahl!");
                    }
                } else if (args.length == 2) {
                    player = Bukkit.getPlayer(args[0]);
                    if (StringUtils.isNumeric(args[1])) {
                        health = Integer.parseInt(args[1]);
                    } else {
                        commandSender.sendMessage(args[1] + " ist keine Zahl!");
                    }
                }
                UUID uuid = player.getUniqueId();
                zPlayer zPlayer = DataHandler.loadPlayer(uuid);
                zPlayer.getHealth().health = health;
                DataHandler.savePlayer(zPlayer);
                Utility.refreshHearts(player, zPlayer);
                commandSender.sendMessage("Das Leben von " + player.getDisplayName() + " wurde auf " + zPlayer.getHealth().health + " geändert!");
                return true;
            }
            return false;
        } else {
            commandSender.sendMessage("Du hast dafür keine Rechte!");
            return true;
        }
    }

}
