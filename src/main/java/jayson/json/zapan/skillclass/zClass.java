package jayson.json.zapan.skillclass;

import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.classes.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum zClass {

    NONE("Keine Klasse", ChatColor.RED + "", new ItemStack(Material.AIR)),
    FIGHTER("Kämpfer", ChatColor.RED + "", new FighterClassItem("fighterClassItem", Material.NETHERITE_SWORD, ItemUseType.OTHER).createItem(null, null)),
    FARMER("Farmer", ChatColor.RED + "", new FarmerClassItem("farmerClassItem", Material.NETHERITE_HOE, ItemUseType.OTHER).createItem(null, null)),
    CRAFTER("Schmied", ChatColor.RED + "", new CrafterClassItem("crafterClassItem", Material.CRAFTING_TABLE, ItemUseType.OTHER).createItem(null, null)),
    TRAVELER("Wanderer", ChatColor.RED + "", new TravelerClassItem("travelerClassItem", Material.IRON_BOOTS, ItemUseType.OTHER).createItem(null, null)),
    POTIONER("Brauer", ChatColor.RED + "", new PotionerClassItem("potionerClassItem", Material.LINGERING_POTION, ItemUseType.OTHER).createItem(null, null)),
    ALCHEMIST("Alchemist", ChatColor.RED + "", new AlchemistClassItem("alchemistClassItem", Material.LAVA_BUCKET, ItemUseType.OTHER).createItem(null, null));


    private String name;
    private ChatColor color = ChatColor.RESET;
    private String colors;
    private ItemStack symbol;
    zClass(String name, String colors, ItemStack symbol) {
        this.name = name;
        this.colors = colors;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return colors;
    }

    public ItemStack getSymbol() {
        return symbol;
    }
}
