package jayson.json.fuchs.events;

import jayson.json.fuchs.data.zPlayer;
import jayson.json.fuchs.io.DataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event) {
        //Player player = event.getEntity();
        zPlayer player = DataHandler.loadPlayer(event.getEntity().getUniqueId());
        player.getHealth().health -= 2;
        DataHandler.savePlayer(player);
        //Utility.RefreshHearts(event.getEntity(), player);
    }

}
