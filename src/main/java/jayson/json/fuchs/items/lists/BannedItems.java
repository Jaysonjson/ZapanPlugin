package jayson.json.fuchs.items.lists;

import org.bukkit.Material;

public enum BannedItems {

    NETHERRITEHOE(Material.NETHERITE_HOE);

    Material material;
    BannedItems(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
