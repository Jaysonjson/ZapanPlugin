package jayson.json.zapan.items.ability;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.*;
import jayson.json.zapan.items.lists.zItem;
import jayson.json.zapan.items.nbt.INBTObject;
import jayson.json.zapan.items.nbt.NBTBoolean;
import jayson.json.zapan.items.nbt.NBTInteger;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;

public class BeerItem extends AbstractItem {


    int damage;
    private int amount;
    public BeerItem(String id, Material material, ItemUseType itemUseType, int damageValue) {
        super(id, material, itemUseType);
        this.damage = damageValue;
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, getId(),true);

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
            itemStack = zItem.GLASSITEM.getAbstractItem().createItem(player);
        }
        System.out.println(itemStack);
        player.setItemInHand(itemStack);
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (zPlayer.getPlayerSpecial().alcohol * 740),0));
        if(zPlayer.getPlayerSpecial().alcohol > 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (zPlayer.getPlayerSpecial().alcohol * 100),0));
        }
        if(zPlayer.getPlayerSpecial().alcohol > 2) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (zPlayer.getPlayerSpecial().alcohol * 60),0));
        }
        if(zPlayer.getPlayerSpecial().alcohol > 3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (zPlayer.getPlayerSpecial().alcohol * 700),2));
        }
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
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }

    @Override
    public int getDamageValue() {
        return damage;
    }
}
