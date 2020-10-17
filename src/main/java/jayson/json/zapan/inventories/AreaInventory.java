package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class AreaInventory implements Listener {

    @Nullable
    public Inventory inventory;

    public AreaInventory() {
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, "Gebiet");
        setContents();
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {

        }
    }

}
