package jayson.json.fuchs.objects.gas.obj;

import org.bukkit.ChatColor;

import jayson.json.fuchs.objects.gas.AbstractGas;

public class NoneGas extends AbstractGas {

	@Override
	public String getId() {
		return "none";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.RED + "Leer";
	}

}
