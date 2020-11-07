package jayson.json.fuchs.items.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IzAbilityItem extends IzItem {
    //void ability(Player player, ItemInventoryData itemInventoryData);
    /*@Deprecated
    default void ability(World world, Player player, ItemStack itemStack) {
        ability(player, null);
    }*/
    void ability(World world, Player player, ItemStack itemStack);
    boolean isAbilityItem();
    int requiredIntelligence();
}
