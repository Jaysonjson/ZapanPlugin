package jayson.json.fuchs.objects.items.currency;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldBarItem extends AbstractItem {

    double currencyValue;
    public GoldBarItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
                currencyValue = tag.getDouble(zItemNBT.HACKSILVER_AMOUNT);
            }
        } else {
            currencyValue = 1250;
        }

        oItem.lore.add(ChatColor.GRAY + "" + currencyValue + "Φ");
        oItem.setItem(ChatColor.GOLD + "Gold Barren");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
            tag.setDouble(zItemNBT.HACKSILVER_AMOUNT, currencyValue);
        }
        return tag;
    }


    @Override
    public @NotNull String getId() {
        return super.id;
    }

    @Override
    public @NotNull Material getMaterial() {
        return material;
    }

    @Override
    public IItemUseType getItemUse() {
        return super.getItemUse();
    }
}
