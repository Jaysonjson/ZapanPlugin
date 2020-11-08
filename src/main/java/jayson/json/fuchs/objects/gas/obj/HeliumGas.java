package jayson.json.fuchs.objects.gas.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.gas.AbstractGas;

public class HeliumGas extends AbstractGas {

	@Override
	public String getId() {
		return "helium";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GREEN + "Helium";
	}

}
