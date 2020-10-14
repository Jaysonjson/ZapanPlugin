package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.ScrapItem;
import jayson.json.zapan.items.other.SkillBookItem;
import jayson.json.zapan.items.currency.ZoryhaShardItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;
import org.bukkit.Material;

public enum zItem implements IzItemRegistry {

    GOLDBAR(new GoldBarItem("goldBarItem", Material.GOLD_INGOT)),
    GOLDNUGGET(new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET)),
    HACKSILVER(new HackSilverItem("hackSilverItem", Material.IRON_NUGGET)),
    ZORYHASHARD(new ZoryhaShardItem("zoryhaShardItem", Material.NETHER_STAR)),
    SKILLBOOK(new SkillBookItem("skillBookItem", Material.WRITTEN_BOOK)),
    SCRAP(new ScrapItem("scrapItem", Material.NETHERITE_SCRAP, 0.25));

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
