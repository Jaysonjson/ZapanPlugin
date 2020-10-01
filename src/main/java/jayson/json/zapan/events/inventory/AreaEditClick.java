package jayson.json.zapan.events.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class AreaEditClick implements Listener {

    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Gebiet Bearbeiten")) {
            if(event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Spawn Mobs")) {
                    event.setCurrentItem(new ItemStack(Material.GREEN_WOOL));
                    event.setCancelled(true);
                }
            }
        }
    }
}
