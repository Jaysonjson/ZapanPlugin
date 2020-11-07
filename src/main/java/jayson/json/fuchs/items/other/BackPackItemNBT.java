package jayson.json.fuchs.items.other;

import jayson.json.fuchs.inventories.BackPackNBTInventory;
import jayson.json.fuchs.items.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BackPackItemNBT extends AbstractItem {
    int inventorySize = 36;
    String id;
    public BackPackItemNBT(String id, Material material, ItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
    }
/*
    @Override
    public ItemStack createItem(Player player) {
        zOItem oItem = new zOItem(this, player);
        oItem.init();

        NBTTagCompound tag = oItem.tagCompound();
        //tag.setString(zItemNBT.CONST_ITEM_UUID, UUID.randomUUID().toString());
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.CONST_IS_BACKPACK, true);
        tag.setString(zItemNBT.CONST_INVENTORY_CONTENT, "");
        oItem.nmsCopy.setTag(tag);

        oItem.lore.add(inventorySize + " Slots");
        oItem.setItem(ChatColor.RESET + "Rucksack NBT");
        return oItem.item;
    }
*/
    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackNBTInventory inventory = new BackPackNBTInventory(player.getInventory().getHeldItemSlot(), inventorySize);
        inventory.openInventory(player);
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

    @Override
    public @NotNull String getId() {
        return super.id;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        return null;
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