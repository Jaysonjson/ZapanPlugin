package jayson.json.fuchs.objects.items.type.ability.liquid;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import jayson.json.fuchs.objects.items.lists.zItem;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BeerItem extends AbstractItem {


    private int amount;
    public BeerItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType, damageValue);
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
            if(tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
                amount = tag.getInt(zItemNBT.LIQUID_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(75);
            amount += new Random().nextInt(25);
        }
        oItem.lore.add(ChatColor.GRAY + "" + amount + "ml");
        oItem.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        oItem.setItem(ChatColor.GOLD + "Bier");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT, true);
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, true);
        if(!tag.hasKey(zItemNBT.LIQUID_AMOUNT)) {
            tag.setInt(zItemNBT.LIQUID_AMOUNT, amount);
        }
        return tag;
    }

    //WIP
    @SuppressWarnings("deprecation")
	@Override
    public void ability(World world, Player player, ItemStack itemStack) {
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        zPlayer.getPlayerSpecial().alcohol += new Random().nextDouble() / 15;
        player.sendMessage("Alkohol: " + zPlayer.getPlayerSpecial().alcohol);
        net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(itemStack);
        NBTTagCompound tag = nmsStack.getTag();
        tag.setInt(zItemNBT.LIQUID_AMOUNT, tag.getInt(zItemNBT.LIQUID_AMOUNT) - 10);
        nmsStack.setTag(tag);
        itemStack = CraftItemStack.asBukkitCopy(nmsStack);
        itemStack = createItem(player, itemStack);
        if(tag.getInt(zItemNBT.LIQUID_AMOUNT) < 0) {
            itemStack = zItem.GLASS_ITEM.getAbstractItem().createItem(player);
        }
        System.out.println(itemStack);
        player.setItemInHand(itemStack);
        Utility.makeDrunk(player, zPlayer);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
        DataHandler.savePlayer(zPlayer);
        player.updateInventory();
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

}
