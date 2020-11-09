package jayson.json.fuchs.objects.items.other;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.inventories.BackPackNBTInventory;
import jayson.json.fuchs.objects.items.*;
import net.minecraft.server.v1_16_R2.NBTTagCompound;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BackPackItemNBT extends AbstractItem {
    
	int inventorySize;
    String content;
    public BackPackItemNBT(String id, Material material, ItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
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
            if(tag.hasKey(zItemNBT.INVENTORY_CONTENT)) {
                content = tag.getString(zItemNBT.INVENTORY_CONTENT);
            }
        } else {
            content = "";
        }

        oItem.lore.add(inventorySize + " Slots");
        if(content != "") {
        	oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + true + "«");
        }
        oItem.setItem(ChatColor.RESET + "Rucksack");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.IS_BACKPACK, true);
        if(!tag.hasKey(zItemNBT.INVENTORY_CONTENT)) {
            tag.setString(zItemNBT.INVENTORY_CONTENT, content);
        }
        return tag;
    }

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
    public @NotNull Material getMaterial() {
        return super.material;
    }

    @Override
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }
}
