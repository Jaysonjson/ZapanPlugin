package jayson.json.zapan.items;

import jayson.json.zapan.items.interfaces.*;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public abstract class AbstractItem implements IzItem, IzAbilityItem, IzAmmo, IzMarketItem, IzNBTItem, IzConsumeItem, IzTextureItem, IzToolItem, IzAlchemyItem {

    @Override
    public Material getMaterial() {
        return material;
    }

    @Deprecated
    @Override
    public void setMaterial(Material material) {

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
    public ItemStack createItem(Player player, ItemStack stack) {
        return new ItemStack(Material.AIR);
    }

    @Override
    @Nullable
    @Deprecated
    public ItemStack createItem() {
        return createItem(null, null);
    }

    @Override
    @Deprecated
    public void update(ItemStack itemStack) {
        itemStack = createItem(null, null);
    }

    @Override
    public ItemStack update(Player player) {
        return createItem(player, null);
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public int getEarthValue() {
        return 0;
    }

    @Override
    public int getWaterValue() {
        return 0;
    }

    @Override
    public void setDamage(int value) {

    }

    @Override
    public void setDurability(int value) {

    }

    @NotNull
    @Override
    public String getId() {
        return id;
    }

    @Deprecated
    @Override
    public void setId(String id) {

    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {

    }

    @Override
    public void consume() {

    }

    @Override
    public int requiredIntelligence() {
        return 0;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        return null;
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
        return material;
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
    public ItemUseType getItemUseType() {
        return itemUseType;
    }
}
