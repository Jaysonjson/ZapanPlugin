package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

public class AreaInventory implements Listener {

    //ArrayList<Player> chatPlayers = new ArrayList<>();
    HashMap<Player, zArea> chatPlayers = new HashMap<>();
    @Nullable
    public Inventory inventory;
    public zArea area;
    public AreaInventory() {
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player, String areaName) {
        if(Utility.areaExists(areaName)) {
            area = DataHandler.loadArea(areaName);
            inventory = Bukkit.createInventory(player, 54, area.name);
            setContents();
            player.openInventory(inventory);
        }
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(10, Utility.createInventoryStack(Material.PAPER, 1, area.displayName));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem.hasItemMeta()) {
                String itemName = clickedItem.getItemMeta().getDisplayName();
                if(itemName.equalsIgnoreCase("Name")) {
                    chatPlayers.put((Player) event.getWhoClicked(), area);
                    event.getWhoClicked().closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(chatPlayers.containsKey(player)) {
            if(!event.getMessage().equalsIgnoreCase("abbrechen")) {
                chatPlayers.get(player).displayName = event.getMessage();
                chatPlayers.remove(player);
                openInventory(player, chatPlayers.get(player).name);
            }
        }
    }

}
