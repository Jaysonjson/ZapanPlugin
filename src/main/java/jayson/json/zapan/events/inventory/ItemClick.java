package jayson.json.zapan.events.inventory;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        int clickedSlot = event.getRawSlot();
        if(Utility.isTopInventory(event)) {
        if(itemStack != null) {
            if (itemStack.hasItemMeta()) {
                net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
                if (nmsItem.hasTag()) {
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if (tag.hasKey(zItemNBT.CONST_CAN_MOVE)) {
                        if (!tag.getBoolean(zItemNBT.CONST_CAN_MOVE)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
                if (Utility.isAbstractItem(itemStack)) {
                    System.out.println("Item geändert -> Click");
                    ItemStack item = Utility.getAbstractItemFromNMS(itemStack).getItem(player);
                    item.setAmount(itemStack.getAmount());
                    event.getInventory().setItem(clickedSlot, item);
                }
                if (!tag.hasKey(zItemNBT.CONST_ITEM_ID)) {
                    if (Utility.isAbstractVanillaItem(itemStack)) {
                        System.out.println("Vanilla Item geändert -> Click");
                        ItemStack item = Utility.getAbstractVanillaOverride(itemStack).getItem(player);
                        item.setAmount(itemStack.getAmount());
                        event.getInventory().setItem(clickedSlot, item);
                        player.updateInventory();
                    }
                }
            }
        }
        }
    }
}
