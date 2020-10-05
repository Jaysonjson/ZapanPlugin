package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ScrapItem implements IzItem {

    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.NETHERITE_SCRAP));
        oItem.lore.add("\u00a760.25Φ");
        oItem.setItem(ChatColor.BOLD + "Schrott");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_CURRENCY_AMOUNT, 0.25);
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
