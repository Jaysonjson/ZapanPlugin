package jayson.json.zapan;

import jayson.json.zapan.data.zDrops;
import jayson.json.zapan.io.DataHandler;

public class References {
    public static zDrops drops;

    public static void reloadDrops() {
        drops = DataHandler.loadDrops();
    }
}
