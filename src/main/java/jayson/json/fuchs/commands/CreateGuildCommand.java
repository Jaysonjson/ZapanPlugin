package jayson.json.fuchs.commands;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.zGuild;
import jayson.json.fuchs.data.zPlayer;
import jayson.json.fuchs.data.zguildobj.zGuildRank;
import jayson.json.fuchs.io.DataHandler;
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
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("create")) {
                    if(zPlayer.isInGuild()) {
                        player.sendMessage("Du bist bereits in einer Guilde!");
                        return true;
                    }
                    if(Utility.countMoneyBackpack(player.getInventory()) >= 50000) {
                        if(!Utility.guildExists(args[1])) {
                            zGuild zGuild = new zGuild();
                            zGuild.name = args[1];
                            zGuild.owner = player.getUniqueId();
                            CreateGuildUUID(zGuild);
                            zGuild.members.put(player.getUniqueId(), zGuildRank.OWNER);
                            zPlayer.setGuildUuid(zGuild.uuid);
                            DataHandler.saveGuild(zGuild);
                            DataHandler.savePlayer(zPlayer);
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
                if(zPlayer.getGuildUuid() != null) {
                    zGuild zGuild = DataHandler.loadGuild(zPlayer.getGuildUuid());
                } else {
                    player.sendMessage("Du bist in keiner Guilde!");
                }
                return true;
            }
        }
        return false;
    }

    private void CreateGuildUUID(zGuild zGuild) {
        UUID uuid = UUID.randomUUID();
        if(!Utility.guildExists(uuid)) {
            zGuild.uuid = uuid;
        } else {
            CreateGuildUUID(zGuild);
        }
    }
}