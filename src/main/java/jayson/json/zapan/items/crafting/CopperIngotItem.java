package jayson.json.zapan.items.crafting;

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

public class CopperIngotItem extends AbstractItem {

    int damage;
    public CopperIngotItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player,true);
        oItem.init();
        int amount = new Random().nextInt(400);
        amount += new Random().nextInt(100);
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey("amount")) {
            tag.setInt("amount", amount);
        }
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        oItem.lore.add(ChatColor.GRAY + "" + tag.getInt("amount") + "g");
        oItem.setItem(ChatColor.GOLD + "Kupfer");
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
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }

    @Override
    public int getDamageValue() {
        return damage;
    }

    @Override
    public int getEarthValue() {
        return 2;
    }

    @Override
    public int getWaterValue() {
        return 6;
    }
}
