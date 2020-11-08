package jayson.json.fuchs.objects.liquid;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jayson.json.fuchs.objects.liquid.interfaces.IzLiquid;

public abstract class AbstractLiquid implements IzLiquid {

	@Override
	public boolean drinkAble() {
		return false;
	}
	
	@Override
	public void drinkAction(World world, Player player, ItemStack itemStack) {		
	}
	
}
