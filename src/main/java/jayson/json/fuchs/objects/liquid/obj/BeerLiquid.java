package jayson.json.fuchs.objects.liquid.obj;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.objects.items.FuchsItem;
import jayson.json.fuchs.objects.items.zItemNBT;
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
	
	@Override
	public boolean drinkAble() {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void drinkAction(World world, Player player, ItemStack itemStack) {
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        zPlayer.getPlayerSpecial().alcohol += new Random().nextDouble() / 15;
        player.sendMessage("Alkohol: " + zPlayer.getPlayerSpecial().alcohol);
        
        FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(itemStack), itemStack);
        fuchsItem.changeDoubleTag(zItemNBT.LIQUID_AMOUNT, fuchsItem.getDoubleFromTag(zItemNBT.LIQUID_AMOUNT) - 10);
        
        itemStack = fuchsItem.getItemStack();

        
        player.setItemInHand(itemStack);
        Utility.makeDrunk(player, zPlayer);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
        DataHandler.savePlayer(zPlayer);
        player.updateInventory();
	}

}
