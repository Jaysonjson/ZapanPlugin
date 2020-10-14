package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ability.BombBeltItem;
import jayson.json.zapan.items.ability.FireEs01BlazeRodItem;
import jayson.json.zapan.items.ability.WallBlazeRodItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.BackPackItem;
import jayson.json.zapan.items.other.BackPackItemNBT;
import org.bukkit.Material;

public enum zItemAbility implements IzItemRegistry {

    BACKPACKITEM9(new BackPackItem("backPackItem9", Material.HEART_OF_THE_SEA, 9)),
    BACKPACKITEM18(new BackPackItem("backPackItem18", Material.HEART_OF_THE_SEA, 18)),
    BACKPACKITEM27(new BackPackItem("backPackItem27", Material.HEART_OF_THE_SEA, 27)),
    BACKPACKITEM36(new BackPackItem("backPackItem36", Material.HEART_OF_THE_SEA, 36)),
    BACKPACKITEM45(new BackPackItem("backPackItem45", Material.HEART_OF_THE_SEA, 45)),
    BACKPACKITEM54(new BackPackItem("backPackItem54", Material.HEART_OF_THE_SEA, 54)),
    BACKPACKITEMNBT9(new BackPackItemNBT("backPackItemNBT9", Material.HEART_OF_THE_SEA, 9)),
    FIREES01BLAZEROD(new FireEs01BlazeRodItem("fireEs01BlazeRodItem", Material.BLAZE_POWDER)),
    BOMBBELT(new BombBeltItem("bombBeltItem", Material.TNT)),
    WALLBLAZEROD(new WallBlazeRodItem("wallBlazeRodItem", Material.BLAZE_POWDER));

    AbstractItem item;
    zItemAbility(AbstractItem item) {
        this.item = item;
    }

    @Override
    public AbstractItem getAbstractItem() {
        return item;
    }

}
