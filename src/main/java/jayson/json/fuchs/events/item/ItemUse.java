package jayson.json.fuchs.events.item;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.AbstractItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemUse implements Listener {

    @EventHandler
    public void ItemUse(PlayerInteractEvent event) {
        if(event.getItem() != null) {

            if(Utility.isBannedItem(event.getItem())) {
                event.setCancelled(true);
            }
            if (event.getItem().getType() != Material.AIR) {
                if (Utility.isAbilityItemAll(event.getPlayer(), event.getItem())) {
                    AbstractItem abstractItem = Utility.getAbstractItemFromNMS(event.getItem());
                    if (abstractItem != null && abstractItem.isAbilityItem()) {
                        abstractItem.ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
                    }
                }
            }
        }
    }
}
