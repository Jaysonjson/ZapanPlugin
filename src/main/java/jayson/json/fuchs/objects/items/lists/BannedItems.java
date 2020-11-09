package jayson.json.fuchs.objects.items.lists;

import org.bukkit.Material;

public enum BannedItems {

	NETHERITE_HOE(Material.NETHERITE_HOE);

    Material material;
    BannedItems(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
