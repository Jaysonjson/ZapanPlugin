package jayson.json.fuchs.events.entity.player;

import jayson.json.fuchs.data.zPlayer;
import jayson.json.fuchs.io.DataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerSleep implements Listener {

    @EventHandler
    public void PlayerSleep(PlayerBedLeaveEvent event) {
        zPlayer zPlayer = DataHandler.loadPlayer(event.getPlayer().getUniqueId());
        if(zPlayer.getPlayerSpecial().alcohol > 0) {
            zPlayer.getPlayerSpecial().alcohol /= 0.5;
            DataHandler.savePlayer(zPlayer);
        }

    }
}
