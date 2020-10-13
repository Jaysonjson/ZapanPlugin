package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zBackPack;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.other.InventoryPageContainer;
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
import java.util.UUID;


public class BackPackInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public zBackPack backPack;
    public ItemStack backPackItem;
    int inventorySize;
    public BackPackInventory(ItemStack backPack, int inventorySize) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory)) {
            if(event.getCurrentItem().hasItemMeta()) {
                NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(backPackItem));
                if(tag.hasKey(zItemNBT.CONST_IS_BACKPACK)) {
                    if (tag.getBoolean(zItemNBT.CONST_IS_BACKPACK)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Items");
        UUID uuid = UUID.fromString(Utility.getItemTag(Utility.createNMSCopy(backPackItem)).getString(zItemNBT.CONST_ITEM_UUID));
        //Vielleicht als Item NBT Tag speichern?
        backPack = DataHandler.loadBackPack(uuid);
        inventory.setContents(backPack.getItemStacks());
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
