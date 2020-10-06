package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.ability.WallBlazeRodItem;
import jayson.json.zapan.items.interfaces.IzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;

public enum zItemAbility implements IzItemRegistry {

    WALLBLAZEROD(new WallBlazeRodItem(), "wallBlazeRodItem");

    AbstractzItem item;
    String id;
    zItemAbility(AbstractzItem item, String id) {
        this.item = item;
        this.id = id;
    }

    @Override
    public AbstractzItem getAbstractItem() {
        return item;
    }

    @Override
    public String getId() {
        return id;
    }
}
