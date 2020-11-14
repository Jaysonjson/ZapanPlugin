package jayson.json.fuchs.objects.items.type.other;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.gas.AbstractGas;
import jayson.json.fuchs.objects.gas.zGas;
import jayson.json.fuchs.objects.items.AbstractItem;
import jayson.json.fuchs.objects.items.zItemNBT;
import jayson.json.fuchs.objects.items.zOItem;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;

public class GasContainerItem extends AbstractItem {

	int damage;
    String gas;
    Double amount;
    AbstractGas abstractGas;
    public GasContainerItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(zItemNBT.CONTAINED_GAS)) {
            	gas = tag.getString(zItemNBT.CONTAINED_GAS);
            }
            if(tag.hasKey(zItemNBT.GAS_AMOUNT)) {
            	amount = tag.getDouble(zItemNBT.GAS_AMOUNT);
            }
        } else {
        	gas = zGas.NONE.getGas().getId();
        	amount = 0.0;
        }

        abstractGas = Utility.gasExists(gas) ? Utility.getGasByID(gas) : zGas.NONE.getGas();
              
        oItem.lore.add(abstractGas.getDisplayName());
        
        oItem.lore.add(amount + "ml");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + gas + "«");
        oItem.setItem(ChatColor.RESET + "Gasbehälter");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(zItemNBT.CONTAINED_GAS)) {
            tag.setString(zItemNBT.CONTAINED_GAS, gas);
        }
        if(!tag.hasKey(zItemNBT.GAS_AMOUNT)) {
        	tag.setDouble(zItemNBT.GAS_AMOUNT, amount);
        }
        return tag;
    }

        
    @Override
    public @NotNull String getId() {
        return super.id;
    }

    @Override
    public @NotNull Material getMaterial() {
        return super.material;
    }

    @Override
    public IItemUseType getItemUse() {
        return super.getItemUse();
    }

    @Override
    @Nullable
    public int getDamageValue() {
        return damage;
    }

}
