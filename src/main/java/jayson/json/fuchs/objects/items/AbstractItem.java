package jayson.json.fuchs.objects.items;

import jayson.json.fuchs.objects.items.interfaces.*;
import jayson.json.fuchs.objects.items.nbt.INBTObject;
import jayson.json.fuchs.objects.items.nbt.NBTBoolean;
import jayson.json.fuchs.objects.items.nbt.NBTString;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import jayson.json.fuchs.objects.items.type.zItemMarketModifierType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public abstract class AbstractItem implements IzItem, IzNBTItem, IzAbilityItem, IzAmmo, IzMarketItem, IzConsumeItem, IzTextureItem, IzToolItem, IzAlchemyItem {

    @Deprecated
    @Override
    public void setMaterial(Material material) {

    }

    public String id;
    public Material material;
    public IItemUseType itemUseType;
    public int modelData;
    public AbstractItem(String id, Material material, IItemUseType itemUseType) {
        this.id = id;
        this.material = material;
        this.itemUseType = itemUseType;
    }

    public AbstractItem(String id, Material material, IItemUseType itemUseType, int modelData) {
        this.id = id;
        this.material = material;
        this.itemUseType = itemUseType;
        this.modelData = modelData;
    }

    @NotNull
    @Override
    public String getId() {
        return id;
    }

    @NotNull
    @Override
    public Material getMaterial() {
        return material;
    }

    public final double itemVersion() {
        return 0.1;
    }

    @Override
    public ItemStack update(Player player, ItemStack itemStack) {
        return createItem(player, itemStack);
    }

    @Override
    public HashMap<String, NBTString> getNBTStrings() {
        return null;
    }

    @Override
    public HashMap<String, Integer> getNBTInts() {
        return null;
    }

    @Override
    public HashMap<String, UUID> getNBTUUIDS() {
        return null;
    }

    @Override
    public HashMap<String, Double> getNBTDoubles() {
        return null;
    }

    @Override
    public HashMap<String, NBTBoolean> getNBTBooleans() {
        return null;
    }

    @Override
    public HashMap<String, INBTObject> getNBTObjects() {
        return new HashMap<>();
    }

    @Override
    @Deprecated
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
        return 1;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        return new NBTTagCompound();
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
    @Deprecated
    public zItemMarketModifierType MARKET_MODIFIER_TYPE() {
        return zItemMarketModifierType.SCRAPYARD;
    }

    //Semi-Alt, --> getItemUse(); Interface (IItemUseType) - / Lib
    @Override
    @Deprecated
    public ItemUseType getItemUseType() {
        return (ItemUseType) itemUseType;
    }
    
    @Override
    public IItemUseType getItemUse() {
    	return itemUseType;
    }
    
    @Override
    public IMarketModifierType MARKET_MODIFIER() {
    	return IzMarketItem.super.MARKET_MODIFIER();
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }

}
