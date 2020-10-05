package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class zOItem {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public net.minecraft.server.v1_16_R2.ItemStack nmsCopy;
    public zOItem(ItemStack itemStack, ArrayList<String> lore) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = lore;
    }

    public zOItem(ItemStack itemStack) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
    }

    public void setItem(String displayName) {
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
        nmsCopy = createNMSCopy();
    }

    public net.minecraft.server.v1_16_R2.ItemStack createNMSCopy() {
        return CraftItemStack.asNMSCopy(item);
    }

    public NBTTagCompound tagCompound() {
        return nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
    }
}
