package jayson.json.zapan.inventories;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.other.InventoryPage;
import jayson.json.zapan.other.InventoryPageContainer;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import java.util.Random;

public class ItemInventory implements Listener {
    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public ItemInventory() {
        Bukkit.getPluginManager().registerEvents(this, Zapan.INSTANCE);
    }

    public void createPageData(Player player) {
        Integer page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        int page_check = ItemRegistry.items.size();
        for (IzItemRegistry item : ItemRegistry.items) {
            page_index++;
            if (page_index < 46) {
                page_content.add(item.getAbstractItem().getItem(player));
            }
            if (page_index >= 46 || page_index.equals(ItemRegistry.items.size()) || page_index.equals(page_check)) {
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
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsCopy = Utility.createNMSCopy(clickedItem);
                    NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                    if(tag.hasKey(zItemNBT.CONST_ITEM_ID)) {
                        event.getView().setCursor(clickedItem);
                        event.setCancelled(true);
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Nächste Seite")) {
                        if (currentPage + 1 < pageContainer.size()) {
                            openInventory((Player) event.getWhoClicked(), currentPage + 1);
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Letzte Seite")) {
                        if (currentPage > 0) {
                            openInventory((Player) event.getWhoClicked(), currentPage - 1);
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
        for (ItemStack content : contents) {
            inventory.addItem(content);
        }
        for (int i = contents.length; i < 45; i++) {
            inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        }
        if (currentPage > 0) {
            inventory.addItem(differentItem(new ItemStack(Material.GREEN_WOOL), "Letzte Seite"));
        } else {
            inventory.addItem(differentItem(new ItemStack(Material.RED_WOOL), "Letzte Seite"));
        }
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.PAPER), "Derzeitige Seite: " + page));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        if (currentPage + 2 < pageContainer.size()) {
            inventory.addItem(differentItem(new ItemStack(Material.GREEN_WOOL), "Nächste Seite"));
        } else {
            inventory.addItem(differentItem(new ItemStack(Material.RED_WOOL), "Nächste Seite"));
        }
        player.openInventory(inventory);
    }

    private ItemStack differentItem(ItemStack stack, String displayName) {
        if(!displayName.equalsIgnoreCase("")) {
            ItemMeta itemMeta = stack.getItemMeta();
            itemMeta.setDisplayName(displayName);
            stack.setItemMeta(itemMeta);
        }
        net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_MOVE, false);
        tag.setInt("FAKENUMBER", new Random().nextInt(5000));
        tag.setInt("SCFAKENUMBER", new Random().nextInt(7000));
        nmsItem.setTag(tag);
        stack = CraftItemStack.asBukkitCopy(nmsItem);
        return stack;
    }

    private ItemStack differentItem(ItemStack stack) {
        return differentItem(stack, "");
    }
}
