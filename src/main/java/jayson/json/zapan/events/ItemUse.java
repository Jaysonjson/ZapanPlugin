package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.AbstractItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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
