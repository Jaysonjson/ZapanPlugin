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
import org.bukkit.event.player.PlayerChatEvent;
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
        inventory.setItem(19, Utility.createInventoryWoolColor(area.breakBlocks, "Blöcke Zerstören", 1));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    if(event.getWhoClicked() instanceof Player) {
                        Player player = (Player) event.getWhoClicked();
                        String itemName = clickedItem.getItemMeta().getDisplayName();
                        if (itemName.equalsIgnoreCase(area.displayName)) {
                            chatPlayers.put(player, area);
                            player.closeInventory();
                        }
                        if (itemName.equalsIgnoreCase("Blöcke Zerstören")) {
                            area.breakBlocks = !area.breakBlocks;
                            DataHandler.saveArea(area);
                            //player.updateInventory();
                        }
                        setContents();
                    }
                }
            }
        }
    }

    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        if(chatPlayers.containsKey(player)) {
            if(!event.getMessage().equalsIgnoreCase("abbrechen")) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(Zapan.INSTANCE, () -> {
                    zArea area = chatPlayers.get(player);
                    area.displayName = event.getMessage();
                    chatPlayers.remove(player);
                    DataHandler.saveArea(area);
                    Utility.reloadAreas();
                    openInventory(player, area.name);
                });
            }
        }
    }
}
