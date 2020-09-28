package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ScrapItem implements IzItem{
    @Override
    public ItemStack GetItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_SCRAP);
        ItemMeta im = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("\u00a760.25€");
        im.setLore(lore);
        im.setDisplayName(ChatColor.BOLD + "Schrott");
        item.setItemMeta(im);
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setDouble(zItem.CONST_CURRENCY_AMOUNT, 0.25);
        tag.setBoolean(zItem.CONST_CAN_CRAFT_MINECRAFT, false);
        nmsItem.setTag(tag);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }
}
