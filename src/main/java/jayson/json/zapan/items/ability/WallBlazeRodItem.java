package jayson.json.zapan.items.ability;

import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.AbstractItem;
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

public class WallBlazeRodItem extends AbstractItem {
    private String id;

    public WallBlazeRodItem(String id) {
        this.id = id;
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(new ItemStack(Material.BLAZE_POWDER), getId());
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
        oItem.lore.add(ChatColor.GRAY + "Macht eine diagonale Wand aus Feuer");

        oItem.setItem(ChatColor.RED + "Feuer Es05");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setInt(zItemNBT.CONST_NEEDED_INTELLIGENCE, 5);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public @NotNull String getId() {
        return id;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        Location location = player.getLocation();
        for (int i = 0; i < 5; i++) {
            Block block = world.getBlockAt(new Location(location.getWorld(), location.getX() + i, location.getY(), location.getZ() + i));
            if (block.getType() == Material.AIR) {
                block.setType(Material.FIRE);
            }
            for (int j = 0; j < 5; j++) {
                block = world.getBlockAt(new Location(location.getWorld(), location.getX() - j, location.getY(), location.getZ() - j));
                if (block.getType() == Material.AIR) {
                    block.setType(Material.FIRE);
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
}
