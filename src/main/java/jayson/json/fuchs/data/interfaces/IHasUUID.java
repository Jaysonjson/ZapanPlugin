package jayson.json.fuchs.data.interfaces;

import java.util.UUID;

public interface IHasUUID {
    UUID getUUID();
    void setUUID(UUID uuid);
    UUID randomUUID();
}
