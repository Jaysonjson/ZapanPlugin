package jayson.json.zapan.items.other;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ScrapItem extends AbstractItem {

    double hacksilverAmount;
    public ScrapItem(String id, double hacksilverAmount) {
        super(id);
        this.hacksilverAmount = hacksilverAmount;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(new ItemStack(Material.NETHERITE_SCRAP), getId());
        oItem.lore.add(ChatColor.GRAY + "" + getHacksilverAmount() + "Φ");
        oItem.setItem(ChatColor.RESET + "Schrott");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_HACKSILVER_AMOUNT, hacksilverAmount);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }


    @Override
    public double getHacksilverAmount() {
        return hacksilverAmount;
    }
}
