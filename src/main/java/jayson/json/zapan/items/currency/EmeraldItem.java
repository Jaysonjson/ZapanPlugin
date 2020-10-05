package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class EmeraldItem extends AbstractzItem {

    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.EMERALD));
        oItem.lore.add(ChatColor.GRAY + "1¢");
        oItem.setItem(ChatColor.DARK_GREEN + "Smaragd");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_EMERALD_AMOUNT, 1);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

}
