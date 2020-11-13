package jayson.json.fuchs.objects.items.other;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.inventories.backpack.BackPackInventory;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
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

    int inventorySize;
    String uuid;
    public BackPackItem(String id, Material material, ItemUseType itemUseType, int inventorySize) {
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
            if(tag.hasKey(zItemNBT.ITEM_UUID)) {
                uuid = tag.getString(zItemNBT.ITEM_UUID);
            }
        } else {
            uuid = generateUUID();
        }

        oItem.lore.add(inventorySize + " Slots");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + uuid + "«");
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
        if(!tag.hasKey(zItemNBT.ITEM_UUID)) {
            tag.setString(zItemNBT.ITEM_UUID, uuid);
        }
        return tag;
    }

    private String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        if(DataHandler.backPackExists(UUID.fromString(uuid))) {
            uuid = generateUUID();
        }
        return uuid;
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
