package jayson.json.zapan.items;

import jayson.json.zapan.items.interfaces.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public abstract class AbstractItem implements IzItem, IzAbilityItem, IzAmmo, IzCurrencyItem, IzMarketItem, IzNBTItem, IzMItem, IzConsumeItem, IzTextureItem {

    @Override
    public Material getItemType() {
        return material;
    }

    @Override
    public Material setItemType() {
        return material;
    }

    public String id;
    public Material material;
    public ItemUseType itemUseType;
    public AbstractItem(String id, Material material, ItemUseType itemUseType) {
        this.id = id;
        this.material = material;
        this.itemUseType = itemUseType;
    }

    public final double itemVersion() {
        return 0.1;
    }

    @Override
    public HashMap<String, String> getNBTStrings() {
        return IzNBTItem.strings;
    }

    @Override
    public HashMap<String, Integer> getNBTInts() {
        return IzNBTItem.ints;
    }

    @Override
    public HashMap<String, UUID> getNBTUUIDS() {
        return IzNBTItem.uuids;
    }

    @Override
    public HashMap<String, Double> getNBTDoubles() {
        return IzNBTItem.doubles;
    }

    @Override
    public HashMap<String, Boolean> getNBTBooleans() {
        return IzNBTItem.booleans;
    }

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
    @Deprecated
    public void update(ItemStack itemStack) {
        itemStack = getItem(null);
    }

    @Override
    public ItemStack update(Player player) {
        return getItem(player);
    }

    @NotNull
    @Override
    public String getId() {
        return id;
    }

    @Deprecated
    @Override
    public void setId() {

    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {

    }

    @Override
    public void consume() {

    }

    @Override
    public int getDamageValue() {
        return 0;
    }

    public boolean isAbilityItem() {
        return false;
    }

    public boolean isConsumeAble() {
        return false;
    }

    public boolean isAmmoItem() {
        return false;
    }

    public boolean isVanillaOverride() {
        return false;
    }

    public Material defaultVanillaOverride() {
        return getItemType();
    }

    @Override
    public double getSellValue() {
        return 0;
    }

    @Override
    public double getBuyValue() {
        return 0;
    }

    @Override
    public double getHacksilverAmount() {
        return 0;
    }

    @Override
    public double getZoryhaShardAmount() {
        return 0;
    }

    @Override
    public void setHacksilverAmount(double amount) {

    }

    @Override
    public void setZoryhaShardAmount(double amount) {

    }

    @Override
    public void increaseHacksilverAmount(double amount) {

    }

    @Override
    public void increaseZoryhaShardAmount(double amount) {

    }

    @Override
    public void decreaseHacksilverAmount(double amount) {

    }

    @Override
    public void decreaseZoryhaShardAmount(double amount) {

    }

    @Override
    public ItemUseType getItemUseType() {
        return itemUseType;
    }
}
