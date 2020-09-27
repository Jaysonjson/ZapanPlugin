package jayson.json.zapan.data;

import com.google.gson.annotations.Expose;
import jayson.json.zapan.data.zplayerobj.*;

import java.util.UUID;

public class zPlayer {

    private zHealth health = new zHealth();

    private transient UUID uuid;



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

}
