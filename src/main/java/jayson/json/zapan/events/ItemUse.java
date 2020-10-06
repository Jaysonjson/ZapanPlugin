package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.interfaces.IzAbilityItem;
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
        if(!event.hasBlock()) {
            if (Utility.isAbilityItem(event.getItem())) {
                AbstractzItem abstractzItem = Utility.getAbstractItemFromNMS(event.getItem());
                if (abstractzItem != null && abstractzItem.isAbilityItem()) {
                    abstractzItem.ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
                }
            }
        }

    }
}
