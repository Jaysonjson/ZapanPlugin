package jayson.json.zapan.inventories.guild;

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

public class GuildPlayerInfoInventory implements Listener {

    @Nullable
    public Inventory inventory;
    public GuildPlayersInventory guildInventory;
    public String zPlayer;
    public GuildPlayerInfoInventory(String player, GuildPlayersInventory guildInventory) {
        this.guildInventory = guildInventory;
        this.zPlayer = player;
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }


    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 27, zPlayer);
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(13, Utility.createInventoryStack(Material.BLACK_WOOL, 1, "Degradieren"));
        inventory.setItem(16, Utility.createInventoryStack(Material.RED_WOOL, 1, "Kicken"));
        inventory.setItem(10, Utility.createInventoryStack(Material.GREEN_WOOL, 1, "Befördern"));
        inventory.setItem(22, Utility.createInventoryStack(Material.PAPER, 1, "Zurück"));
        inventory.setItem(4, Utility.createInventoryStack(Material.PLAYER_HEAD, 1, zPlayer));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                GuildPlayerInfoInventory guildPlayerInfoInventory = new GuildPlayerInfoInventory("Zapan", guildInventory);
            }
            if (event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Zurück")) {
                    guildInventory.openInventory((Player) event.getWhoClicked());
                }
            }
        }
    }
}
