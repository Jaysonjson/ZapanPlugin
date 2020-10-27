package jayson.json.zapan.data;

import jayson.json.zapan.data.zareaobj.zWorld;
import jayson.json.zapan.data.zareaobj.zLocation;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class zArea {

    public UUID uuid;
    public String displayName;
    public zLocation location = new zLocation();
    public int size;
    public UUID owner;
    public zWorld world = zWorld.OVERWORLD;
    public boolean breakBlocks = false;
    public boolean spawnMobs = false;
    public boolean dropItems = false;
    public boolean placeBlocks = false;
    public boolean allowOverlap = false;
    public int priority = 0;
    public Location createLocation(World world) {
        return new Location(world, location.x, location.y, location.z);
    }

}
