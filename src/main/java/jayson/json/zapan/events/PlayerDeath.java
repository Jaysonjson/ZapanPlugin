package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.entity.Player;
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
