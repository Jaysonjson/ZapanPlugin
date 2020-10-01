package jayson.json.zapan.other;

public class InventoryPage {
    String content;
    Integer index;
    public InventoryPage(String content, Integer index) {
        this.content = content;
        this.index = index;
    }
    public String getContent() {
        return content;
    }
    Integer getIndex() {
        return index;
    }
}
