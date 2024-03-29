package jayson.json.fuchs.events.entity;

import jayson.json.fuchs.References;
import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.drop.obj.zMobDrop;
import jayson.json.fuchs.objects.items.AbstractItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent event) {
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
