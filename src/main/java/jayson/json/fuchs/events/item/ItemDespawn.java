package jayson.json.fuchs.events.item;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.objects.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemDespawn implements Listener {
    @EventHandler
    public void itemDespawnEvent(ItemDespawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        if(item.hasItemMeta()) {
            NBTTagCompound tag = Utility.getItemTag(item);
            if(tag.hasKey(zItemNBT.IS_BACKPACK) && tag.hasKey(zItemNBT.ITEM_UUID)) {
                DataHandler.deleteBackPack(UUID.fromString(tag.getString(zItemNBT.ITEM_UUID)));
            }
        }
    }
}
