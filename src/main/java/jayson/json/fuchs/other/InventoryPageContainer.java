package jayson.json.fuchs.other;

import java.util.ArrayList;

public class InventoryPageContainer<T> {
    @SuppressWarnings("rawtypes")
	public ArrayList<InventoryPage> pages = new ArrayList<>();
    public InventoryPage<T> addPage(InventoryPage<T> page) {
        page.index = size();
        pages.add(page);
        return page;
    }
    @SuppressWarnings("unchecked")
	public InventoryPage<T> getPage(Integer index) {
        return this.pages.get(index);
    }
    public Integer size() {
        return pages.size() + 1;
    }
}
