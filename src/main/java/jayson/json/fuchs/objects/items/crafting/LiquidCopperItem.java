package jayson.json.fuchs.objects.items.crafting;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.*;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LiquidCopperItem extends AbstractItem {

    int damage;
    int amount;
    public LiquidCopperItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
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
        zOItem oItem = new zOItem(this, player, stack, true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
                amount = tag.getInt(zItemNBT.LIQUID_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(400);
            amount += new Random().nextInt(100);
        }

        oItem.lore.add(ChatColor.GRAY + "" + amount + "ml");
        oItem.lore.add(ChatColor.GRAY + "Flüssiges Kupfer in einen Eimer");
        oItem.setItem(ChatColor.GOLD + "Flüssiges Kupfer");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
            tag.setInt(zItemNBT.LIQUID_AMOUNT, amount);
        }
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

    @Override
    public int getDamageValue() {
        return damage;
    }
}
