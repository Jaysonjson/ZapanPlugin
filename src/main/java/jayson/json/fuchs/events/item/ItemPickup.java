package jayson.json.fuchs.events.item;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickup implements Listener {

    @EventHandler
    public void itemPickupEvent(EntityPickupItemEvent event) {
        ItemStack itemStack = event.getItem().getItemStack();
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
            if (Utility.isAbstractItem(itemStack)) {
                System.out.println("Item geändert -> Pickup");
                ItemStack item = Utility.getAbstractItemFromNMS(itemStack).createItem(player, itemStack);
                item.setAmount(itemStack.getAmount());
                event.getItem().setItemStack(item);
            }
            if(!tag.hasKey(zItemNBT.ITEM_ID)) {
                if (Utility.isAbstractVanillaItem(itemStack)) {
                    System.out.println("Vanilla Item geändert -> Pickup");
                    ItemStack item = Utility.getAbstractVanillaOverride(itemStack).createItem(player);
                    item.setAmount(itemStack.getAmount());
                    event.getItem().setItemStack(item);
                }
            }
        }
    }
}


