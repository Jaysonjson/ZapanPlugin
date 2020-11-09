package jayson.json.fuchs.objects.liquid.obj.metal;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class MoltenIronLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "moltenIron";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Geschmolzenes Eisen";
	}
	
	@Override
	public int getDamageValue() {
		return 19;
	}

}
