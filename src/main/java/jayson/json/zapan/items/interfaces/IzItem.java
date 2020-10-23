package jayson.json.zapan.items.interfaces;

import jayson.json.zapan.items.ItemUseType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzItem {
    String id = "";
    String getId();
    void setId(String id);
    ItemStack getItem();
    ItemStack getItem(Player player);
    void update(ItemStack itemStack);
    ItemStack update(Player player);
    ItemUseType getItemUseType();
    Material getMaterial();
    void setMaterial(Material material);
}
