package jayson.json.zapan.data.zareaobj;

import org.bukkit.World;

public enum WORLD {
    OVERWORLD(World.Environment.NORMAL),
    NETHER(World.Environment.NETHER),
    END(World.Environment.THE_END);

    World.Environment environment;
    WORLD(World.Environment environment) {
        this.environment = environment;
    }

    public World.Environment getEnvironment() {
        return environment;
    }
}
