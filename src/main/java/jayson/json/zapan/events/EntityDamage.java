package jayson.json.zapan.events;

import jayson.json.zapan.Constant;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.zItemNBT;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
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
                NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
                if(tag.hasKey(zItemNBT.CONST_ITEM_DURABILITY)) {
                    tag.setInt(zItemNBT.CONST_ITEM_DURABILITY, tag.getInt(zItemNBT.CONST_ITEM_DURABILITY) - 1);
                }
            }
        }
    }
}

