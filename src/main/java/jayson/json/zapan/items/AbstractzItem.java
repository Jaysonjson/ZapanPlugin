package jayson.json.zapan.items;

import jayson.json.zapan.items.interfaces.IzAbilityItem;
import jayson.json.zapan.items.interfaces.IzAmmo;
import jayson.json.zapan.items.interfaces.IzItem;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractzItem implements IzItem, IzAbilityItem, IzAmmo {

    @Override
    public ItemStack getItem(Player player) {
        return new ItemStack(Material.AIR);
    }

    @Override
    public ItemStack getItem() {
        return getItem(null);
    }

    @Override
    public IzAbilityItem getAbilityItem() {
        return this;
    }

    @Override
    public void ability(World world, Player player) {

    }

    public boolean isAbilityItem() {
        return false;
    }

    public boolean isAmmoItem() {
        return false;
    }

}
