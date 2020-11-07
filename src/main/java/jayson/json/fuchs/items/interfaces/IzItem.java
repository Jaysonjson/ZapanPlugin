package jayson.json.fuchs.items.interfaces;

import jayson.json.fuchs.items.ItemUseType;
import jayson.json.fuchs.items.zAdditionalItemInformation;
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
    @Deprecated
    ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem);
    default ItemStack createItem() {
        return createItem(null, null, null);
    };
    default ItemStack createItem(Player player) {
        return createItem(player, null, null);
    };
    default ItemStack createItem(ItemStack itemStack) {
        return createItem(null, itemStack, null);
    };
    default ItemStack createItem(Player player, ItemStack itemStack) {
        return createItem(player, itemStack, null);
    };
    ItemStack update(Player player, ItemStack itemStack, zAdditionalItemInformation infoItem);
    ItemUseType getItemUseType();
    @NotNull
    Material getMaterial();
    void setMaterial(Material material);
    NBTTagCompound getTag(NBTTagCompound tag);
}
