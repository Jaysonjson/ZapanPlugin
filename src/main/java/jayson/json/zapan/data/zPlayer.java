package jayson.json.zapan.data;

import com.google.gson.annotations.Expose;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zguildobj.zPlayerClass;
import jayson.json.zapan.data.zplayerobj.*;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.skillclass.zClass;

import javax.annotation.Nullable;
import java.util.UUID;

public class zPlayer implements IHasUUID {

    private zHealth health = new zHealth();

    private transient UUID uuid;

    private UUID guildUuid = null;

    private zStats stats = new zStats();

    private zLevel level = new zLevel();

    private zPlayerClass playerClass = new zPlayerClass();

    private zPlayerSpecial playerSpecial = new zPlayerSpecial();

    public zPlayerClass getPlayerClass() {
        return playerClass;
    }

    public zPlayerSpecial getPlayerSpecial() {
        return playerSpecial;
    }

    public zHealth getHealth() {
        return health;
    }

    public void setHealth(zHealth health) {
        this.health = health;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return UUID.randomUUID();
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
        if(getGuildUuid() != null) {
            if (!Utility.guildExists(getGuildUuid())) {
                setGuildUuid(null);
            }
            return true;
        }
        return false;
    }

    public void setLevel(zLevel level) {
        this.level = level;
    }

    public zLevel getLevel() {
        return level;
    }
}
