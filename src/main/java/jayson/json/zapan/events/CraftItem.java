package jayson.json.zapan.events;

import jayson.json.zapan.items.lists.BannedItems;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.event.Event;
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
                    if(tag.hasKey(zItemNBT.CONST_CAN_CRAFT_MINECRAFT)) {
                        if (!tag.getBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT)) {
                            event.setCancelled(true);
                        }
                    }
                }
                for (BannedItems value : BannedItems.values()) {
                    if(value.getMaterial().equals(content.getType()) || value.getMaterial().equals(event.getRecipe().getResult().getType())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
