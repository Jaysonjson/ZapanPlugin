package jayson.json.fuchs.skillclass.alchemist;

import jayson.json.fuchs.skillclass.zAbstractClass;

import java.util.ArrayList;

public class zAlchemistClass extends zAbstractClass {

    public ArrayList<String> learnedItems = new ArrayList<>();

    public ArrayList<String> getLearnedItems() {
        return learnedItems;
    }
}
