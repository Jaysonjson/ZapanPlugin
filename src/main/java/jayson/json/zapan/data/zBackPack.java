package jayson.json.zapan.data;

import jayson.json.zapan.Utility;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class zBackPack implements IHasUUID {
    public String inventoryContent = "rO0ABXcEAAAACXBwcHBzcgAab3JnLmJ1a2tpdC51dGlsLmlvLldyYXBwZXLyUEfs8RJvBQIAAUwA\\r\\nA21hcHQAD0xqYXZhL3V0aWwvTWFwO3hwc3IANWNvbS5nb29nbGUuY29tbW9uLmNvbGxlY3QuSW1t\\r\\ndXRhYmxlTWFwJFNlcmlhbGl6ZWRGb3JtAAAAAAAAAAACAAJbAARrZXlzdAATW0xqYXZhL2xhbmcv\\r\\nT2JqZWN0O1sABnZhbHVlc3EAfgAEeHB1cgATW0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAA\\r\\neHAAAAADdAACPT10AAF2dAAEdHlwZXVxAH4ABgAAAAN0AB5vcmcuYnVra2l0LmludmVudG9yeS5J\\r\\ndGVtU3RhY2tzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEu\\r\\nbGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAKFHQADFJPVFRFTl9GTEVTSHBwcHA\\u003d\\r\\n";
    public UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public ItemStack[] getItemStacks() {
        return Utility.generateInventoryContent(inventoryContent);
    }

    public void setItemStacks(ItemStack[] itemStacks) {
        this.inventoryContent = Utility.createInventoryContent(itemStacks);
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUuid() {
        return null;
    }
}
