package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.zItem;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {
    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Monster) {
            if(new Random().nextInt(2) == 1) {
                Utility.SpawnCustomItem(zItem.SCRAP, event.getEntity().getWorld(), event.getEntity().getLocation());
            }
        }
    }
}
