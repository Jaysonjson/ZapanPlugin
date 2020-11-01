package jayson.json.zapan.items.nbt;

public class NBTInteger implements INBTObject {

    private final boolean changeAble;
    private final int defaultValue;
    public NBTInteger(int defaultValue, boolean changeAble) {
        this.defaultValue = defaultValue;
        this.changeAble = changeAble;
    }

    @Override
    public NBTType getType() {
        return NBTType.INTEGER;
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
