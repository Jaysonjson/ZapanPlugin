package jayson.json.zapan.inventories;


import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.other.InventoryPage;
import jayson.json.zapan.other.InventoryPageContainer;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBamboo;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class AreaListInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public AreaListInventory() {
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void createPageData(Player player) {
        Integer page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        int page_check = Zapan.INSTANCE.areas.size();
        for (zArea area : Zapan.INSTANCE.areas) {
            page_index++;
            if (page_index < 46) {
                ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
                ItemMeta itemMeta = itemStack.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + area.uuid + "«");
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(area.displayName);
                itemStack.setItemMeta(itemMeta);
                net.minecraft.server.v1_16_R2.ItemStack nmsCopy = Utility.createNMSCopy(itemStack);
                NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                tag.setString(zItemNBT.ITEM_UUID, area.uuid.toString());
                tag.setInt("areaItemID_LIST", 0);
                itemStack = CraftItemStack.asBukkitCopy(nmsCopy);
                page_content.add(itemStack);
            }
            if (page_index >= 46 || page_index.equals(ItemRegistry.items.size()) || page_index.equals(page_check)) {
                page_content.add(Utility.createInventoryStack(Material.EMERALD, 1, "Neues Gebiet"));
                page++;
                page_check -= 46;
                InventoryPage<ArrayList<ItemStack>> pageInv = new InventoryPage<>(page_content, page);
                pageInv.stacks = page_content.toArray(new ItemStack[0]);
                pageContainer.addPage(pageInv);
                page_content.clear();
                page_index = 0;
            }
        }
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(this.inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                Player player = (Player) event.getWhoClicked();
                if (clickedItem.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsCopy = Utility.createNMSCopy(clickedItem);
                    NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                    if(tag.hasKey(zItemNBT.ITEM_UUID) && tag.hasKey("areaItemID_LIST")) {
                        AreaInventory areaInventory = new AreaInventory(DataHandler.loadArea(UUID.fromString(tag.getString(zItemNBT.ITEM_UUID))), this);
                        areaInventory.openInventory(player);
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Nächste Seite")) {
                        if (currentPage + 1 < pageContainer.size()) {
                            openInventory(player, currentPage + 1);
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Letzte Seite")) {
                        if (currentPage > 0) {
                            openInventory(player, currentPage - 1);
                        }
                    }
                }
            }
        }
    }

    public void openInventory(Player player, int page) {
        inventory = Bukkit.createInventory(player, 54, "Items");
        currentPage = page;
        createPage(player, inventory, page);
    }

    private void createPage(Player player, Inventory inventory, int page) {
        inventory.clear();
        ItemStack[] contents = pageContainer.getPage(page).getStacks();
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        int i = 0;
        for (ItemStack content : contents) {
            inventory.setItem(i, content);
            i++;
        }
        if (currentPage > 0) {
            inventory.setItem(45, Utility.createInventoryStack(Material.GREEN_WOOL, 1, "Letzte Seite"));
        } else {
            inventory.setItem(45, Utility.createInventoryStack(Material.RED_WOOL, 1, "Letzte Seite"));
        }
        inventory.setItem(49, Utility.createInventoryStack(Material.PAPER, 1, "Derzeitige Seite"));
        if (currentPage + 2 < pageContainer.size()) {
            inventory.setItem(53, Utility.createInventoryStack(Material.GREEN_WOOL, 1, "Nächste Seite"));
        } else {
            inventory.setItem(53, Utility.createInventoryStack(Material.RED_WOOL, 1, "Nächste Seite"));
        }
        player.openInventory(inventory);
    }
}
