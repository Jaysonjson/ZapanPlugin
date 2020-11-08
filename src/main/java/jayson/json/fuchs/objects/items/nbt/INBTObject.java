package jayson.json.fuchs.objects.items.nbt;

public interface INBTObject {
    NBTType getType();
    boolean changeAble();
    Object defaultValue();
}
