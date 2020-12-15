package jayson.json.fuchs.handler.uuid;

public class UUIDData implements IUUIDData {

    String path;
    public UUIDData(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return path;
    }

}
