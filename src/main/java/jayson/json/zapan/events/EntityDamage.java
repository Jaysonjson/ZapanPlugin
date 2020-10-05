package jayson.json.zapan.events;

import jayson.json.zapan.Constant;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            event.setDamage(event.getDamage() + (zPlayer.getStats().getStrength() * Constant.DAMAGE_MODIFIER));
        }
    }
}

