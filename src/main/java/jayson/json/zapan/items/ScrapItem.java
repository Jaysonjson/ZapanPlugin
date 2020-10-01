package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ScrapItem implements IzItem {
    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.NETHERITE_SCRAP), new ArrayList<>());
        oItem.lore.add("\u00a760.25€");
        oItem.itemMeta.setLore(oItem.lore);
        oItem.itemMeta.setDisplayName(ChatColor.BOLD + "Schrott");
        oItem.item.setItemMeta(oItem.itemMeta);
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(oItem.item);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 0.25);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        nmsItem.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(nmsItem);
        return oItem.item;
    }
}
