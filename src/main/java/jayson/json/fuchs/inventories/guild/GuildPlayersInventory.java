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

public class GuildPlayersInventory implements Listener {

    @Nullable
    public Inventory inventory;
    public GuildInventory guildInventory;
    public GuildPlayersInventory(GuildInventory guildInventory) {
        this.guildInventory = guildInventory;
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }


    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, "Spieler");
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(10, Utility.createInventoryStack(Material.PLAYER_HEAD, 1, "Jayson"));
        inventory.setItem(40, Utility.createInventoryStack(Material.PAPER, 1, "Zurück"));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                GuildPlayerInfoInventory guildPlayerInfoInventory = new GuildPlayerInfoInventory("Zapan", this);
                guildPlayerInfoInventory.openInventory((Player) event.getWhoClicked());
            }
            if (event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Zurück")) {
                    guildInventory.openInventory((Player) event.getWhoClicked());
                }
            }
        }
    }

}
