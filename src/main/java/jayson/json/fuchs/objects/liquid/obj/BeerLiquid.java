package jayson.json.fuchs.objects.liquid.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class BeerLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "beer";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Bier";
	}
	
	@Override
	public int getDamageValue() {
		return 5;
	}

}
