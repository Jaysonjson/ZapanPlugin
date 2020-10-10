package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.inventories.GuildChunkInventory;
import jayson.json.zapan.npc.NPC;
import jayson.json.zapan.other.Scoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        //event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), new GoldBarItem().GetItem());
        Utility.refreshHearts(event.getPlayer());
        Scoreboard.updateScoreboard(event.getPlayer());
        NPC.sendSinglePacket(event.getPlayer());
        GuildChunkInventory inventory = new GuildChunkInventory();
        inventory.openInventory(event.getPlayer());
    }

}
