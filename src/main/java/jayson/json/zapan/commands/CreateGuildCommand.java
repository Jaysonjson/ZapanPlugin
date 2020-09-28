package jayson.json.zapan.commands;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zguildobj.zGuildRank;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateGuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            zPlayer zPlayer = DataHandler.LoadPlayer(player.getUniqueId());
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("create")) {
                    if(zPlayer.GetGuildUuid() != null) {
                        player.sendMessage("Du bist bereits in einer Guilde!");
                        return true;
                    }
                    if(Utility.CountMoney(player) >= 50000) {
                        if(!Utility.GuildExists(args[1])) {
                            zGuild zGuild = new zGuild();
                            zGuild.name = args[1];
                            zGuild.owner = player.getUniqueId();
                            zGuild.uuid = UUID.randomUUID();
                            zGuild.members.put(player.getUniqueId(), zGuildRank.OWNER);
                            zPlayer.SetGuildUuid(zGuild.uuid);
                            DataHandler.SaveGuild(zGuild);
                            DataHandler.SavePlayer(zPlayer);
                            player.sendMessage("Guilde " + zGuild.name + " (" + zGuild.uuid + ") erstellt!");
                        } else {
                            player.sendMessage("Eine Guilde mit dem Namen \"" + args[1] + "\" existiert bereits!");
                            return true;
                        }
                    } else {
                        player.sendMessage("Du hast nicht genügend Geld!");
                    }
                    return true;
                }
            } else {
                if(zPlayer.GetGuildUuid() != null) {
                    zGuild zGuild = DataHandler.LoadGuild(zPlayer.GetGuildUuid());
                } else {
                    player.sendMessage("Du bist in keiner Guilde!");
                }
                return true;
            }
        }
        return false;
    }
}
