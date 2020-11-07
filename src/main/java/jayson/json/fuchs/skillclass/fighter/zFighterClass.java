package jayson.json.fuchs.skillclass.fighter;

import jayson.json.fuchs.skillclass.zAbstractClass;

public class zFighterClass extends zAbstractClass {
    public zFighterSubClass subClass = zFighterSubClass.DPS;

    public zFighterSubClass getSubClass() {
        return subClass;
    }
}
