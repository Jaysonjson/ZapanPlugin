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
    public ItemStack createItem(Player player, ItemStack stack) {
        zOItem oItem = new zOItem(this, player,true);

        oItem.setItem(ChatColor.YELLOW + "Malz");

        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
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
