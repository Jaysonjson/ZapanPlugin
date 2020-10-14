package jayson.json.zapan.items.currency;

import jayson.json.zapan.items.AbstractItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldNuggetItem extends AbstractItem {

    public GoldNuggetItem(String id, Material material) {
        super(id, material);
    }

    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getItemType()), super.getId());
        oItem.lore.add(ChatColor.GRAY + "17.43Φ");
        oItem.setItem(ChatColor.GOLD + "Goldklumpen");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setDouble(zItemNBT.CONST_HACKSILVER_AMOUNT, 5.43);
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public @NotNull String getId() {
        return super.getId();
    }

    @Override
    public Material getItemType() {
        return super.getItemType();
    }
}
