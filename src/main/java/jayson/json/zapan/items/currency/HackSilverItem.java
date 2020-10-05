package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.IzItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class HackSilverItem implements IzItem {
    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.IRON_NUGGET));
        oItem.lore.add(ChatColor.GRAY + "1Φ");
        oItem.setItem(ChatColor.GRAY + "Hacksilber");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 1);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public float getCurrencyValue() {
        return 0;
    }

    @Override
    public float setCurrencyValue() {
        return 0;
    }
}
