package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class GoldNuggetItem extends AbstractzItem {
    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.GOLD_NUGGET));
        oItem.lore.add(ChatColor.GRAY + "17.43Φ");
        oItem.setItem(ChatColor.GOLD + "Goldklumpen");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 5.43);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

}
