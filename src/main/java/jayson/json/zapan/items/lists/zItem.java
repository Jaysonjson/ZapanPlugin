package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.crafting.CopperIngotItem;
import jayson.json.zapan.items.crafting.HopItem;
import jayson.json.zapan.items.crafting.MaltItem;
import jayson.json.zapan.items.crafting.SilverIngotItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.ScrapItem;
import jayson.json.zapan.items.other.SkillBookItem;
import jayson.json.zapan.items.currency.ZoryhaShardItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;
import jayson.json.zapan.items.vanillaOverride.IronIngotItem;
import org.bukkit.Material;

public enum zItem implements IzItemRegistry {

    GOLDBAR(new GoldBarItem("goldBarItem", Material.GOLD_INGOT, ItemUseType.CURRENCY)),
    GOLDNUGGET(new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET, ItemUseType.CURRENCY)),
    HACKSILVER(new HackSilverItem("hackSilverItem", Material.IRON_NUGGET, ItemUseType.CURRENCY)),
    ZORYHASHARD(new ZoryhaShardItem("zoryhaShardItem", Material.NETHER_STAR, ItemUseType.CURRENCY)),
    SKILLBOOK(new SkillBookItem("skillBookItem", Material.WRITTEN_BOOK, ItemUseType.OTHER)),
    COPPERINGOT(new CopperIngotItem("copperIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING)),
    SILVERINGOT(new SilverIngotItem("silverIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING)),
    MALTITEM(new MaltItem("maltItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING)),
    HOPITEM(new HopItem("hopItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING)),
    IRONINGOT(new IronIngotItem("ironIngot", Material.IRON_INGOT, ItemUseType.CRAFTING)),
    SCRAP(new ScrapItem("scrapItem", Material.NETHERITE_SCRAP, ItemUseType.CURRENCY, 0.25));

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
