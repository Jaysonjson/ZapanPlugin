package jayson.json.fuchs.items.lists;

import jayson.json.fuchs.items.AbstractItem;
import jayson.json.fuchs.items.ItemUseType;
import jayson.json.fuchs.items.ability.BeerItem;
import jayson.json.fuchs.items.crafting.*;
import jayson.json.fuchs.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.items.other.ScrapItem;
import jayson.json.fuchs.items.other.SkillBookItem;
import jayson.json.fuchs.items.currency.ZoryhaShardItem;
import jayson.json.fuchs.items.currency.GoldBarItem;
import jayson.json.fuchs.items.currency.GoldNuggetItem;
import jayson.json.fuchs.items.currency.HackSilverItem;
import jayson.json.fuchs.items.vanillaOverride.IronIngotItem;
import jayson.json.fuchs.items.vanillaOverride.IronOreItem;
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
    BEERITEM(new BeerItem("beerItem", Material.NETHERITE_HOE, ItemUseType.ABILITY, 5)),
    GLASSITEM(new GlassItem("glassItem", Material.NETHERITE_HOE, ItemUseType.ABILITY, 6)),
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
