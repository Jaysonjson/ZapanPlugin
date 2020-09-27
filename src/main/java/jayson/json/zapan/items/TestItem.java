package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestItem implements IzItem{
    public ItemStack GetItem() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta im = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("\u00a74Bestimmt ein echter Diamand!");
        im.setLore(lore);
        item.setItemMeta(im);
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setString("key", "value");
        nmsItem.setTag(tag);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }
}
