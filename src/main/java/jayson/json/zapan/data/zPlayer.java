package jayson.json.zapan.data;

import com.google.gson.annotations.Expose;
import jayson.json.zapan.data.zplayerobj.*;

import javax.annotation.Nullable;
import java.util.UUID;

public class zPlayer {

    private zHealth health = new zHealth();

    private transient UUID uuid;

    private UUID guildUuid = null;

    private zStats stats = new zStats();


    public zHealth getHealth() {
        return health;
    }

    public void setHealth(zHealth health) {
        this.health = health;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Nullable
    public UUID getGuildUuid() {
        return guildUuid;
    }

    public void setGuildUuid(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }

    public zStats getStats() {
        return stats;
    }

    public void setStats(zStats stats) {
        this.stats = stats;
    }

}
