package jayson.json.fuchs.inventories.area;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import jayson.json.fuchs.data.area.data.zArea;
import jayson.json.fuchs.data.io.DataHandler;
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
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        if(area != null) {
            inventory = Bukkit.createInventory(player, 54, area.getDisplayName());
            setContents(player);
            player.openInventory(inventory);
        }
    }

    public void setContents(Player player) {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(10, Utility.createInventoryStack(Material.PAPER, 1, area.getDisplayName()));
        int prioItemAmount = area.getPriority();;
        if(area.getPriority() < 1) {
            prioItemAmount = 1;
        } else if(area.getPriority() > 64) {
            prioItemAmount = 64;
        }
        
        ArrayList<String> locationLore = new ArrayList<>();
        locationLore.add("x: " + area.getLocation().x);
        locationLore.add("y: " + area.getLocation().y);
        locationLore.add("z: " + area.getLocation().z);
        locationLore.add("distanz: " + player.getLocation().distance(area.createLocation(player.getWorld())));
        
        inventory.setItem(11, Utility.createInventoryStack(Material.SLIME_BALL, prioItemAmount,"Priorität: " + area.getPriority()));
        inventory.setItem(12, Utility.createInventoryStack(Material.PAPER, 1, "Lage", locationLore));
        inventory.setItem(19, Utility.createInventoryWoolColor(area.isBreakBlocks(), "Blöcke Zerstören", 1));
        inventory.setItem(20, Utility.createInventoryWoolColor(area.isPlaceBlocks(), "Blöcke Platzieren", 1));
        inventory.setItem(21, Utility.createInventoryWoolColor(area.isSpawnMobs(), "Spawn Mobs", 1));
        inventory.setItem(22, Utility.createInventoryWoolColor(area.isAllowOverlap(), "Überlappung Erlauben", 1));
        inventory.setItem(23, Utility.createInventoryWoolColor(area.isAllowOwnerOverlap(), "Besitzer Überlappung Erlauben", 1));
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
                        if (itemName.equalsIgnoreCase(area.getDisplayName())) {
                            chatPlayers.put(player, area);
                            chatPlayersType.put(player, ChatType.NAME);
                            player.sendMessage("Schreibe eine Naricht in den Chat um den Namen zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                            player.closeInventory();
                        }
                        if(itemName.equalsIgnoreCase("Priorität: " + area.getPriority())) {
                            chatPlayers.put(player, area);
                            chatPlayersType.put(player, ChatType.PRIORITY);
                            player.sendMessage("Schreibe eine Zahl in den Chat, um die Priorität zu ändern. Schreibe \"Abbrechen\" um Abzubrechen");
                            player.closeInventory();
                        }

          
                        switch(itemName) {
                        	case "Blöcke Zerstören":
                                area.setBreakBlocks(!area.isBreakBlocks());
                        		break;
                        	case "Blöcke Platzieren":
                                area.setPlaceBlocks(!area.isPlaceBlocks());
                        		break;
                        	case "Spawn Mobs":
                                area.setSpawnMobs(!area.isSpawnMobs());
                        		break;
                        	case "Überlappung Erlauben":
                        		area.setAllowOverlap(!area.isAllowOverlap());
                        		break;
                        	case "Besitzer Überlappung Erlauben":
                        		area.setAllowOwnerOverlap(!area.isAllowOwnerOverlap());
                        		break;
                        }
                        
                        DataHandler.saveArea(area);
                        
                        if(itemName.equalsIgnoreCase("Zurück")) {
                            if(areaListInventory != null) {
                                areaListInventory.openInventory(player, 0);
                            } else {
                                AreaListInventory inventory = new AreaListInventory();
                                inventory.createPageData(player);
                                inventory.openInventory(player, 0);
                            }
                        }
                        setContents((Player) event.getWhoClicked());
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
                Bukkit.getScheduler().runTask(Fuchs.INSTANCE, () -> {
                    ChatType type = chatPlayersType.get(player);
                    zArea area = chatPlayers.get(player);
                    boolean success = false;
                    switch (type) {
                        case NAME:
                            if(!Utility.areaExists(area.getDisplayName())) {
                                area.setDisplayName(event.getMessage());
                                success = true;
                            } else {
                                player.sendMessage("Gebietname exisitert bereits...");
                            }
                            break;
                        case PRIORITY:
                            if(Pattern.matches("^[0-9]+$", event.getMessage())) {
                                area.setPriority(Integer.parseInt(event.getMessage()));
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
