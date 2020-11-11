package jayson.json.fuchs.data.zguildobj;

import jayson.json.fuchs.skillclass.*;
import jayson.json.fuchs.skillclass.alchemist.zAlchemistClass;
import jayson.json.fuchs.skillclass.fighter.zFighterClass;
import org.jetbrains.annotations.NotNull;

public class zPlayerClass {
    public zClass current = zClass.NONE;
    public zDefaultClass defaultData = new zDefaultClass();
    public zAlchemistClass alchemistData = new zAlchemistClass();
    public zFighterClass fighterData = new zFighterClass();
    public transient zAbstractClass abstractClass = current.getAbstractClass();

    @Deprecated
    public void changeClass(zClass zclass) {
        current = zclass;
        abstractClass = current.getAbstractClass();
    }

    @NotNull
    public zFighterClass getFighterData() {
        return fighterData;
    }

    @NotNull
    public zAlchemistClass getAlchemistData() {
        return alchemistData;
    }

    public Object getData() {
        Object obj = defaultData;
        switch (current) {
            case FIGHTER:
                obj = fighterData;
                break;
            case ALCHEMIST:
                obj = alchemistData;
                break;
		default:
			obj = defaultData;
			break;
        }
        return obj;
    }
}
