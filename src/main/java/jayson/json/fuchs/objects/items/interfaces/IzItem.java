package jayson.json.fuchs.objects.items.interfaces;

import jayson.json.fuchs.objects.items.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IzItem {
    String id = "";
    @NotNull
    String getId();
    void setId(String id);
    ItemStack createItem(Player player, ItemStack stack);
    default ItemStack createItem() {
        return createItem(null, null);
    };
    default ItemStack createItem(Player player) {
        return createItem(player, null);
    };
    default ItemStack createItem(ItemStack itemStack) {
        return createItem(null, itemStack);
    };
    ItemStack update(Player player, ItemStack itemStack);
    ItemUseType getItemUseType();
    default IItemUseType getItemUse() {
    	return getItemUseType();
    };
    @NotNull
    Material getMaterial();
    void setMaterial(Material material);
    NBTTagCompound getTag(NBTTagCompound tag);
}
