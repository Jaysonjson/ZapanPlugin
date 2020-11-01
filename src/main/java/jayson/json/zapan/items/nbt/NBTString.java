package jayson.json.zapan.items.nbt;

public class NBTString implements INBTObject {

    private final boolean changeAble;
    private final boolean defaultValue;
    public NBTString(boolean defaultValue, boolean changeAble) {
        this.defaultValue = defaultValue;
        this.changeAble = changeAble;
    }

    @Override
    public NBTType getType() {
        return NBTType.STRING;
    }

    @Override
    public boolean changeAble() {
        return changeAble;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }
}
