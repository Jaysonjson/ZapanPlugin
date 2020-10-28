package jayson.json.zapan.items;

import jayson.json.zapan.items.interfaces.*;
import jayson.json.zapan.other.zItemMarketModifierType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public abstract class AbstractItem implements IzItem, IzAbilityItem, IzAmmo, IzMarketItem, IzConsumeItem, IzTextureItem, IzToolItem, IzAlchemyItem {

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
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        return new ItemStack(Material.AIR);
    }

    @Override
    @Deprecated
    public void update(ItemStack itemStack) {
        itemStack = createItem(null, null, null);
    }

    @Override
    public ItemStack update(Player player) {
        return createItem(player, null, null);
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
    public int getMetalValue() {
        return 0;
    }

    @Override
    public int getFireValue() {
        return 0;
    }

    @Override
    public void setDamage(int value) {

    }

    @Override
    public void setDurability(int value) {

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
    public double getMarketValue() {
        return 0;
    }

    @Override
    public zItemMarketModifierType MARKET_MODIFIER_TYPE() {
        return zItemMarketModifierType.SCRAPYARD;
    }

    @Override
    public ItemUseType getItemUseType() {
        return itemUseType;
    }
}
