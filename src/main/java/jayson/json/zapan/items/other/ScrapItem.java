package jayson.json.zapan.items.other;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ItemUseType;
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
    public ScrapItem(String id, Material material, ItemUseType itemUseType, double hacksilverAmount) {
        super(id, material, itemUseType);
        this.hacksilverAmount = hacksilverAmount;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getItemType()), super.getId());
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
    public @NotNull String getId() {
        return super.getId();
    }

    @Override
    public Material getItemType() {
        return super.getItemType();
    }

    @Override
    public double getHacksilverAmount() {
        return hacksilverAmount;
    }

    @Override
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }
}
