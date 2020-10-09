package jayson.json.zapan.items.other;

import jayson.json.zapan.inventories.BackPackInventory;
import jayson.json.zapan.inventories.BackPackNBTInventory;
import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BackPackItemNBT extends AbstractItem {
    int inventorySize = 36;
    String id;
    public BackPackItemNBT(String id, int inventorySize) {
        this.id = id;
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(new ItemStack(Material.HEART_OF_THE_SEA), getId());
        oItem.lore.add(inventorySize + " Slots");
        oItem.setItem(ChatColor.RESET + "Rucksack");
        NBTTagCompound tag = oItem.tagCompound();
        //tag.setString(zItemNBT.CONST_ITEM_UUID, UUID.randomUUID().toString());
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.CONST_IS_BACKPACK, true);
        tag.setString(zItemNBT.CONST_INVENTORY_CONTENT, "");
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackNBTInventory inventory = new BackPackNBTInventory(itemStack, inventorySize);
        inventory.openInventory(player);
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

    @Override
    public @NotNull String getId() {
        return id;
    }
}
