package jayson.json.fuchs.data.area.data;

import jayson.json.fuchs.data.area.obj.zWorld;
import jayson.json.fuchs.data.area.obj.zLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class zArea {

    private UUID uuid;
    private String displayName;
    private zLocation location = new zLocation();
    private int size;
    private UUID owner;
    private zWorld world = zWorld.OVERWORLD;
    private boolean breakBlocks = false;
    private boolean spawnMobs = false;
    private boolean dropItems = false;
    private boolean placeBlocks = false;
    private boolean allowOverlap = false;
    private boolean allowOwnerOverlap = true;
    private int priority = 0;

    public Location createLocation(World world) {
        return new Location(world, location.x, location.y, location.z);
    }

    public boolean canOverlap(Player player) {
        if(player != null) {
            if (player.getUniqueId().equals(owner)) {
                return allowOwnerOverlap;
            }
        }
        return allowOverlap;
    }

    public boolean canOverlap() {
        return canOverlap(null);
    }
    
    public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
    
    public UUID getUuid() {
		return uuid;
	}
    
    public String getDisplayName() {
		return displayName;
	}
    
    public zLocation getLocation() {
		return location;
	}
   
    public UUID getOwner() {
		return owner;
	}

    public int getPriority() {
		return priority;
	}
    
    public int getSize() {
		return size;
	}
    
    public zWorld getWorld() {
		return world;
	}
    
    public void setAllowOverlap(boolean allowOverlap) {
		this.allowOverlap = allowOverlap;
	}
    
    public void setAllowOwnerOverlap(boolean allowOwnerOverlap) {
		this.allowOwnerOverlap = allowOwnerOverlap;
	}
    
    public void setBreakBlocks(boolean breakBlocks) {
		this.breakBlocks = breakBlocks;
	}
    
    public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
    
    public void setDropItems(boolean dropItems) {
		this.dropItems = dropItems;
	}
    
    public void setLocation(zLocation location) {
		this.location = location;
	}
    
    public void setOwner(UUID owner) {
		this.owner = owner;
	}
    
    public void setPlaceBlocks(boolean placeBlocks) {
		this.placeBlocks = placeBlocks;
	}
    
    public void setPriority(int priority) {
		this.priority = priority;
	}
    
    public void setSize(int size) {
		this.size = size;
	}
    
    public void setSpawnMobs(boolean spawnMobs) {
		this.spawnMobs = spawnMobs;
	}
    
    public void setWorld(zWorld world) {
		this.world = world;
	}
    
    public boolean isAllowOverlap() {
		return allowOverlap;
	}
    
    public boolean isAllowOwnerOverlap() {
		return allowOwnerOverlap;
	}
    
    public boolean isBreakBlocks() {
		return breakBlocks;
	}
    
    public boolean isDropItems() {
		return dropItems;
	}
    
    public boolean isPlaceBlocks() {
		return placeBlocks;
	}
    
    public boolean isSpawnMobs() {
		return spawnMobs;
	}
}
