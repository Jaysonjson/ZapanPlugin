package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.ability.WallBlazeRodItem;
import jayson.json.zapan.items.interfaces.IzItem;

public enum zItemAbility {

    WALLBLAZEROD(new WallBlazeRodItem(), "wallBlazeRodItem");

    AbstractzItem item;
    String id;
    zItemAbility(AbstractzItem item, String id) {
        this.item = item;
        this.id = id;
    }

    @Deprecated
    public IzItem getzItem() {
        return item;
    }

    public AbstractzItem getAbstractItem() {
        return item;
    }

    public String getId() {
        return id;
    }
}
