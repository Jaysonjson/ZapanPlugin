package jayson.json.zapan.data;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.UUID;

public class zBackPack {
    public String inventoryContent = "";
    public UUID uuid = UUID.randomUUID();


    public String createInventoryContent(ItemStack[] itemStacks) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(itemStacks.length);
            for (ItemStack itemStack : itemStacks) {
                dataOutput.writeObject(itemStack);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return "";
    }

    public static ItemStack[] generateInventoryContent(String inventoryContent) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inventoryContent));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }
            dataInput.close();
            return items;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return new ItemStack[0];
    }

    public UUID getUuid() {
        return uuid;
    }

    public ItemStack[] getItemStacks() {
        return generateInventoryContent(inventoryContent);
    }

    public void setItemStacks(ItemStack[] itemStacks) {
        this.inventoryContent = createInventoryContent(itemStacks);
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
