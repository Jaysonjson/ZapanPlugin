package jayson.json.fuchs.objects.liquid.obj.metal;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class MoltenCopperLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "moltenCopper";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Geschmolzenes Kupfer";
	}
	
	@Override
	public int getDamageValue() {
		return 17;
	}

}
