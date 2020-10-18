package jayson.json.zapan.skillclass;

import org.bukkit.ChatColor;

public enum zClass {

    NONE("Keine Klasse", ChatColor.RED + ""),
    FARMER("Farmer", ChatColor.RED + ""),
    CRAFTER("Schmied", ChatColor.RED + ""),
    TRAVELER("Wanderer", ChatColor.RED + ""),
    POTIONER("Brauer", ChatColor.RED + ""),
    ALCHEMIST("Alchemist", ChatColor.RED + ""),
    FIGHTER("Kämpfer", ChatColor.RED + "");


    String name;
    ChatColor color = ChatColor.RESET;
    String colors;
    zClass(String name, String colors) {
        this.name = name;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public String getColors() {
        return colors;
    }

    @Deprecated
    public ChatColor getColor() {
        return color;
    }
}
