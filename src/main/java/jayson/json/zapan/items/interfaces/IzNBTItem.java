package jayson.json.zapan.items.interfaces;

import java.util.HashMap;
import java.util.UUID;

public interface IzNBTItem extends IzItem {
    HashMap<String, String> strings = new HashMap<>();
    HashMap<String, Integer> ints = new HashMap<>();
    HashMap<String, UUID> uuids = new HashMap<>();
    HashMap<String, Double> doubles = new HashMap<>();
    HashMap<String, Boolean> booleans = new HashMap<>();

    HashMap<String, String> getNBTStrings();
    HashMap<String, Integer> getNBTInts();
    HashMap<String, UUID> getNBTUUIDS();
    HashMap<String, Double> getNBTDoubles();
    HashMap<String, Boolean> getNBTBooleans();
}
