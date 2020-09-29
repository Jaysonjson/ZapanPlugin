package jayson.json.zapan.items;

import jayson.json.zapan.items.currency.GoldBarItem;

public enum zItem {

    GOLDBAR(new GoldBarItem(), "goldBar"),
    SCRAP(new ScrapItem(), "scrapItem");


    IzItem item;
    String id;
    zItem(IzItem item, String id) {

    }

    public IzItem getItem() {
        return item;
    }

    public String getId() {
        return id;
    }
}
