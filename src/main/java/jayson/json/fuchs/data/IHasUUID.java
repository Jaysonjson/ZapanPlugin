package jayson.json.fuchs.data;

import java.util.UUID;

public interface IHasUUID {
    UUID getUUID();
    void setUUID(UUID uuid);
    UUID randomUUID();
}
