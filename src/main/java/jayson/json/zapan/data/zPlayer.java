package jayson.json.zapan.data;

import com.google.gson.annotations.Expose;
import jayson.json.zapan.data.zplayerobj.*;

import javax.annotation.Nullable;
import java.util.UUID;

public class zPlayer {

    private zHealth health = new zHealth();

    private transient UUID uuid;

    private UUID guildUuid = null;


    public zHealth GetHealth() {
        return health;
    }

    public void SetHealth(zHealth health) {
        this.health = health;
    }

    public UUID GetUuid() {
        return uuid;
    }

    public void SetUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Nullable
    public UUID GetGuildUuid() {
        return guildUuid;
    }

    public void SetGuildUuid(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }
}
