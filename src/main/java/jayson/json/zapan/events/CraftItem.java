package jayson.json.zapan.events;

import jayson.json.zapan.items.zItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {
    @EventHandler
    public void CraftItem(CraftItemEvent event) {
        for (ItemStack content : event.getInventory().getContents()) {
            if(content != null) {
                net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(content);
                if(nmsItem.hasTag()) {
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if(tag.hasKey(zItem.CONST_CAN_CRAFT_MINECRAFT)) {
                        if (!tag.getBoolean(zItem.CONST_CAN_CRAFT_MINECRAFT)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
