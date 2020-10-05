package jayson.json.zapan.items.other;

import jayson.json.zapan.items.AbstractzItem;
import jayson.json.zapan.items.zItemNBT;
import jayson.json.zapan.items.zOItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class SkillBookItem extends AbstractzItem {
    @Override
    public ItemStack getItem() {
        zOItem oItem = new zOItem(new ItemStack(Material.WRITTEN_BOOK));
        oItem.setItem(ChatColor.BOLD + "Skill Buch");
        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(zItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setString(zItemNBT.CONST_ITEM_ID, "skillBookItem");
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

}
