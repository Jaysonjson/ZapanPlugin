package jayson.json.zapan.events.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase("Items")) {
            if(event.getCurrentItem().hasItemMeta()) {

            }
        }
    }
}
