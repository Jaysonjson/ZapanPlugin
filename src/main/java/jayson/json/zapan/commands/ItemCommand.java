package jayson.json.zapan.commands;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.items.lists.zItem;
import jayson.json.zapan.items.lists.zItemAbility;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.other.InventoryPage;
import jayson.json.zapan.other.InventoryPageContainer;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if(player.isOp()) {
            Inventory gui = Bukkit.createInventory(player, 54, "Items");

            InventoryPageContainer<ArrayList<ItemStack>> pageContainer = new InventoryPageContainer<>();
            Integer page_index = 0;
            Integer page = 0;
            ArrayList<ItemStack> page_content = new ArrayList<>();
            int page_check = zItem.values().length;
            for (IzItemRegistry item : ItemRegistry.items) {
                page_index++;
                if (page_index < 44) {
                    page_content.add(item.getAbstractItem().getItem(player));
                }
                if (page_index >= 44 || page_index.equals(ItemRegistry.items.size()) || page_index.equals(page_check)) {
                    page++;
                    page_check -= 45;
                    pageContainer.addPage(new InventoryPage<>(page_content, page));
                    //page_content.clear();
                    page_index = 0;
                }
            }
            createPage(gui, pageContainer, 0);


            //SortedSet<InventoryItem> keys = new TreeSet<>(References.inventory.keySet());


            player.openInventory(gui);
            return true;
        }
        return false;
    }

    private void createPage(Inventory inventory, InventoryPageContainer<ArrayList<ItemStack>> pageContainer, int page) {
        ArrayList<ItemStack> contents = pageContainer.getPage(page).getContent();
        for (ItemStack content : contents) {
            inventory.addItem(content);
        }
        for (int i = contents.size(); i < 45; i++) {
            inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        }
        inventory.addItem(differentItem(new ItemStack(Material.GREEN_WOOL), "Letzte Seite"));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.PAPER), "Derzeitige Seite: " + page));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        inventory.addItem(differentItem(new ItemStack(Material.GLASS_PANE)));
        if (page < pageContainer.size()) {
            inventory.addItem(differentItem(new ItemStack(Material.GREEN_WOOL), "Nächste Seite"));
        } else {
            inventory.addItem(differentItem(new ItemStack(Material.RED_WOOL), "Letzte Seite"));
        }
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
