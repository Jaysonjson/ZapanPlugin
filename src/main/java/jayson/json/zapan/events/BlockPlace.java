package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        if(!Utility.CanPlaceBlock(event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
}
