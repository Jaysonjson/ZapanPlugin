package jayson.json.fuchs.items.vanillaOverride;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.items.*;
import jayson.json.fuchs.items.nbt.INBTObject;
import jayson.json.fuchs.items.nbt.NBTBoolean;
import jayson.json.fuchs.items.nbt.NBTInteger;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;

public class IronIngotItem extends AbstractItem {


    int amount = 0;
    public IronIngotItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, getId(),false);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.ITEM_AMOUNT)) {
                amount = tag.getInt(zItemNBT.ITEM_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(75);
            amount += new Random().nextInt(25);
        }
        oItem.lore.add(ChatColor.GRAY + "" + amount + "g");
        oItem.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        oItem.setItem(ChatColor.GRAY + "Eisen");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, true);
        if(!tag.hasKey(zItemNBT.ITEM_AMOUNT)) {
            tag.setInt(zItemNBT.ITEM_AMOUNT, amount);
        }
        return tag;
    }

    @Override
    public HashMap<String, INBTObject> getNBTObjects() {
        HashMap<String, INBTObject> tags = new HashMap<>();
        tags.put(zItemNBT.CAN_CRAFT, new NBTBoolean(true, true));
        tags.put(zItemNBT.CAN_CRAFT_MINECRAFT, new NBTBoolean(true, true));
        tags.put(zItemNBT.ITEM_AMOUNT, new NBTInteger(0, false));
        return tags;
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

    @Override
    public boolean isVanillaOverride() {
        return true;
    }

}