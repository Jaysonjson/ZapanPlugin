package jayson.json.fuchs.commands.guild;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.guild.data.zGuild;
import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.guild.obj.zGuildRank;
import jayson.json.fuchs.inventories.guild.GuildInventory;
import jayson.json.fuchs.data.io.DataHandler;
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
                            zGuild.setName(args[1]);
                            zGuild.setOwner(player.getUniqueId());
                            CreateGuildUUID(zGuild);
                            zGuild.getMembers().put(player.getUniqueId(), zGuildRank.OWNER);
                            zPlayer.setGuildUuid(zGuild.getUUID());
                            DataHandler.saveGuild(zGuild);
                            DataHandler.savePlayer(zPlayer);
                            player.sendMessage("Guilde " + zGuild.getName() + " (" + zGuild.getUUID() + ") erstellt!");
                            player.getInventory().setContents(Utility.removeMoneyBackpack(player.getInventory(), 50000));
                            player.updateInventory();
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
                    GuildInventory guildInventory = new GuildInventory(zGuild, zPlayer);
                    guildInventory.openInventory(player);
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
            zGuild.setUUID(uuid);
        } else {
            CreateGuildUUID(zGuild);
        }
    }
}
