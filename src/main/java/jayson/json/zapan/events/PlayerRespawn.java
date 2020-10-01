package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Utility.refreshHearts(event.getPlayer());
    }
}
