package jayson.json.zapan.events;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.other.Scoreboard;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Random;

public class PlayerMove implements Listener {

    private ArrayList<Player> players = new ArrayList<>();


    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        Random random = new Random();
        if(zPlayer.getPlayerSpecial().alcohol > 0) {
            Utility.makeDrunk(player, zPlayer);
            if(random.nextInt(250) == 1) {
                zPlayer.getPlayerSpecial().alcohol -= random.nextDouble() / 10;
            }
        }
        Scoreboard.updateScoreboard(player, zPlayer);
        zArea area = Utility.getNearestArea(event.getPlayer().getWorld().getEnvironment(), player.getLocation());
        Location locationP0 = area.createLocation(player.getWorld()).add(area.size, area.size, area.size);
        Location locationP1 = area.createLocation(player.getWorld()).subtract(area.size, area.size, area.size);
        if (Utility.isInArea(player.getLocation(), locationP0, locationP1)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(area.displayName.toUpperCase()).color(ChatColor.DARK_PURPLE).create());
            if (!players.contains(player)) {
                //player.sendMessage("Du bist jetzt im Gebiet " + area.name + "!");
                players.add(player);
            }
        } else {
            if (players.contains(player)) {
                //player.sendMessage("Du hast das Gebiet " + area.name + " verlassen!");
                players.remove(player);
            }
        }
        DataHandler.savePlayer(zPlayer);
    }
}
