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
import org.jetbrains.annotations.NotNull;

public class MaltItem extends AbstractItem {

    int damage;
    public MaltItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getItemType()), super.getId());
        Damageable damageable = (Damageable) oItem.itemMeta;
        damageable.setDamage(getDamageValue());
        oItem.itemMeta.setUnbreakable(true);
        oItem.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        oItem.setItem(ChatColor.YELLOW + "Malz");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT, true);
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
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }

    @Override
    public int getDamageValue() {
        return damage;
    }
}
