package jayson.json.zapan.other;

import java.util.ArrayList;

public class InventoryPageContainer {
    ArrayList<InventoryPage> pages = new ArrayList<>();
    public InventoryPage addPage(InventoryPage page) {
        page.index = size();
        pages.add(page);
        return page;
    }
    InventoryPage getPage(Integer index) {
        return this.pages.get(index);
    }
    public Integer size() {
        return pages.size() + 1;
    }
}
