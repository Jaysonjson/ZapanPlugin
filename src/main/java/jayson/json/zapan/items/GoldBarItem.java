package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GoldBarItem implements IzItem {
    @Override
    public ItemStack GetItem() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta im = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("\u00a761.250€");
        im.setLore(lore);
        im.setDisplayName("\u00a76Gold Bar");
        item.setItemMeta(im);
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setDouble("currencyAmount", 1250);
        tag.setBoolean("canCraft", false);
        nmsItem.setTag(tag);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }
}
