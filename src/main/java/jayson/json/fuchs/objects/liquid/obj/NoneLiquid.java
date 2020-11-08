package jayson.json.fuchs.objects.liquid.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class NoneLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "none";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.RED + "Leer";
	}
	
	@Override
	public int getDamageValue() {
		return 6;
	}

}
