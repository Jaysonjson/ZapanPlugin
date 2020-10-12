package jayson.json.zapan.data;

import java.util.UUID;

public interface IHasUUID {
    UUID getUuid();
    void setUuid(UUID uuid);
    UUID randomUuid();
}
