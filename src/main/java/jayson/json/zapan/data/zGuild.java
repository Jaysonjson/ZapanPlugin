package jayson.json.zapan.data;

import jayson.json.zapan.data.zguildobj.zGuildBanner;
import jayson.json.zapan.data.zguildobj.zGuildRank;
import java.util.HashMap;
import java.util.UUID;

public class zGuild {

    public UUID owner;
    public String name = "";
    public HashMap<UUID, zGuildRank> members = new HashMap<>();
    public UUID uuid;
    public zGuildBanner banner = new zGuildBanner();
}
