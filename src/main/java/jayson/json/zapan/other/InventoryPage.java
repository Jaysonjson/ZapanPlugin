package jayson.json.zapan.other;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryPage<T> {
    T content;
    public ItemStack[] stacks;
    Integer index;
    public InventoryPage(T content, Integer index) {
       /* if(this.content instanceof ArrayList) {
            ArrayList<?> st = (ArrayList<?>) content;
            for (Object o : st) {
                ((ArrayList<Object>) this.content).add(o);
            }
        } else {
            this.content = content;
        }*/

        this.index = index;
    }

    public ItemStack[] getStacks() {
        return stacks;
    }

    public T getContent() {
        return content;
    }
    Integer getIndex() {
        return index;
    }
}

