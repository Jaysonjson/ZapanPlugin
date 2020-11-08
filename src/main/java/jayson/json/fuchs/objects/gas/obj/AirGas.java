package jayson.json.fuchs.objects.gas.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.gas.AbstractGas;

public class AirGas extends AbstractGas {

	@Override
	public String getId() {
		return "air";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.WHITE + "Luft";
	}

}
