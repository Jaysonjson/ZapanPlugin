package jayson.json.fuchs.objects.items.lists;

import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.type.crafting.*;
import jayson.json.fuchs.objects.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.objects.items.type.other.*;
import jayson.json.fuchs.objects.items.type.currency.*;
import jayson.json.fuchs.objects.items.type.vanillaOverride.*;
import org.bukkit.Material;

public enum zItem implements IzItemRegistry {

    GOLD_BAR(new GoldBarItem("goldBarItem", Material.GOLD_INGOT, ItemUseType.CURRENCY)),
    GOLD_NUGGET(new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET, ItemUseType.CURRENCY)),
    HACKSILVER(new HackSilverItem("hackSilverItem", Material.IRON_NUGGET, ItemUseType.CURRENCY)),
    ZORYHA_SHARD(new ZoryhaShardItem("zoryhaShardItem", Material.NETHER_STAR, ItemUseType.CURRENCY)),
    SKILL_BOOK(new SkillBookItem("skillBookItem", Material.WRITTEN_BOOK, ItemUseType.OTHER)),
    COPPER_INGOT(new CopperIngotItem("copperIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 1)),
    SILVER_INGOT(new SilverIngotItem("silverIngotItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 2)),
    MALT_ITEM(new MaltItem("maltItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 3)),
    HOP_ITEM(new HopItem("hopItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 4)),
    //BEER_ITEM(new BeerItem("beerItem", Material.NETHERITE_HOE, ItemUseType.ABILITY, 5)),
    GLASS_ITEM(new GlassItem("glassItem", Material.NETHERITE_HOE, ItemUseType.ABILITY, 6)),
    //WATER_ITEM(new WaterItem("waterItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 7)),
    COPPER_ROD(new CopperRodItem("copperRodItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 8)),
    COPPER_PIPE(new CopperPipeItem("copperPipeItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 9)),
    COPPER_LIQUID(new LiquidCopperItem("copperLiquidItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 10)),
    COPPER_SWORD(new CopperSwordItem("copperSwordItem", Material.NETHERITE_HOE, ItemUseType.TOOL, 11)),
    LIQUID_CONTAINER(new LiquidContainerItem("liquidContainerItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 12)),
    GAS_CONTAINER(new GasContainerItem("gasContainerItem", Material.NETHERITE_HOE, ItemUseType.CRAFTING, 15)),
    IRON_INGOT(new IronIngotItem("ironIngotItem", Material.IRON_INGOT, ItemUseType.CRAFTING)),
    IRO_NORE(new IronOreItem("ironOreItem", Material.IRON_ORE, ItemUseType.CRAFTING)),
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
