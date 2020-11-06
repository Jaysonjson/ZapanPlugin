package jayson.json.zapan.skillclass.fighter;

import jayson.json.zapan.skillclass.zAbstractClass;

public class zFighterClass extends zAbstractClass {
    public zFighterSubClass subClass = zFighterSubClass.DPS;

    public zFighterSubClass getSubClass() {
        return subClass;
    }
}
