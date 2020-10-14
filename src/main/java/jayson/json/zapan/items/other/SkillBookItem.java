package jayson.json.zapan.items.other;

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

public class SkillBookItem extends AbstractItem {

    public SkillBookItem(String id, Material material) {
        super(id, material);
    }
    @Override
    public ItemStack getItem(Player player) {
        zOItem oItem = new zOItem(this, player, new ItemStack(getItemType()), super.getId());
        oItem.setItem(ChatColor.BOLD + "Skillbuch");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setString(zItemNBT.CONST_ITEM_ID, "skillBookItem");
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
