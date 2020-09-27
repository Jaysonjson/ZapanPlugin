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
    public boolean breakBlocks = true;
    public boolean spawnMobs = true;
    public Location CreateLocation(World world) {
        return new Location(world, location.x, location.y, location.z);
    }

}
