package jayson.json.zapan.items.interfaces;

import jayson.json.zapan.items.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzItem {
    String id = "";
    String getId();
    void setId(String id);
    ItemStack createItem();
    ItemStack createItem(Player player, ItemStack stack);
    void update(ItemStack itemStack);
    ItemStack update(Player player);
    ItemUseType getItemUseType();
    Material getMaterial();
    void setMaterial(Material material);
    NBTTagCompound getTag(NBTTagCompound tag);
}
