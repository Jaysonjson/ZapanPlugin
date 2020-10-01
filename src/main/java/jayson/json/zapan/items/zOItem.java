package jayson.json.zapan.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class zOItem {
    public ItemStack item;
    public ItemMeta itemMeta;
    ArrayList<String> lore;

    public zOItem(ItemStack itemStack, ArrayList<String> lore) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = lore;
    }
}
