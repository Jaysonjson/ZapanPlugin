package jayson.json.fuchs.items.nbt;

public class NBTUUID implements INBTObject {

    private final boolean changeAble;
    private final String defaultValue;
    public NBTUUID(String defaultValue, boolean changeAble) {
        this.defaultValue = defaultValue;
        this.changeAble = changeAble;
    }

    @Override
    public NBTType getType() {
        return NBTType.UUID;
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
