package jayson.json.zapan;

import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.IzItem;
import jayson.json.zapan.items.zItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
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
           // distances.put((location.getX() - areas.location.x), areas);
           // distancesD.add((location.getX() - areas.location.x));
            double distance = location.distance(areas.CreateLocation(location.getWorld()));
            distances.put(distance, areas);
            distancesD.add(distance);
        }
        Collections.sort(distancesD);
        //Collections.reverse(distancesD);
        return distances.get(distancesD.get(0));
    }

    @Deprecated
    public static zLocation GetNearestAreaDistanceDEP(Location location) {
        ArrayList<Double> distancesX = new ArrayList<>();
        ArrayList<Double> distancesZ = new ArrayList<>();
        for (zArea areas : Zapan.INSTANCE.areas) {
            distancesX.add(location.getX() - areas.location.x);
            distancesZ.add(location.getZ() - areas.location.z);
        }
        Collections.sort(distancesX);
        Collections.reverse(distancesX);
        Collections.sort(distancesZ);
        Collections.reverse(distancesZ);
        return new zLocation(distancesX.get(0), 0, distancesZ.get(0));
    }

    public static zLocation GetNearestAreaDistance(Location location) {
        zArea area = GetNearestArea(location);
        //return new zLocation(area.location.x - location.getX() - area.size, 0, area.location.z - location.getZ() - area.size);
        return new zLocation(area.location.x - location.getX(), 0, area.location.z - location.getZ());
    }

    public static boolean AreaOverlap(Location locationP1, Location locationP2, Location locationBP1, Location locationBP2)
    {
        boolean XCol = IsAreaBetween(locationP1.getX(), locationP2.getX(), locationBP1.getX()) || IsAreaBetween(locationP1.getX(), locationP2.getX(), locationBP2.getX());
        boolean YCol = IsAreaBetween(locationP1.getY(), locationP2.getY(), locationBP1.getY()) || IsAreaBetween(locationP1.getY(), locationP2.getY(), locationBP2.getY());
        boolean ZCol = IsAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP1.getZ()) || IsAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP2.getZ());

        return XCol && YCol && ZCol;
    }

    public static boolean IsAreaBetween(double point1, double point2, double target) {
        if(point1 < point2) {
            return target >= point1 && target <= point2;
        } else {
            return target >= point2 && target <= point1;
        }
    }

    public static boolean IsInSpawnArea(Location location, World world) {
        zArea area = GetNearestArea(location);
        if(area.name.toLowerCase().equals("spawn")) {
            Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
            Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
            return Utility.IsInArea(location, locationP0, locationP1);
        }
        return false;
    }

    public static boolean CanBreakBlock(Player player, Location location, World world) {
        zArea area = GetNearestArea(location);
        Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.IsInArea(location, locationP0, locationP1)) {
            return area.breakBlocks || player.isOp();
        }
        return true;
    }

    public static boolean CanPlaceBlock(Player player, Location location, World world) {
        zArea area = GetNearestArea(location);
        Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.IsInArea(location, locationP0, locationP1)) {
            return area.placeBlocks || player.isOp();
        }
        return true;
    }

    public static boolean CanEntitySpawn(Location location, World world) {
        zArea area = GetNearestArea(location);
        Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.IsInArea(location, locationP0, locationP1)) {
            return area.spawnMobs;
        }
        return true;
    }

    public static boolean CanDropItem(Player player, Location location, World world) {
        zArea area = GetNearestArea(location);
        Location locationP0 = area.CreateLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.CreateLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.IsInArea(location, locationP0, locationP1)) {
            return area.dropItems || player.isOp();
        }
        return true;
    }

    public static void SpawnCustomItem(IzItem item, World world, Location location) {
        world.dropItemNaturally(location, item.GetItem());
    }

    public static double CountMoney(Player player) {
        double amount = 0;
        for (ItemStack content : player.getInventory().getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(content);
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if (tag.hasKey(zItem.CONST_CURRENCY_AMOUNT)) {
                        amount += (tag.getDouble(zItem.CONST_CURRENCY_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    public static void ReloadAreas() {
        Zapan.INSTANCE.areas.clear();

        for (File file : new File(DataHandler.AREA_DIR).listFiles()) {
            zArea area = DataHandler.LoadArea(file.getName().replaceAll(".json", ""));
            Zapan.INSTANCE.areas.add(area);
            DataHandler.SaveArea(area);
        }
    }

    public static boolean GuildExists(UUID uuid) {
        return new File(DataHandler.GUILD_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean GuildExists(String name) {
        for (File file : new File(DataHandler.GUILD_DIR).listFiles()) {
            zGuild guild = DataHandler.LoadGuild(file.getName().replaceAll(".json", ""));
            if(guild.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean AreaExists(String name) {
        return new File(DataHandler.AREA_DIR + name.toLowerCase() + ".json").exists();
    }

}
