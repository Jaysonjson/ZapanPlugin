package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.other.Scoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {

    @EventHandler
    public void DropItem(PlayerDropItemEvent event) {
        if(!Utility.canDropItem(event.getPlayer(), event.getPlayer().getLocation(), event.getPlayer().getWorld())) {
            event.setCancelled(true);
        }
        Scoreboard.updateScoreboard(event.getPlayer());
    }

}
