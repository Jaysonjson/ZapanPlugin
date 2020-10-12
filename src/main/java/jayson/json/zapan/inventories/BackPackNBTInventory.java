package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zBackPack;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.UUID;

public class BackPackNBTInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public int backPackItem;
    int inventorySize;
    public BackPackNBTInventory(int backPack, int inventorySize) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Items");
        String contents = Utility.getItemTag(Utility.createNMSCopy(player.getInventory().getItemInMainHand())).getString(zItemNBT.CONST_INVENTORY_CONTENT);
        inventory.setContents(Utility.generateInventoryContent(contents));
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(inventory.getItem(backPackItem));
            NBTTagCompound tag = Utility.getItemTag(nmsStack);
            tag.setString(zItemNBT.CONST_INVENTORY_CONTENT, Utility.createInventoryContent(event.getInventory().getContents()));
            this.inventory.setItem(backPackItem, CraftItemStack.asBukkitCopy(nmsStack));
        }
    }
}
