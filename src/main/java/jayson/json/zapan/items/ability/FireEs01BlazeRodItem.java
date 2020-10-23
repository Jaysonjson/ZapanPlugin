package jayson.json.zapan.items.ability;

import jayson.json.zapan.data.zPlayer;
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
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FireEs01BlazeRodItem extends AbstractItem {


    public FireEs01BlazeRodItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getMaterial()), getId());
        oItem.init();

        if(player != null) {
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            if(zPlayer.getStats().getIntelligence() >= 5) {
                oItem.lore.add(ChatColor.GREEN + "Benötigt Intelligenz 5");
            } else {
                oItem.lore.add(ChatColor.RED + "Benötigt Intelligenz 5 (Du hast: " + zPlayer.getStats().getIntelligence() + ")");
            }
        } else {
            oItem.lore.add(ChatColor.GRAY + "Benötigt Intelligenz 5");
        }
        oItem.lore.add(ChatColor.GRAY + "Platziert zufällig Feuer in der Nähe");

        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setInt(zItemNBT.CONST_NEEDED_INTELLIGENCE, 5);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);

        oItem.setItem(ChatColor.RED + "Feuer Es01");
        return oItem.item;
    }


    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        Location location = player.getLocation();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(12); i++) {
            Block block = world.getBlockAt(new Location(location.getWorld(), location.getX() + i, location.getY(), location.getZ() + i));
            if (block.getType() == Material.AIR && random.nextInt(3) == 1) {
                block.setType(Material.FIRE);
            }
            for (int j = 0; j < random.nextInt(12); j++) {
                block = world.getBlockAt(new Location(location.getWorld(), location.getX() - i, location.getY(), location.getZ() - j));
                if (block.getType() == Material.AIR && random.nextInt(3) == 1) {
                    block.setType(Material.FIRE);
                }
                for (int k = 0; k < random.nextInt(12); k++) {
                    block = world.getBlockAt(new Location(location.getWorld(), location.getX() - k, location.getY(), location.getZ() + j));
                    if (block.getType() == Material.AIR && random.nextInt(3) == 1) {
                        block.setType(Material.FIRE);
                    }
                    for (int l = 0; l < random.nextInt(12); l++) {
                        block = world.getBlockAt(new Location(location.getWorld(), location.getX() + l - k, location.getY(), location.getZ() - k));
                        if (block.getType() == Material.AIR && random.nextInt(3) == 1) {
                            block.setType(Material.FIRE);
                        }
                        for (int m = 0; m < random.nextInt(12); m++) {
                            block = world.getBlockAt(new Location(location.getWorld(), location.getX() + l - m + i, location.getY(), location.getZ() - m));
                            if (block.getType() == Material.AIR && random.nextInt(2) == 1) {
                                block.setType(Material.FIRE);
                            }
                        }
                    }
                }
            }
        }
        if(itemStack != null) {
            itemStack.setAmount(itemStack.getAmount() - 1);
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
