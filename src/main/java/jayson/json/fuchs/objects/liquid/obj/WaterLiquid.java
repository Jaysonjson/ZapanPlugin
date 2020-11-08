package jayson.json.fuchs.objects.liquid.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class WaterLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "water";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.AQUA + "Wasser";
	}
	
	@Override
	public int getDamageValue() {
		return 6;
	}

}
