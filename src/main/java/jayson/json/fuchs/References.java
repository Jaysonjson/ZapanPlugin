package jayson.json.fuchs;

import jayson.json.fuchs.data.drop.data.zDrops;
import jayson.json.fuchs.io.DataHandler;

public class References {
    public static zDrops drops;

    public static void reloadDrops() {
        drops = DataHandler.loadDrops();
    }
}
