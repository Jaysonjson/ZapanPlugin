package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        if(!Utility.canBreakBlock(event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

}
