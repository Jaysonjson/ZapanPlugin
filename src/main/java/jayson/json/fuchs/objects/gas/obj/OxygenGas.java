package jayson.json.fuchs.objects.gas.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.gas.AbstractGas;

public class OxygenGas extends AbstractGas {

	@Override
	public String getId() {
		return "oxygen";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.AQUA + "Sauerstoff";
	}

}
