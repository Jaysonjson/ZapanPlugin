package jayson.json.zapan.events;

import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class ChatEvent implements Listener {

    @EventHandler(priority= EventPriority.LOWEST)
    public void Chat(AsyncPlayerChatEvent event) {
        String guildName = "";
        zPlayer zPlayer = DataHandler.loadPlayer(event.getPlayer().getUniqueId());
        if(zPlayer.isInGuild()) {
            guildName = "[" + ChatColor.AQUA + DataHandler.loadGuild(zPlayer.getGuildUuid()).name + ChatColor.RESET + "] ";
        }
        event.setFormat(guildName + event.getPlayer().getDisplayName() + "§7: " + event.getMessage());
    }

}
