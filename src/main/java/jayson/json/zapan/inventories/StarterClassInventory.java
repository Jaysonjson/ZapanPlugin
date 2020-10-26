package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.skillclass.zClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class StarterClassInventory implements Listener {

    @Nullable
    public Inventory inventory;
    public zPlayer zPlayer;
    public StarterClassInventory() {
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, "Starterklasse");
        zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(20, Utility.createInventoryStack(zClass.FIGHTER.getSymbol(), 1, "Kämpfer"));
        inventory.setItem(29, Utility.createInventoryStack(Material.PAPER, 1, "Lore"));
        inventory.setItem(24, Utility.createInventoryStack(zClass.FARMER.getSymbol(), 1, "Farmer"));
        inventory.setItem(33, Utility.createInventoryStack(Material.PAPER, 1, "Lore"));
        Utility.createInventoryBorder(inventory, Material.WHITE_STAINED_GLASS_PANE);
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            ItemStack clickedItem = event.getCurrentItem();
            ItemMeta clickedItemMeta = clickedItem.getItemMeta();
            if(clickedItemMeta.getDisplayName().equalsIgnoreCase("Kämpfer")) {
                zPlayer.getPlayerClass().current = zClass.FIGHTER;
            } else if(clickedItemMeta.getDisplayName().equalsIgnoreCase("Farmer")) {
                zPlayer.getPlayerClass().current = zClass.FARMER;
            }
            DataHandler.savePlayer(zPlayer);
            event.getWhoClicked().closeInventory();
        }
    }
}
