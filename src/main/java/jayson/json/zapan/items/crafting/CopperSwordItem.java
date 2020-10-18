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

public class CopperSwordItem extends AbstractItem {

    int damage;
    public CopperSwordItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player,true);
        oItem.lore.add(ChatColor.GRAY + "Ein Schwert aus Kupfer");
        oItem.setItem(ChatColor.GOLD + "Kupfer Schwert");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        if (!tag.hasKey(zItemNBT.CONST_ITEM_DURABILITY)) {
            tag.setInt(zItemNBT.CONST_ITEM_DURABILITY, getDurability());
        }
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        oItem.lore.add(ChatColor.BLUE + "" + tag.getInt(zItemNBT.CONST_ITEM_DURABILITY) + "/" + getDurability());
        oItem.itemMeta = oItem.item.getItemMeta();
        oItem.itemMeta.setLore(oItem.lore);
        oItem.item.setItemMeta(oItem.itemMeta);
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
    public int getDamage() {
        return 3;
    }

    @Override
    public int getDurability() {
        return 435;
    }
}
