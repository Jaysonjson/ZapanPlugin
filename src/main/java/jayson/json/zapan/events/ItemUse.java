package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.inventories.ItemInventoryData;
import jayson.json.zapan.items.AbstractItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUse implements Listener {

    @EventHandler
    public void ItemUse(PlayerInteractEvent event) {
       /* BookMeta bookMeta = (BookMeta) event.getItem().getItemMeta();
        bookMeta.setTitle("Skill Buch");
        bookMeta.setAuthor("Oberbürgermeister");
        List<String> pages = new ArrayList<>();
        pages.add("Skill Buch");
        pages.add("Stärke: 5");
        bookMeta.setPages(pages);
        event.getItem().setItemMeta(bookMeta);
              */
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
