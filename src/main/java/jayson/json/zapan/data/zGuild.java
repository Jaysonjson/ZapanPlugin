package jayson.json.zapan.data;

import jayson.json.zapan.data.zguildobj.zGuildRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class zGuild {
    public UUID owner;
    public String name = "";
    public HashMap<UUID, zGuildRank> members = new HashMap<>();
    public UUID uuid;
}
