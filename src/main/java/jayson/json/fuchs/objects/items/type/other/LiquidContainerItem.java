package jayson.json.fuchs.objects.items.type.other;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.items.AbstractItem;
import jayson.json.fuchs.objects.items.FuchsItem;
import jayson.json.fuchs.objects.items.zItemNBT;
import jayson.json.fuchs.objects.items.zOItem;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import jayson.json.fuchs.objects.liquid.AbstractLiquid;
import jayson.json.fuchs.objects.liquid.zLiquid;
import net.minecraft.server.v1_16_R2.NBTTagCompound;

public class LiquidContainerItem extends AbstractItem {

    String liquid;
    Double amount;
    AbstractLiquid abstractLiquid;
    public LiquidContainerItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
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
            if(tag.hasKey(zItemNBT.CONTAINED_LIQUID)) {
            	liquid = tag.getString(zItemNBT.CONTAINED_LIQUID);
            }
            if(tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
            	amount = tag.getDouble(zItemNBT.LIQUID_AMOUNT);
            }
        } else {
        	liquid = zLiquid.NONE.getLiquid().getId();
        	amount = 0.0;
        }

        abstractLiquid = Utility.liquidExists(liquid) ? Utility.getLiquidByID(liquid) : zLiquid.NONE.getLiquid();
              
        oItem.lore.add(abstractLiquid.getDisplayName());
        
        oItem.lore.add(amount + "ml");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + liquid + "«");
        oItem.setItem(ChatColor.RESET + "Flüssigkeitsbehälter");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        if(!tag.hasKey(zItemNBT.CONTAINED_LIQUID)) {
            tag.setString(zItemNBT.CONTAINED_LIQUID, liquid);
        }
        if(!tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
        	tag.setDouble(zItemNBT.LIQUID_AMOUNT, amount);
        }
        return tag;
    }

    
    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
    	FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(itemStack), itemStack);
    	String liId = fuchsItem.getStringFromTag(zItemNBT.CONTAINED_LIQUID);
    	AbstractLiquid aLiquid = Utility.liquidExists(liId) ? Utility.getLiquidByID(liId) : zLiquid.NONE.getLiquid();
    	aLiquid.drinkAction(world, player, itemStack);
    	//FuchsItem fuchsItem = new FuchsItem(Utility.getAbstractItemFromNMS(itemStack), itemStack);
        //fuchsItem.changeStringTag(zItemNBT.CONTAINED_LIQUID, zLiquid.BEER.getLiquid().getId());
        //fuchsItem.changeDoubleTag(zItemNBT.LIQUID_AMOUNT, 500d);
    	//world.dropItemNaturally(player.getLocation(), fuchsItem.getItemStack());
        //player.setItemInHand(fuchsItem.getItemStack());
    }
    
    @Override
    public boolean isAbilityItem() {
    	return true;
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
        return abstractLiquid.getDamageValue();
    }

    
}
