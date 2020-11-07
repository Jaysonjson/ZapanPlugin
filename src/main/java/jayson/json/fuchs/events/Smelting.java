package jayson.json.fuchs.events;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.items.FuchsItem;
import jayson.json.fuchs.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Smelting implements Listener {

    @EventHandler
    public void Smelting(FurnaceSmeltEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        Inventory inventory = furnace.getInventory();
        ItemStack itemStack = event.getResult();
        ItemStack existingItem = inventory.getItem(2);
        NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
        boolean override = false;
        if(!tag.hasKey(zItemNBT.ITEM_ID)) {
            if (Utility.isAbstractVanillaItem(itemStack)) {
                override = true;
                itemStack = Utility.getAbstractVanillaOverride(itemStack).createItem(null, null, null);
                inventory.setItem(2, itemStack);
            }
        }

        int amount = Utility.addAmount(Utility.getAbstractVanillaOverride(itemStack).createItem(null, itemStack, null), existingItem);

        if(override) {
                /*ItemStack item = Utility.getAbstractVanillaOverride(itemStack).createItem(null, itemStack, null);

                net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(item);
                NBTTagCompound tagI = nmsStack.getTag();
                tagI.setInt(zItemNBT.ITEM_AMOUNT, amount);
                nmsStack.setTag(tagI);
                item = CraftItemStack.asBukkitCopy(nmsStack);
                item = Utility.getAbstractVanillaOverride(itemStack).createItem(item);
                item.setAmount(1);
                //event.setResult(item);
                 */
        	event.getSource().setAmount(event.getSource().getAmount() - 1);
        	FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractVanillaOverride(itemStack), itemStack);
            fuchsItem.changeIntTag(zItemNBT.ITEM_AMOUNT, amount);
        	fuchsItem.setAmount(1);
        	inventory.setItem(2, fuchsItem.getItemStack());
        }

    }
}

