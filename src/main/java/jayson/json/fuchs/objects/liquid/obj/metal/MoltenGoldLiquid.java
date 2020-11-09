package jayson.json.fuchs.objects.liquid.obj.metal;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class MoltenGoldLiquid extends AbstractLiquid {

	@Override
	public String getId() {
		return "moltenGold";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Geschmolzenes Gold";
	}
	
	@Override
	public int getDamageValue() {
		return 18;
	}

}
