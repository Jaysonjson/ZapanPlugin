package jayson.json.fuchs.events.entity.player;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.inventories.classes.StarterClassInventory;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.npc.NPC;
import jayson.json.fuchs.other.Scoreboard;
import jayson.json.fuchs.skillclass.zClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        Utility.refreshHearts(event.getPlayer(), zPlayer);
        Scoreboard.updateScoreboard(player, zPlayer);
        NPC.sendSinglePacket(player);
        if(zPlayer.getPlayerClass().current.equals(zClass.NONE)) {
            StarterClassInventory classInventory = new StarterClassInventory();
            classInventory.openInventory(event.getPlayer());
        }
        //AreaInventory areaInventory = new AreaInventory();
        //areaInventory.openInventory(player, Utility.getNearestArea(World.Environment.NORMAL, player.getLocation()).name);
        //GuildChunkInventory inventory = new GuildChunkInventory();
        //inventory.openInventory(event.getPlayer());
      /*  zPlayer player = DataHandler.loadPlayer(event.getPlayer().getUniqueId());
        if(player.isInGuild()) {
            zGuild guild = DataHandler.loadGuild(player.getGuildUuid());
            System.out.println("open guild_" + guild.name);
            GuildInventory guildInventory = new GuildInventory(guild, player);
            guildInventory.openInventory(event.getPlayer());
        }*/

      /*  Player player = event.getPlayer();
        Inventory inventory = Bukkit.createInventory(player, 54, player.getDisplayName() + "'s Inventar");
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            inventory.setItem(i, player.getInventory().getContents()[i]);
        }
        player.openInventory(inventory);
       */
    }

}
