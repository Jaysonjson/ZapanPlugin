package jayson.json.zapan.items.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzAbilityItem extends IzItem {
    void ability(World world, Player player, ItemStack itemStack);
}
