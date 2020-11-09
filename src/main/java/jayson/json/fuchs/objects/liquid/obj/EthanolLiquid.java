package jayson.json.fuchs.objects.liquid.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class EthanolLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "ethanol";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.WHITE + "Ethanol";
	}
	
	@Override
	public int getDamageValue() {
		return 16;
	}


}
