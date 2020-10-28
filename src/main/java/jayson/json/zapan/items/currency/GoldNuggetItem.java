package jayson.json.zapan.items.currency;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.*;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldNuggetItem extends AbstractItem {

    double currencyValue;
    public GoldNuggetItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, getId(),true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
                currencyValue = tag.getDouble(zItemNBT.HACKSILVER_AMOUNT);
            }
        } else {
            currencyValue = 17.43;
        }

        oItem.lore.add(ChatColor.GRAY + "" + currencyValue + "Φ");
        oItem.setItem("\u00a76Goldklumpen");
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
