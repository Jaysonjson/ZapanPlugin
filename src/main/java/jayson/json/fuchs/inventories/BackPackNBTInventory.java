package jayson.json.fuchs.inventories;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import jayson.json.fuchs.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;

public class BackPackNBTInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public int backPackItem;
    int inventorySize;
    public BackPackNBTInventory(int backPack, int inventorySize) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Items");
        String contents = Utility.getItemTag(Utility.createNMSCopy(inventory.getItem(backPackItem))).getString(zItemNBT.INVENTORY_CONTENT);
        inventory.setContents(Utility.generateInventoryContent(contents));
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(inventory.getItem(backPackItem));
            NBTTagCompound tag = Utility.getItemTag(nmsStack);
            tag.setString(zItemNBT.INVENTORY_CONTENT, Utility.createInventoryContent(event.getInventory().getContents()));
            this.inventory.setItem(backPackItem, CraftItemStack.asBukkitCopy(nmsStack));
        }
    }
}
