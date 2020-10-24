package jayson.json.zapan.items.crafting;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SilverIngotItem extends AbstractItem {

    int damage;
    int amount;
    public SilverIngotItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, getId(),true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.ITEM_AMOUNT)) {
                amount = tag.getInt(zItemNBT.ITEM_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(400);
            amount += new Random().nextInt(100);
        }

        oItem.lore.add(ChatColor.GRAY + "" + amount + "g");
        oItem.setItem(ChatColor.GRAY + "Silber");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(zItemNBT.ITEM_AMOUNT)) {
            tag.setInt(zItemNBT.ITEM_AMOUNT, amount);
        }
        return tag;
    }

    @Override
    public @NotNull String getId() {
        return super.getId();
    }

    @Override
    public Material getMaterial() {
        return super.getMaterial();
    }

    @Override
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }

    @Override
    public int getDamageValue() {
        return damage;
    }
}
