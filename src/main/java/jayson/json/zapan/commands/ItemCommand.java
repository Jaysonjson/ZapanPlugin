package jayson.json.zapan.commands;

import jayson.json.zapan.data.zArea;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItem;
import jayson.json.zapan.other.InventoryPage;
import jayson.json.zapan.other.InventoryPageContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        Inventory gui = Bukkit.createInventory(player, 54, "Items");

        InventoryPageContainer pageContainer = new InventoryPageContainer();
        Integer p_i = 0;
        Integer page = 0;
        StringBuilder page_content = new StringBuilder();
        int current_page = 0;
        int page_check = zItem.values().length;

        for (zItem value : zItem.values()) {
            p_i++;
            if (p_i <= 45) {
                page_content.append(value.getId());
            }
            if (p_i >= 44 || p_i.equals(zItem.values().length) || p_i.equals(page_check)) {
                page++;
                page_check -= 5;
                pageContainer.addPage(new InventoryPage(page_content.toString(), page));
                page_content = new StringBuilder();
                p_i = 0;
            }

            ItemStack itemStack = value.getzItem().getItem();
            gui.addItem(itemStack);
        }

        gui.addItem(new ItemStack(Material.GREEN_WOOL));
        gui.addItem(new ItemStack(Material.RED_WOOL));

        //SortedSet<InventoryItem> keys = new TreeSet<>(References.inventory.keySet());

        if (current_page > pageContainer.size()) {

        }



        player.openInventory(gui);
        return false;
    }
}
