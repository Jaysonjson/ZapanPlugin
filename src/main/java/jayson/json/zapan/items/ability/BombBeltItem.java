package jayson.json.zapan.items.ability;

import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.ItemUseType;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import jayson.json.zapan.data.zPlayer;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BombBeltItem extends AbstractItem {


    public BombBeltItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    //@Override
    //public ItemStack createItem(Player player) {
      /*  zOItem oItem = new zOItem(this, player);
        oItem.init();
        if(player != null) {
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            if(zPlayer.getStats().getIntelligence() >= 5) {
                oItem.lore.add(ChatColor.GREEN + "Benötigt Intelligenz -1");
            } else {
                oItem.lore.add(ChatColor.RED + "Benötigt Intelligenz -1 (Du hast: " + zPlayer.getStats().getIntelligence() + ")");
            }
        } else {
            oItem.lore.add(ChatColor.GRAY + "Benötigt Intelligenz -1");
        }
        oItem.lore.add(ChatColor.GRAY + "Die Intelligenz wurde für Clarest angepasst!");

        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setInt(zItemNBT.CONST_NEEDED_INTELLIGENCE, -1);
        tag.setInt(new Random().nextInt(150) + "IDChange", new Random().nextInt(500));
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);

        oItem.setItem(ChatColor.RED + "Bombengürtel");
        return oItem.item;

       */
    //}


    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        if(player.getInventory().getChestplate().getType().equals(Material.AIR)) {
            player.getInventory().setChestplate(itemStack);
            if (itemStack != null) {
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

    @Override
    public @NotNull String getId() {
        return super.getId();
    }

    @Override
    public Material getMaterial() {
        return super.getMaterial();
    }

    @Override
    public ItemUseType getItemUseType() {
        return super.getItemUseType();
    }
}
