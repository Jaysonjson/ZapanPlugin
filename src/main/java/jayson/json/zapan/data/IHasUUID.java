package jayson.json.zapan.data;

import java.util.UUID;

public interface IHasUUID {
    UUID getUUID();
    void setUUID(UUID uuid);
    UUID randomUUID();
}
