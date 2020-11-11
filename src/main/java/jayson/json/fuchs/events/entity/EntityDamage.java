package jayson.json.fuchs.events.entity;

import jayson.json.fuchs.Constant;
import jayson.json.fuchs.Utility;
import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.io.DataHandler;
import jayson.json.fuchs.objects.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
    @SuppressWarnings("deprecation")
	@EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            event.setDamage(event.getDamage() + (zPlayer.getStats().getStrength() * Constant.DAMAGE_MODIFIER));
            if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                ItemStack itemStack = ((Player) event.getDamager()).getItemInHand();
                net.minecraft.server.v1_16_R2.ItemStack nmsCopy = Utility.createNMSCopy(itemStack);
                NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                if(tag.hasKey(zItemNBT.ITEM_DURABILITY)) {
                    tag.setInt(zItemNBT.ITEM_DURABILITY, tag.getInt(zItemNBT.ITEM_DURABILITY) - 1);
                    nmsCopy.setTag(tag);
                    ((Player) event.getDamager()).setItemInHand(CraftItemStack.asBukkitCopy(nmsCopy));
                }
            }
        }
    }
}

