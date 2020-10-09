package jayson.json.zapan.data;

import com.google.gson.annotations.Expose;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zguildobj.zPlayerClass;
import jayson.json.zapan.data.zplayerobj.*;
import jayson.json.zapan.skillclass.zClass;

import javax.annotation.Nullable;
import java.util.UUID;

public class zPlayer {

    private zHealth health = new zHealth();

    private transient UUID uuid;

    private UUID guildUuid = null;

    private zStats stats = new zStats();

    private zLevel level = new zLevel();

    private zPlayerClass playerClass = new zPlayerClass();


    public zPlayerClass getPlayerClass() {
        return playerClass;
    }

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

    public boolean isInGuild() {
        if(!Utility.guildExists(getGuildUuid())) {
            setGuildUuid(null);
        }
        return getGuildUuid() != null;
    }

    public void setLevel(zLevel level) {
        this.level = level;
    }

    public zLevel getLevel() {
        return level;
    }
}
