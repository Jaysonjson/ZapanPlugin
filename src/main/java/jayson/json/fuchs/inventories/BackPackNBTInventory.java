package jayson.json.fuchs.inventories;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import jayson.json.fuchs.objects.items.FuchsItem;
import jayson.json.fuchs.objects.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory)) {
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(clickedItem));
                    if (tag.hasKey(zItemNBT.IS_BACKPACK)) {
                        if (tag.getBoolean(zItemNBT.IS_BACKPACK)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
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
            ItemStack itemStack = inventory.getItem(backPackItem);
            FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(itemStack), itemStack);
            fuchsItem.changeStringTag(zItemNBT.INVENTORY_CONTENT, Utility.createInventoryContent(event.getInventory().getContents()));
            this.inventory.setItem(backPackItem, fuchsItem.getItemStack());
        }
    }
}
