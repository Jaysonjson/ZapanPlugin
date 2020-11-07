package jayson.json.fuchs.events;

import jayson.json.fuchs.References;
import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.zdropobj.zMobDrop;
import jayson.json.fuchs.items.AbstractItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        Random random = new Random();
        EntityType type = event.getEntityType();
        for (zMobDrop mobDrop : References.drops.getMobDrops()) {
            if (type.equals(mobDrop.type)) {
                for (AbstractItem abstractItem : mobDrop.itemDrops.keySet()) {
                    if (random.nextInt(mobDrop.itemDrops.get(abstractItem)) == 1) {
                        Player player = null;
                        if (event.getEntity().getKiller() != null) {
                            player = event.getEntity().getKiller();
                        }
                        Utility.spawnCustomItem(player, abstractItem, event.getEntity().getWorld(), event.getEntity().getLocation());
                    }
                }
            }
        }
    }

}
