package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GoldBarItem extends AbstractzItem {

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(new ItemStack(Material.GOLD_INGOT));
        oItem.lore.add(ChatColor.GRAY + "1.250Φ");
        oItem.setItem("\u00a76Gold Barren");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 1250);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }



}
