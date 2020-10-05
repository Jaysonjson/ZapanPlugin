package jayson.json.zapan.items;

import jayson.json.zapan.items.currency.EmeraldItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;

public enum zItem {

    GOLDBAR(new GoldBarItem(), "goldBarItem"),
    GOLDNUGGET(new GoldNuggetItem(), "goldNuggetItem"),
    HACKSILVER(new HackSilverItem(), "hackSilverItem"),
    EMERALD(new EmeraldItem(), "emeraldItem"),
    SKILLBOOK(new SkillBookItem(), "skillBook"),
    SCRAP(new ScrapItem(), "scrapItem");

    //TODO: Market-Value -> Wert des Items verändert sich
    IzItem item;
    String id;
    zItem(IzItem item, String id) {
        this.item = item;
        this.id = id;
    }

    public IzItem getzItem() {
        return item;
    }

    public String getId() {
        return id;
    }
}
