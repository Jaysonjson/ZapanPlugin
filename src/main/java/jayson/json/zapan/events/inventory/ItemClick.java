package jayson.json.zapan.events.inventory;

import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase("Items")) {
            if(event.getCurrentItem().hasItemMeta()) {
                net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getCurrentItem());
                if(nmsItem.hasTag()) {
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if (tag.hasKey(zItemNBT.CONST_CAN_MOVE)) {
                        if (!tag.getBoolean(zItemNBT.CONST_CAN_MOVE)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
