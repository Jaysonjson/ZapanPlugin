package jayson.json.fuchs.items.ability;

import jayson.json.fuchs.items.*;
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


    public WallBlazeRodItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack, zAdditionalItemInformation infoItem) {
        zOItem oItem = new zOItem(this, player,false);
        oItem.lore.add(ChatColor.GRAY + "Macht eine diagonale Wand aus Feuer");
        oItem.setItem(ChatColor.RED + "Feuer Es05");

        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setInt(zItemNBT.NEEDED_INTELLIGENCE, requiredIntelligence());
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int requiredIntelligence() {
        return 5;
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
}
