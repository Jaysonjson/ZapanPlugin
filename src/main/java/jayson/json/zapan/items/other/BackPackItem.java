package jayson.json.zapan.items.other;

import jayson.json.zapan.inventories.BackPackInventory;
import jayson.json.zapan.items.AbstractzItem;
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

public class BackPackItem extends AbstractzItem {
    String id;
    public BackPackItem(String id) {
        this.id = id;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(new ItemStack(Material.HEART_OF_THE_SEA), getId());
        oItem.setItem(ChatColor.BOLD + "Backpack");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setString(zItemNBT.CONST_ITEM_UUID, UUID.randomUUID().toString());
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackInventory inventory = new BackPackInventory(itemStack);
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
