package jayson.json.fuchs.objects.items.type.other;

import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.objects.items.*;
import jayson.json.fuchs.objects.items.interfaces.IItemUseType;
import jayson.json.fuchs.objects.items.type.ItemUseType;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SkillBookItem extends AbstractItem {


    public SkillBookItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        zOItem oItem = new zOItem(this, player, true);
        oItem.setItem(ChatColor.RESET + "Skillbuch");

        if(player != null) {
            createBookTag(player, oItem.item);
        }

        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        itemStack.setItemMeta(createBookTag(player, itemStack));
    }

    public BookMeta createBookTag(Player player, ItemStack itemStack) {
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle("Skill Buch");
        bookMeta.setAuthor("Oberbürgermeister");
        List<String> pages = new ArrayList<>();
        pages.add(ChatColor.BOLD + "     Skill Buch\n\n" +
                "" + ChatColor.RESET +
                "\nStärke: " + zPlayer.getStats().getStrength() +
                "\nIntelligenz: " + zPlayer.getStats().getIntelligence()
        );
        bookMeta.setPages(pages);
        return bookMeta;
    }
    
    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(zItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
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

    @Override
    public boolean isAbilityItem() {
        return true;
    }
}
