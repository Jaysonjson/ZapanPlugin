package jayson.json.fuchs.objects.liquid.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class LavaLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "lava";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.DARK_RED + "Lava";
	}
	
	@Override
	public int getDamageValue() {
		return 14;
	}

}
