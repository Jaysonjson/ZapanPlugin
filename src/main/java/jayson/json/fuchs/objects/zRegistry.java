package jayson.json.fuchs.objects;

import java.util.ArrayList;
import java.util.Arrays;

import jayson.json.fuchs.objects.gas.interfaces.IzGasRegistry;
import jayson.json.fuchs.objects.items.interfaces.IzItemRegistry;
import jayson.json.fuchs.objects.items.lists.zItem;
import jayson.json.fuchs.objects.items.lists.zItemAbility;
import jayson.json.fuchs.objects.liquid.zLiquid;
import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;

public class zRegistry {
	
    public static ArrayList<IzItemRegistry> items = new ArrayList<>();
    public static ArrayList<IzLiquidRegistry> liquids = new ArrayList<>();
    public static ArrayList<IzGasRegistry> gasses = new ArrayList<>();
    
    @Deprecated
    public static void reloadItems() {
        items.addAll(Arrays.asList(zItemAbility.values()));
        items.addAll(Arrays.asList(zItem.values()));
    }

    @Deprecated
    public static void reloadLiquids() {
    	liquids.addAll(Arrays.asList(zLiquid.values()));
    }
    
    @Deprecated
    public static void reloadGasses() {
    	
    }
    
 
    public static void addGas(IzGasRegistry izGasRegistry) {
    	gasses.add(izGasRegistry);
    }
    
    public static <T extends Enum<T>> void addGasses(T[] gasEnum) {
        for (T t : gasEnum) {
            if(t instanceof IzGasRegistry) {
                gasses.add((IzGasRegistry) t);
            }
        }
    }
    
    
    public static void addLiquid(IzLiquidRegistry izLiquidRegistry) {
    	liquids.add(izLiquidRegistry);
    }
    
    public static <T extends Enum<T>> void addLiquids(T[] liquidEnum) {
        for (T t : liquidEnum) {
            if(t instanceof IzLiquidRegistry) {
                liquids.add((IzLiquidRegistry) t);
            }
        }
    }
    
    public static void addItem(IzItemRegistry izItemRegistry) {
        items.add(izItemRegistry);
    }

    public static <T extends Enum<T>> void addItems(T[] itemEnum) {
        for (T t : itemEnum) {
            if(t instanceof IzItemRegistry) {
                items.add((IzItemRegistry) t);
            }
        }
    }
}
