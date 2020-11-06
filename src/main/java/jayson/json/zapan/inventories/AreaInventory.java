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
import java.util.regex.Pattern;

public class AreaInventory implements Listener {

    //ArrayList<Player> chatPlayers = new ArrayList<>();
    HashMap<Player, zArea> chatPlayers = new HashMap<>();
    HashMap<Player, ChatType> chatPlayersType = new HashMap<>();

    enum ChatType {
        NAME, PRIORITY
    };

    @Nullable
    public Inventory inventory;
    public zArea area;
    public AreaListInventory areaListInventory;
    public AreaInventory(zArea area, AreaListInventory areaListInventory) {
        this.area = area;
        this.areaListInventory = areaListInventory;
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void openInventory(Player player) {
        if(area != null) {
            inventory = Bukkit.createInventory(player, 54, area.displayName);
            setContents();
            player.openInventory(inventory);
        }
    }

    public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(10, Utility.createInventoryStack(Material.PAPER, 1, area.displayName));
        int prioItemAmount = area.priority;;
        if(area.priority < 1) {
            prioItemAmount = 1;
        } else if(area.priority > 64) {
            prioItemAmount = 64;
        }
        inventory.setItem(11, Utility.createInventoryStack(Material.SLIME_BALL, prioItemAmount,"Priorität: " + area.priority));
        inventory.setItem(19, Utility.createInventoryWoolColor(area.breakBlocks, "Blöcke Zerstören", 1));
        inventory.setItem(49, Utility.createInventoryStack(Material.PAPER, 1, "Zurück"));
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
                            chatPlayersType.put(player, ChatType.NAME);
                            player.sendMessage("Schreibe eine Naricht in den Chat um den Namen zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                            player.closeInventory();
                        }
                        if(itemName.equalsIgnoreCase("Priorität: " + area.priority)) {
                            chatPlayers.put(player, area);
                            chatPlayersType.put(player, ChatType.PRIORITY);
                            player.sendMessage("Schreibe eine Zahl in den Chat, um die Priorität zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                            player.closeInventory();
                        }
                        if (itemName.equalsIgnoreCase("Blöcke Zerstören")) {
                            area.breakBlocks = !area.breakBlocks;
                            DataHandler.saveArea(area);
                            //player.updateInventory();
                        }
                        if(itemName.equalsIgnoreCase("Zurück")) {
                            if(areaListInventory != null) {
                                areaListInventory.openInventory(player, 0);
                            } else {
                                AreaListInventory inventory = new AreaListInventory();
                                inventory.createPageData(player);
                                inventory.openInventory(player, 0);
                            }
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
                    ChatType type = chatPlayersType.get(player);
                    zArea area = chatPlayers.get(player);
                    boolean success = false;
                    switch (type) {
                        case NAME:
                            if(!Utility.areaExists(area.displayName)) {
                                area.displayName = event.getMessage();
                                success = true;
                            } else {
                                player.sendMessage("Gebietname exisitert bereits...");
                            }
                            break;
                        case PRIORITY:
                            if(Pattern.matches("^[0-9]+$", event.getMessage())) {
                                area.priority = Integer.parseInt(event.getMessage());
                                success = true;
                            } else {
                                player.sendMessage("Dies ist keine Zahl! Versuche es nochmal...");
                            }
                            break;
                        default:
                            //Sicherhaltshaber
                            success = true;
                    }

                    if(success) {
                        chatPlayers.remove(player);
                        chatPlayersType.remove(player);
                    }
                    DataHandler.saveArea(area);
                    Utility.reloadAreas();
                    openInventory(player);
                });
            } else {
                event.setCancelled(true);
                chatPlayers.remove(player);
                chatPlayersType.remove(player);
            }
        }
    }
}
