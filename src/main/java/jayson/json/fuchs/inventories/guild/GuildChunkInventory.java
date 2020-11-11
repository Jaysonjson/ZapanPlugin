package jayson.json.fuchs.inventories.guild;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class GuildChunkInventory implements Listener {

    @Nullable
    Inventory inventory;

    public GuildChunkInventory() {
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, "Guilden Gebiete");
        setContents();
        player.openInventory(inventory);
    }

    private void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, new ItemStack(Material.CYAN_STAINED_GLASS_PANE));
        }

        inventory.setItem(2, Utility.createInventoryStack(Material.ORANGE_STAINED_GLASS_PANE, 5, "Chunks verbleibend"));
        inventory.setItem(6, new ItemStack(Material.PURPLE_STAINED_GLASS_PANE));
        inventory.setItem(31, new ItemStack(Material.RED_STAINED_GLASS_PANE));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
        }
    }
}
