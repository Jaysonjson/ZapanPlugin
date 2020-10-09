package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.ability.FireEs01BlazeRodItem;
import jayson.json.zapan.items.ability.WallBlazeRodItem;
import jayson.json.zapan.items.interfaces.IzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.BackPackItem;

public enum zItemAbility implements IzItemRegistry {

    BACKPACKITEM9(new BackPackItem("backPackItem9", 9)),
    BACKPACKITEM18(new BackPackItem("backPackItem18", 18)),
    BACKPACKITEM27(new BackPackItem("backPackItem27", 27)),
    BACKPACKITEM36(new BackPackItem("backPackItem36", 36)),
    BACKPACKITEM45(new BackPackItem("backPackItem45", 45)),
    BACKPACKITEM54(new BackPackItem("backPackItem54", 54)),
    FIREES01BLAZEROD(new FireEs01BlazeRodItem("fireEs01BlazeRodItem")),
    WALLBLAZEROD(new WallBlazeRodItem("wallBlazeRodItem"));

    AbstractzItem item;
    zItemAbility(AbstractzItem item) {
        this.item = item;
    }

    @Override
    public AbstractzItem getAbstractItem() {
        return item;
    }

}
