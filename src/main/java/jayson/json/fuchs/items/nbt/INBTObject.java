package jayson.json.fuchs.items.nbt;

public interface INBTObject {
    NBTType getType();
    boolean changeAble();
    Object defaultValue();
}
