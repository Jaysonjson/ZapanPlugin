package jayson.json.fuchs.events.item;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.other.Scoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {

    @EventHandler
    public void dropItemEvent(PlayerDropItemEvent event) {
        if(!Utility.canDropItem(event.getPlayer(), event.getPlayer().getLocation(), event.getPlayer().getWorld())) {
            event.setCancelled(true);
        }
        Scoreboard.updateScoreboard(event.getPlayer());
    }

}
