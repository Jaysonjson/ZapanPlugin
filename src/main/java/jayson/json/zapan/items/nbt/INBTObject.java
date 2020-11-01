package jayson.json.zapan.items.nbt;

public interface INBTObject {
    NBTType getType();
    boolean changeAble();
    Object defaultValue();
}
