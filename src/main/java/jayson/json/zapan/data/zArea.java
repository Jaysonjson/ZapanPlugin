package jayson.json.zapan.data;

import jayson.json.zapan.data.zareaobj.WORLD;
import jayson.json.zapan.data.zareaobj.zLocation;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class zArea {

    public String name;
    public zLocation location = new zLocation();
    public int size;
    public UUID owner;
    public WORLD world = WORLD.OVERWORLD;
    public boolean breakBlocks = false;
    public boolean spawnMobs = false;
    public boolean dropItems = false;
    public boolean placeBlocks = false;

    public Location createLocation(World world) {
        return new Location(world, location.x, location.y, location.z);
    }

}
