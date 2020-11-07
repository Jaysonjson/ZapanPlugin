package jayson.json.fuchs.events.inventory;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
                        if (tag.hasKey(zItemNBT.CAN_MOVE)) {
                            if (!tag.getBoolean(zItemNBT.CAN_MOVE)) {
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
                        ItemStack item = Utility.getAbstractItemFromNMS(itemStack).createItem(player, itemStack, null);
                        item.setAmount(itemStack.getAmount());
                        event.getInventory().setItem(clickedSlot, item);
                    }
                    if (!tag.hasKey(zItemNBT.ITEM_ID) || tag.getDouble(zItemNBT.ITEM_VERSION) != 404) {
                    	if (Utility.isAbstractVanillaItem(itemStack)) {
                    		System.out.println("Vanilla Item geändert -> Click");
                        	ItemStack item = Utility.getAbstractVanillaOverride(itemStack).createItem(player, null, null);
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
