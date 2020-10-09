package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zBackPack;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.other.InventoryPageContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;


public class BackPackInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public zBackPack backPack;
    public ItemStack backPackItem;
    int inventorySize = 36;
    public BackPackInventory(ItemStack backPack, int inventorySize) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player) {
        Inventory gui = Bukkit.createInventory(player, inventorySize, "Items");
        UUID uuid = UUID.fromString(Utility.getItemTag(Utility.createNMSCopy(backPackItem)).getString(zItemNBT.CONST_ITEM_UUID));
        backPack = DataHandler.loadBackPack(uuid);
        gui.setContents(backPack.getItemStacks());
        inventory = gui;
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            backPack.setItemStacks(inventory.getContents());
            DataHandler.saveBackPack(backPack);
        }
    }
}
