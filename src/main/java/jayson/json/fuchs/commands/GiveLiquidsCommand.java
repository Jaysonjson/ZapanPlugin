package jayson.json.fuchs.commands;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.FuchsItem;
import jayson.json.fuchs.objects.items.lists.zItem;
import jayson.json.fuchs.objects.items.zItemNBT;
import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;
import jayson.json.fuchs.objects.zRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveLiquidsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			for (IzLiquidRegistry liquid : zRegistry.liquids) {
				ItemStack stack = zItem.LIQUIDCONTAINER.getAbstractItem().createItem();
				FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(stack), stack);
				fuchsItem.changeStringTag(zItemNBT.CONTAINED_LIQUID, liquid.getLiquid().getId());
				fuchsItem.changeDoubleTag(zItemNBT.LIQUID_AMOUNT, 500d);
				Player player = (Player) arg0;
				player.getWorld().dropItemNaturally(player.getLocation(), fuchsItem.getItemStack());
			}
		}
		return false;
	}

}
