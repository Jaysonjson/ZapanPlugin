package jayson.json.zapan.items.other;

import jayson.json.zapan.inventories.BackPackInventory;
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

public class BackPackItem extends AbstractItem {
    int inventorySize = 36;

    public BackPackItem(String id, Material material, int inventorySize) {
        super(id, material);
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getItemType()), super.getId());
        oItem.lore.add(inventorySize + " Slots");
        oItem.setItem(ChatColor.RESET + "Rucksack");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setString(zItemNBT.CONST_ITEM_UUID, UUID.randomUUID().toString());
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.CONST_IS_BACKPACK, true);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackInventory inventory = new BackPackInventory(itemStack, inventorySize);
        inventory.openInventory(player);
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }
    @Override
    public @NotNull String getId() {
        return super.getId();
    }

    @Override
    public Material getItemType() {
        return super.getItemType();
    }
}
