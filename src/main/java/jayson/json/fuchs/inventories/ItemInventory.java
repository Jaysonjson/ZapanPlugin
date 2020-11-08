package jayson.json.fuchs.inventories;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import jayson.json.fuchs.objects.items.FuchsItem;
import jayson.json.fuchs.objects.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.objects.items.lists.zItem;
import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;
import jayson.json.fuchs.objects.zRegistry;
import jayson.json.fuchs.objects.gas.interfaces.IzGasRegistry;
import jayson.json.fuchs.objects.items.zItemNBT;
import jayson.json.fuchs.other.InventoryPage;
import jayson.json.fuchs.other.InventoryPageContainer;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemInventory implements Listener {
    @Nullable
    private Inventory inventory = null;
    public InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
    public int currentPage = 0;
    public ItemInventory() {
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }

    public void createPageData(Player player) {
        Integer page_index = 0;
        Integer page = 0;
        ArrayList<ItemStack> page_content = new ArrayList<>();
        ArrayList<ItemStack> stacks = getStacks(player);
        int page_check = stacks.size();
        for (ItemStack item : stacks) {
            page_index++;
            if (page_index < 46) {
                page_content.add(item);
            }
            if (page_index >= 46 || page_index.equals(stacks.size()) || page_index.equals(page_check)) {
                page++;
                page_check -= 46;
                InventoryPage<ArrayList<ItemStack>> pageInv = new InventoryPage<>(page_content, page);
                pageInv.stacks = page_content.toArray(new ItemStack[0]);
                System.out.println(Arrays.toString(pageInv.stacks));
                pageContainer.addPage(pageInv);
                page_content.clear();
                page_index = 0;
            }
        }
    }

    public ArrayList<ItemStack> getStacks(Player player) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        for (IzItemRegistry item : zRegistry.items) {
            itemStacks.add(item.getAbstractItem().createItem(player));
        }

        for (IzLiquidRegistry liquid : zRegistry.liquids) {
            ItemStack stack = zItem.LIQUIDCONTAINER.getAbstractItem().createItem();
            FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(stack), stack);
            fuchsItem.changeStringTag(zItemNBT.CONTAINED_LIQUID, liquid.getLiquid().getId());
            fuchsItem.changeDoubleTag(zItemNBT.LIQUID_AMOUNT, 500d);
            itemStacks.add(fuchsItem.getItemStack());
        }
        
        for (IzGasRegistry gas : zRegistry.gasses) {
            ItemStack stack = zItem.GASCONTAINER.getAbstractItem().createItem();
            FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(stack), stack);
            fuchsItem.changeStringTag(zItemNBT.CONTAINED_GAS, gas.getGas().getId());
            fuchsItem.changeDoubleTag(zItemNBT.GAS_AMOUNT, 500d);
            itemStacks.add(fuchsItem.getItemStack());	
        }
        
        return itemStacks;
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(this.inventory) && Utility.isTopInventory(event)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsCopy = Utility.createNMSCopy(clickedItem);
                    NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                    if(tag.hasKey(zItemNBT.ITEM_ID) && event.getWhoClicked().isOp()) {
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
/*
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
*/

}
