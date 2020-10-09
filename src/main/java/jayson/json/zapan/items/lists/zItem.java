package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.ScrapItem;
import jayson.json.zapan.items.other.SkillBookItem;
import jayson.json.zapan.items.currency.EmeraldItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;

public enum zItem implements IzItemRegistry {

    GOLDBAR(new GoldBarItem("goldBarItem")),
    GOLDNUGGET(new GoldNuggetItem("goldNuggetItem")),
    HACKSILVER(new HackSilverItem("hackSilverItem")),
    EMERALD(new EmeraldItem("emeraldItem")),
    SKILLBOOK(new SkillBookItem("skillBookItem")),
    SCRAP(new ScrapItem("scrapItem", 0.25));

    //TODO: Market-Value -> Wert des Items verändert sich
    AbstractItem item;
    zItem(AbstractItem item) {
        this.item = item;
    }

    @Override
    public AbstractItem getAbstractItem() {
        return item;
    }
}
