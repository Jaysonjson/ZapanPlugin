package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.ScrapItem;
import jayson.json.zapan.items.other.SkillBookItem;
import jayson.json.zapan.items.currency.EmeraldItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;
import jayson.json.zapan.items.interfaces.IzItem;

public enum zItem implements IzItemRegistry {

    GOLDBAR(new GoldBarItem(), "goldBarItem"),
    GOLDNUGGET(new GoldNuggetItem(), "goldNuggetItem"),
    HACKSILVER(new HackSilverItem(), "hackSilverItem"),
    EMERALD(new EmeraldItem(), "emeraldItem"),
    SKILLBOOK(new SkillBookItem(), "skillBookItem"),
    SCRAP(new ScrapItem(), "scrapItem");

    //TODO: Market-Value -> Wert des Items verändert sich
    AbstractzItem item;
    String id;
    zItem(AbstractzItem item, String id) {
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
