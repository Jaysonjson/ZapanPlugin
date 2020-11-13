package jayson.json.fuchs.events.entity.player;

import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.io.DataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        //Player player = event.getEntity();
        zPlayer player = DataHandler.loadPlayer(event.getEntity().getUniqueId());
        player.getHealth().health -= 2;
        DataHandler.savePlayer(player);
        //Utility.RefreshHearts(event.getEntity(), player);
    }

}
