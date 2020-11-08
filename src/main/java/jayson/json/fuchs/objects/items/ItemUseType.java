package jayson.json.fuchs.objects.items;

import org.bukkit.ChatColor;

public enum ItemUseType {

    CRAFTING(ChatColor.AQUA + "Herstellungsmaterial"),
    ABILITY(ChatColor.AQUA + "Benutzbar"),
    CURRENCY(ChatColor.AQUA + "Währung"),
    TOOL(ChatColor.AQUA + "Werkzeug"),
    OTHER("");

    private final String loreText;
    ItemUseType(String loreText) {
        this.loreText = loreText;
    }

    public String getLoreText() {
        return loreText;
    }

}
