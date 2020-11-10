package jayson.json.fuchs.objects.items;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.items.interfaces.IItemUseType;

public enum ItemUseType implements IItemUseType {

    CRAFTING(ChatColor.AQUA + "Herstellungsmaterial"),
    ABILITY(ChatColor.AQUA + "Benutzbar"),
    CURRENCY(ChatColor.AQUA + "Währung"),
    TOOL(ChatColor.AQUA + "Werkzeug"),
    OTHER("");

    private final String loreText;
    ItemUseType(String loreText) {
        this.loreText = loreText;
    }

    @Override
    public String getLoreText() {
        return loreText;
    }

}
