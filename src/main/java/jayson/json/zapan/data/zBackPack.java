package jayson.json.zapan.data;

import jayson.json.zapan.Utility;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class zBackPack implements IHasUUID {
    public String inventoryContent = "";
    public UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public ItemStack[] getItemStacks() {
        return Utility.generateInventoryContent(inventoryContent);
    }

    public void setItemStacks(ItemStack[] itemStacks) {
        this.inventoryContent = Utility.createInventoryContent(itemStacks);
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return null;
    }
}
