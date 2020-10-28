package jayson.json.zapan.items.interfaces;

import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.zAdditionalItemInformation;
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
    ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem);
    void update(ItemStack itemStack);
    ItemStack update(Player player);
    ItemUseType getItemUseType();
    @NotNull
    Material getMaterial();
    void setMaterial(Material material);
    NBTTagCompound getTag(NBTTagCompound tag);
}
