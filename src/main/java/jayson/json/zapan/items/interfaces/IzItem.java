package jayson.json.zapan.items.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzItem {

    ItemStack getItem();
    ItemStack getItem(Player player);
    IzAbilityItem getAbilityItem();
}
