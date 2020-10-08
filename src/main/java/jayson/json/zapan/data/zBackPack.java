package jayson.json.zapan.data;

import jayson.json.zapan.Utility;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class zBackPack {
    public String inventoryContent = "";
    public UUID uuid = UUID.randomUUID();

    public UUID getUuid() {
        return uuid;
    }

    public ItemStack[] getItemStacks() {
        return Utility.generateInventoryContent(inventoryContent);
    }

    public void setItemStacks(ItemStack[] itemStacks) {
        this.inventoryContent = Utility.createInventoryContent(itemStacks);
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
