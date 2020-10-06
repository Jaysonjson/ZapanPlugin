package jayson.json.zapan.items;

import jayson.json.zapan.items.interfaces.IzAbilityItem;
import jayson.json.zapan.items.interfaces.IzAmmo;
import jayson.json.zapan.items.interfaces.IzCurrencyItem;
import jayson.json.zapan.items.interfaces.IzItem;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public abstract class AbstractzItem implements IzItem, IzAbilityItem, IzAmmo, IzCurrencyItem {

    @Override
    public ItemStack getItem(Player player) {
        return new ItemStack(Material.AIR);
    }

    @Override
    @Nullable
    @Deprecated
    public ItemStack getItem() {
        return getItem(null);
    }

    @Override
    public IzAbilityItem getAbilityItem() {
        return this;
    }

    @Override
    @Deprecated
    public void update(ItemStack itemStack) {
        itemStack = getItem();
    }

    @Override
    public ItemStack update(Player player) {
        return getItem(player);
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

    @Override
    public double getHacksilverAmount() {
        return 0;
    }

    @Override
    public double getEmeraldAmount() {
        return 0;
    }

    @Override
    public void setHacksilverAmount(double amount) {

    }

    @Override
    public void setEmeraldAmount(double amount) {

    }

    @Override
    public void increaseHacksilverAmount(double amount) {

    }

    @Override
    public void increaseEmeraldAmount(double amount) {

    }

    @Override
    public void decreaseHacksilverAmount(double amount) {

    }

    @Override
    public void decreaseEmeraldAmount(double amount) {

    }
}
