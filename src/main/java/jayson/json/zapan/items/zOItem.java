package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class zOItem {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public String id;
    public net.minecraft.server.v1_16_R2.ItemStack nmsCopy;
    public zOItem(ItemStack itemStack, ArrayList<String> lore, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = lore;
        this.id = id;
    }

    public zOItem(ItemStack itemStack, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
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
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        tag.setString(zItemNBT.CONST_ITEM_ID, id);
        return tag;
    }
}
