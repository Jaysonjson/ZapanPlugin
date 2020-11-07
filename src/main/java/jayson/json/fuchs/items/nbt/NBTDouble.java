package jayson.json.fuchs.items.nbt;

public class NBTDouble implements INBTObject {

    private final boolean changeAble;
    private final double defaultValue;
    public NBTDouble(double defaultValue, boolean changeAble) {
        this.defaultValue = defaultValue;
        this.changeAble = changeAble;
    }

    @Override
    public NBTType getType() {
        return NBTType.DOUBLE;
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
