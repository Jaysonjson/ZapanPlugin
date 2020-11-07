package jayson.json.fuchs.inventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemInventoryData {
    int slot = 0;
    Inventory inventory;
    ItemStack itemStack;

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
