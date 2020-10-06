package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.ability.FireEs01BlazeRodItem;
import jayson.json.zapan.items.ability.WallBlazeRodItem;
import jayson.json.zapan.items.interfaces.IzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;

public enum zItemAbility implements IzItemRegistry {

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
