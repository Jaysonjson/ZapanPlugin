package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.inventories.AreaInventory;
import jayson.json.zapan.inventories.GuildChunkInventory;
import jayson.json.zapan.inventories.StarterClassInventory;
import jayson.json.zapan.inventories.guild.GuildInventory;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.npc.NPC;
import jayson.json.zapan.other.Scoreboard;
import jayson.json.zapan.skillclass.zClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        Utility.refreshHearts(event.getPlayer(), zPlayer);
        Scoreboard.updateScoreboard(player, zPlayer);
        NPC.sendSinglePacket(player);
        if(zPlayer.getPlayerClass().type.equals(zClass.NONE)) {
            StarterClassInventory classInventory = new StarterClassInventory();
            classInventory.openInventory(event.getPlayer());
        }
        AreaInventory areaInventory = new AreaInventory();
        areaInventory.openInventory(player, "fuchsia");
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
