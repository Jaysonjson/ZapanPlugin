package jayson.json.fuchs.objects.items.other;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.*;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ScrapItem extends AbstractItem {

    double currencyValue;
    public ScrapItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
                currencyValue = tag.getDouble(zItemNBT.HACKSILVER_AMOUNT);
            }
        } else {
            currencyValue = 0.25;
        }

        oItem.lore.add(ChatColor.GRAY + "" + currencyValue + "Φ");
        oItem.setItem(ChatColor.RESET + "Schrott");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        if(!tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
            tag.setDouble(zItemNBT.HACKSILVER_AMOUNT, currencyValue);
        }
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public @NotNull String getId() {
        return super.id;
    }

    @Override
    public @NotNull Material getMaterial() {
        return super.material;
    }


    @Override
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }
}