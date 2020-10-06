package jayson.json.zapan.items.lists;

import jayson.json.zapan.items.interfaces.IzItemRegistry;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemRegistry {
    public static ArrayList<IzItemRegistry> items = new ArrayList<>();

    public static void reloadItems() {
        items.addAll(Arrays.asList(zItemAbility.values()));
        items.addAll(Arrays.asList(zItem.values()));
    }
}
