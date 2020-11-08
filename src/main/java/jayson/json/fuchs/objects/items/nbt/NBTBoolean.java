package jayson.json.fuchs.objects.items.nbt;

public class NBTBoolean implements INBTObject {

    private final boolean changeAble;
    private final boolean defaultValue;
    public NBTBoolean(boolean defaultValue, boolean changeAble) {
        this.defaultValue = defaultValue;
        this.changeAble = changeAble;
    }

    @Override
    public NBTType getType() {
        return NBTType.BOOLEAN;
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
