package jayson.json.zapan.items.currency;

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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldBarItem extends AbstractItem {


    public GoldBarItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getMaterial()), super.getId());
        oItem.init();

        NBTTagCompound tag = oItem.getTagCompound();
        tag.setDouble(zItemNBT.CONST_HACKSILVER_AMOUNT, 1250);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        System.out.println(Utility.getItemTag(oItem.nmsCopy).hasKey(zItemNBT.CONST_ITEM_ID));
        System.out.println(Utility.getItemTag(Utility.createNMSCopy(oItem.item)).hasKey(zItemNBT.CONST_ITEM_ID));
        oItem.lore.add(ChatColor.GRAY + "1.250Φ");
        oItem.setItem("\u00a76Gold Barren");
        System.out.println(Utility.getItemTag(Utility.createNMSCopy(oItem.item)).hasKey(zItemNBT.CONST_ITEM_ID));
        return oItem.item;
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
}
