package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.crafting.*;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.other.ScrapItem;
import jayson.json.zapan.items.other.SkillBookItem;
import jayson.json.zapan.items.currency.ZoryhaShardItem;
import jayson.json.zapan.items.currency.GoldBarItem;
import jayson.json.zapan.items.currency.GoldNuggetItem;
import jayson.json.zapan.items.currency.HackSilverItem;
import jayson.json.zapan.items.vanillaOverride.IronIngotItem;
import jayson.json.zapan.items.vanillaOverride.IronOreItem;
import org.bukkit.Material;

public enum zItem implements IzItemRegistry {

    GOLDBAR(new GoldBarItem("goldBarItem", Material.GOLD_INGOT, ItemUseType.CURRENCY)),
    GOLDNUGGET(new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET, ItemUseType.CURRENCY)),
    HACKSILVER(new HackSilverItem("hackSilverItem", Material.IRON_NUGGET, ItemUseType.CURRENCY)),
    ZORYHASHARD(new ZoryhaShardItem("zoryhaShardItem", Material.NETHER_STAR, ItemUseType.CURRENCY)),
    SKILLBOOK(new SkillBookItem("skillBookItem", Material.WRITTEN_BOOK, ItemUseType.OTHER)),
    COPPERINGOT(new CopperIngotItem("copperIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 1)),
    COPPERROD(new CopperRodItem("copperRodItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 5)),
    COPPERPIPE(new CopperPipeItem("copperPipeItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 6)),
    COPPERLIQUID(new LiquidCopperItem("copperLiquidItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 6)),
    COPPERSWORD(new CopperSwordItem("copperSwordItem", Material.NETHERITE_HOE, ItemUseType.TOOL, 7)),
    SILVERINGOT(new SilverIngotItem("silverIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 2)),
    MALTITEM(new MaltItem("maltItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 3)),
    HOPITEM(new HopItem("hopItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 4)),
    IRONINGOT(new IronIngotItem("ironIngotItem", Material.IRON_INGOT, ItemUseType.CRAFTING)),
    IRONORE(new IronOreItem("ironOreItem", Material.IRON_ORE, ItemUseType.CRAFTING)),
    SCRAP(new ScrapItem("scrapItem", Material.NETHERITE_SCRAP, ItemUseType.CURRENCY));

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
