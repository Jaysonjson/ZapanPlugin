package jayson.json.zapan;

import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class Utility {

    public static void RefreshHearts(Player player) {
        zPlayer zPlayer = DataHandler.LoadPlayer(player.getUniqueId());
        RefreshHearts(player, zPlayer);
    }

    public static void RefreshHearts(Player player, zPlayer zPlayer) {
        if(zPlayer.GetHealth().health > 2048) {
            zPlayer.GetHealth().health = 2048;
        }
        if(zPlayer.GetHealth().health <= 0) {
            zPlayer.GetHealth().health = 2;
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(zPlayer.GetHealth().health);
        player.setHealth(zPlayer.GetHealth().health);
    }

    public static boolean IsInArea(Location location, Location locationP0, Location locationP1) {
        double maxX = (Math.max(locationP0.getX(), locationP1.getX()));
        double minX = (Math.min(locationP0.getX(), locationP1.getX()));

        double maxY = (Math.max(locationP0.getY(), locationP1.getY()));
        double minY = (Math.min(locationP0.getY(), locationP1.getY()));

        double maxZ = (Math.max(locationP0.getZ(), locationP1.getZ()));
        double minZ = (Math.min(locationP0.getZ(), locationP1.getZ()));

        if(location.getX() <= maxX && location.getX() >= minX) {
            if(location.getY() <= maxY && location.getY() >= minY) {
                return location.getZ() <= maxZ && location.getZ() >= minZ;
            }
        }
        return false;
    }


    public static zArea GetNearestArea(Location location) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        for (zArea areas : Zapan.INSTANCE.areas) {
            distances.put(location.getX() - areas.location.x, areas);
            distancesD.add(location.getX() - areas.location.x);
        }
        Collections.sort(distancesD);
        Collections.reverse(distancesD);
        return distances.get(distancesD.get(0));
    }

    public static boolean IsInSpawnArea(Location location, World world) {
        zArea area = GetNearestArea(location);
        if(area.name.toLowerCase() == "spawn") {
            Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
            Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
            if (Utility.IsInArea(location, locationP0, locationP1)) {
                return true;
            }
        }
        return false;
    }
}
