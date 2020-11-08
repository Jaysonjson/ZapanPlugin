package jayson.json.fuchs.objects.items.interfaces;

import jayson.json.fuchs.objects.items.nbt.INBTObject;
import jayson.json.fuchs.objects.items.nbt.NBTBoolean;
import jayson.json.fuchs.objects.items.nbt.NBTString;

import java.util.HashMap;
import java.util.UUID;

public interface IzNBTItem extends IzItem {
    HashMap<String, NBTString> strings = new HashMap<>();
    HashMap<String, Integer> ints = new HashMap<>();
    HashMap<String, UUID> uuids = new HashMap<>();
    HashMap<String, Double> doubles = new HashMap<>();
    HashMap<String, NBTBoolean> booleans = new HashMap<>();

    @Deprecated
    HashMap<String, NBTString> getNBTStrings();
    @Deprecated
    HashMap<String, Integer> getNBTInts();
    @Deprecated
    HashMap<String, UUID> getNBTUUIDS();
    @Deprecated
    HashMap<String, Double> getNBTDoubles();
    @Deprecated
    HashMap<String, NBTBoolean> getNBTBooleans();

    HashMap<String, INBTObject> getNBTObjects();
}
