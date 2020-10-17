package jayson.json.zapan.items;

import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class zOItem {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public String id;
    public net.minecraft.server.v1_16_R2.ItemStack nmsCopy;
    public AbstractItem zItem;
    public Player player = null;
    public zOItem(AbstractItem zItem, ItemStack itemStack, ArrayList<String> lore, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = lore;
        this.id = id;
        this.zItem = zItem;
    }

    public zOItem(AbstractItem zItem, ItemStack itemStack, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
        this.zItem = zItem;
    }

    public zOItem(AbstractItem zItem, Player player, ItemStack itemStack, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
        this.zItem = zItem;
        this.player = player;
    }

    public void setItem(String displayName) {
        switch (zItem.getItemUseType()) {
            case CRAFTING: lore.add(ChatColor.AQUA + "Herstellungsmaterial"); break;
            case CURRENCY: lore.add(ChatColor.AQUA + "Währung"); break;
            case ABILITY: lore.add(ChatColor.AQUA + "Benutzbar"); break;
        }
        if(player != null) {
            if(player.getGameMode().equals(GameMode.CREATIVE) ||player.getGameMode().equals(GameMode.SPECTATOR)) {
                lore.add(ChatColor.LIGHT_PURPLE + "In Kreativ bekommen");
                lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + player.getDisplayName() + "«");
            }
        }
        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id + " [" + zItem.itemVersion() + "]");
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
        nmsCopy = createNMSCopy();
    }

    public net.minecraft.server.v1_16_R2.ItemStack createNMSCopy() {
        return CraftItemStack.asNMSCopy(item);
    }

    public void setTagCompound() {
        NBTTagCompound tag = getTagCompound();
        //tag.setString(zItemNBT.CONST_ITEM_ID, id);
        //tag.setDouble(zItemNBT.CONST_ITEM_VERSION, zItem.itemVersion());
        zItem.getNBTStrings().keySet().forEach((s -> tag.setString(s, zItem.getNBTStrings().get(s))));
        zItem.getNBTUUIDS().keySet().forEach((s -> tag.setString(s, zItem.getNBTUUIDS().get(s).toString())));
        zItem.getNBTInts().keySet().forEach((s -> tag.setInt(s, zItem.getNBTInts().get(s))));
        zItem.getNBTDoubles().keySet().forEach((s -> tag.setDouble(s, zItem.getNBTDoubles().get(s))));
        zItem.getNBTBooleans().keySet().forEach((s -> tag.setBoolean(s, zItem.getNBTBooleans().get(s))));
    }

    public NBTTagCompound tagCompound() {
        return getTagCompound();
    }

    public NBTTagCompound getTagCompound() {
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        tag.setString(zItemNBT.CONST_ITEM_ID, id);
        tag.setDouble(zItemNBT.CONST_ITEM_VERSION, zItem.itemVersion());
        return tag;
    }
}
