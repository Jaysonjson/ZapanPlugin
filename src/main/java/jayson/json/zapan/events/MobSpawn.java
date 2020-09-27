package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zArea;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawn implements Listener {

        @EventHandler
        public void EntitySpawn(EntitySpawnEvent event) {
            if(Utility.IsInSpawnArea(event.getLocation(), event.getEntity().getWorld())) {
                event.setCancelled(true);
            }
        }
}

