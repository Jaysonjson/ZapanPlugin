package jayson.json.fuchs.items.lists;

import jayson.json.fuchs.items.AbstractItem;
import jayson.json.fuchs.items.ItemUseType;
import jayson.json.fuchs.items.ability.FireEs01BlazeRodItem;
import jayson.json.fuchs.items.ability.WallBlazeRodItem;
import jayson.json.fuchs.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.items.other.BackPackItem;
import org.bukkit.Material;

public enum zItemAbility implements IzItemRegistry {

    BACKPACKITEM9(new BackPackItem("backPackItem9", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 9)),
    BACKPACKITEM18(new BackPackItem("backPackItem18", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 18)),
    BACKPACKITEM27(new BackPackItem("backPackItem27", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 27)),
    BACKPACKITEM36(new BackPackItem("backPackItem36", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 36)),
    BACKPACKITEM45(new BackPackItem("backPackItem45", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 45)),
    BACKPACKITEM54(new BackPackItem("backPackItem54", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 54)),
    //BACKPACKITEMNBT9(new BackPackItemNBT("backPackItemNBT9", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 9)),
    FIREES01BLAZEROD(new FireEs01BlazeRodItem("fireEs01BlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY)),
    //BOMBBELT(new BombBeltItem("bombBeltItem", Material.TNT, ItemUseType.ABILITY)),
    WALLBLAZEROD(new WallBlazeRodItem("wallBlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY));

    AbstractItem item;
    zItemAbility(AbstractItem item) {
        this.item = item;
    }

    @Override
    public AbstractItem getAbstractItem() {
        return item;
    }

}
