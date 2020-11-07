package jayson.json.fuchs.data.zareaobj;

import org.bukkit.World;

public enum zWorld {
    OVERWORLD(World.Environment.NORMAL),
    NETHER(World.Environment.NETHER),
    END(World.Environment.THE_END);

    World.Environment environment;
    zWorld(World.Environment environment) {
        this.environment = environment;
    }

    public World.Environment getEnvironment() {
        return environment;
    }
}
