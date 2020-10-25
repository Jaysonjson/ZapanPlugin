package jayson.json.zapan.skillclass;

import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.classes.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public enum zClass {

    NONE("Keine Klasse", new zDefaultClass(),ChatColor.RED + "", new ItemStack(Material.AIR)),
    FIGHTER("Kämpfer",new zFighterClass(), ChatColor.RED + "", new FighterClassItem("fighterClassItem", Material.NETHERITE_SWORD, ItemUseType.OTHER).createItem(null, null)),
    FARMER("Farmer", null,ChatColor.RED + "", new FarmerClassItem("farmerClassItem", Material.NETHERITE_HOE, ItemUseType.OTHER).createItem(null, null)),
    CRAFTER("Schmied", null,ChatColor.RED + "", new CrafterClassItem("crafterClassItem", Material.CRAFTING_TABLE, ItemUseType.OTHER).createItem(null, null)),
    TRAVELER("Wanderer", null,ChatColor.RED + "", new TravelerClassItem("travelerClassItem", Material.IRON_BOOTS, ItemUseType.OTHER).createItem(null, null)),
    POTIONER("Brauer", null,ChatColor.RED + "", new PotionerClassItem("potionerClassItem", Material.LINGERING_POTION, ItemUseType.OTHER).createItem(null, null)),
    ALCHEMIST("Alchemist", new zAlchemistClass(),ChatColor.RED + "", new AlchemistClassItem("alchemistClassItem", Material.LAVA_BUCKET, ItemUseType.OTHER).createItem(null, null));


    private String name;
    private zAbstractClass abstractClass;
    private ChatColor color = ChatColor.RESET;
    private String colors;
    private ItemStack symbol;
    zClass(String name, zAbstractClass abstractClass, String colors, ItemStack symbol) {
        this.name = name;
        this.colors = colors;
        this.symbol = symbol;
        this.abstractClass = abstractClass;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public zAbstractClass getAbstractClass() {
        return abstractClass;
    }

    public String getColor() {
        return colors;
    }

    public ItemStack getSymbol() {
        return symbol;
    }
}
