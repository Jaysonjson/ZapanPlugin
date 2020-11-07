package jayson.json.fuchs.items.lists;

import jayson.json.fuchs.items.interfaces.IzItemRegistry;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemRegistry {
    public static ArrayList<IzItemRegistry> items = new ArrayList<>();

    public static void reloadItems() {
        items.addAll(Arrays.asList(zItemAbility.values()));
        items.addAll(Arrays.asList(zItem.values()));
    }

    public static void addItem(IzItemRegistry izItemRegistry) {
        items.add(izItemRegistry);
    }

    public static <T extends Enum<T>> void addItems(T[] itemEnum) {
        for (T t : itemEnum) {
            if(t instanceof IzItemRegistry) {
                items.add((IzItemRegistry) t);
            }
        }
    }
}
