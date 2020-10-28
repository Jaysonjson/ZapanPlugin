package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.items.lists.BannedItems;
import jayson.json.zapan.items.zAdditionalItemInformation;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Smelting implements Listener {

    //@EventHandler
    public void Smelting(FurnaceSmeltEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        Inventory inventory = furnace.getInventory();
        ItemStack itemStack = event.getResult();
        ItemStack existingItem = inventory.getItem(2);
        NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
        System.out.println(Utility.getItemTag(existingItem).getInt(zItemNBT.ITEM_AMOUNT));
        int amount = Utility.addAmount(Utility.getAbstractVanillaOverride(itemStack).createItem(null, null, null), existingItem);
        zAdditionalItemInformation infoItem = new zAdditionalItemInformation();
        infoItem.amount = amount;
        System.out.println(infoItem.amount);
        if (Utility.isAbstractItem(itemStack)) {
            ItemStack item = Utility.getAbstractItemFromNMS(itemStack).createItem(null, itemStack, infoItem);
            item.setAmount(1);
            event.setResult(item);
            //inventory.setItem(2, item);
        }
        if(!tag.hasKey(zItemNBT.ITEM_ID)) {
            if (Utility.isAbstractVanillaItem(itemStack)) {
                ItemStack item = Utility.getAbstractVanillaOverride(itemStack).createItem(null, null, infoItem);
                item.setAmount(1);
                event.setResult(item);
                //inventory.setItem(2, item);
            }
        }
        /*
        for (HumanEntity viewer : inventory.getViewers()) {
            Player player = (Player) viewer;
            player.updateInventory();
        }
        */
    }
}

