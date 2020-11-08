package jayson.json.fuchs.events.entity;

import jayson.json.fuchs.Utility;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawn implements Listener {

        @EventHandler
        public void EntitySpawn(EntitySpawnEvent event) {
            if(event.getEntity() instanceof Monster) {
                if (!Utility.canEntitySpawn(event.getLocation(), event.getEntity().getWorld())) {
                    event.setCancelled(true);
                }
            }
        }

}

