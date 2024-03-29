package jayson.json.fuchs.objects.items.type.crafting.copper;

import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CopperRodItem extends AbstractItem {

    public CopperRodItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType, damageValue);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        zOItem oItem = new zOItem(this, player, true);

        oItem.lore.add(ChatColor.GRAY + "Eine Stange aus Kupfer");
        oItem.setItem(ChatColor.GOLD + "Kupfer Stange");

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
        return super.id;
    }

    @Override
    public @NotNull Material getMaterial() {
        return super.material;
    }

    @Override
    public IItemUseType getItemUse() {
        return super.getItemUse();
    }

}
