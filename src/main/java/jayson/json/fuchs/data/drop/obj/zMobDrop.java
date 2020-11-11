package jayson.json.fuchs.data.drop.obj;

import jayson.json.fuchs.objects.items.AbstractItem;
import org.bukkit.entity.EntityType;
import java.util.HashMap;

public class zMobDrop {
    public EntityType type = EntityType.BOAT;
    public HashMap<String, Integer> itemDropsID = new HashMap<>();
    public transient HashMap<AbstractItem, Integer> itemDrops = new HashMap<>();
}
