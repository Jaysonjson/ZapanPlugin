package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickup implements Listener {

    @EventHandler
    public void ItemPickup(EntityPickupItemEvent event) {
        ItemStack itemStack = event.getItem().getItemStack();
        if(Utility.isAbstractItem(itemStack)) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                event.getItem().setItemStack(Utility.getAbstractItemFromNMS(itemStack).getItem(player));
            }
        }
    }

}
