package jayson.json.zapan.data.zguildobj;

import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.skillclass.*;
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
        }
        return obj;
    }
}
