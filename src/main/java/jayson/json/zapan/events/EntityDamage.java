package jayson.json.zapan.events;

import jayson.json.zapan.Constant;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent event) {
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

