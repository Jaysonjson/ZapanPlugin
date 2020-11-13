package jayson.json.fuchs.events;

import jayson.json.fuchs.data.player.data.zPlayer;
import jayson.json.fuchs.data.io.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler(priority= EventPriority.LOWEST)
    public void Chat(AsyncPlayerChatEvent event) {
        String guildName = "";
        zPlayer zPlayer = DataHandler.loadPlayer(event.getPlayer().getUniqueId());
        if(zPlayer.isInGuild()) {
            guildName = "[" + ChatColor.AQUA + DataHandler.loadGuild(zPlayer.getGuildUuid()).getName() + ChatColor.RESET + "] ";
        }
        event.setFormat(guildName + event.getPlayer().getDisplayName() + "§7: " + event.getMessage());
    }

}
