package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemDespawn implements Listener {
    @EventHandler
    public void ItemDespawn(ItemDespawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        if(item.hasItemMeta()) {
            NBTTagCompound tag = Utility.getItemTag(item);
            if(tag.hasKey(zItemNBT.IS_BACKPACK) && tag.hasKey(zItemNBT.ITEM_UUID)) {
                DataHandler.deleteBackPack(UUID.fromString(tag.getString(zItemNBT.ITEM_UUID)));
            }
        }
    }
}