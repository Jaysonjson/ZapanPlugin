package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.IzItem;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GoldBarItem implements IzItem {

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta im = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("\u00a761.250€");
        im.setLore(lore);
        im.setDisplayName("\u00a76Gold Barren");
        item.setItemMeta(im);
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 1250);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        nmsItem.setTag(tag);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }

}
