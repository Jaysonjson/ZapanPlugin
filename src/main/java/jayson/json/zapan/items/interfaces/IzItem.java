package jayson.json.zapan.items.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzItem {

    ItemStack getItem();
    ItemStack getItem(Player player);
    void update(ItemStack itemStack);
    ItemStack update(Player player);
    String getId();
    void setId();
}