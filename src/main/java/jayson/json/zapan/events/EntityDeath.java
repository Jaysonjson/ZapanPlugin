package jayson.json.zapan.events;

import jayson.json.zapan.References;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zdropobj.zMobDrop;
import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.lists.zItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        /*if(event.getEntity() instanceof Monster) {
            if(new Random().nextInt(2) == 1) {
                Utility.spawnCustomItem(zItem.SCRAP.getAbstractItem(), event.getEntity().getWorld(), event.getEntity().getLocation());
            }
        }*/
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
