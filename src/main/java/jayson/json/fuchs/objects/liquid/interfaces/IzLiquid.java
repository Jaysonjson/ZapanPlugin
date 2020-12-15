package jayson.json.fuchs.objects.liquid.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzLiquid {

	String getId();
	String getDisplayName();
	int getDamageValue();
	boolean drinkAble();
	void drinkAction(World world, Player player, ItemStack itemStack);
	
}
