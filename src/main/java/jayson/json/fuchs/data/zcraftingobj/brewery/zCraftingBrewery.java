package jayson.json.fuchs.data.zcraftingobj.brewery;

import java.util.ArrayList;

import jayson.json.fuchs.data.zcraftingobj.brewery.objs.zCraftingBreweryLiquidInput;
import jayson.json.fuchs.data.zcraftingobj.brewery.objs.zCraftingBreweryLiquidOutput;
import jayson.json.fuchs.items.AbstractItem;

public class zCraftingBrewery {

	
	public ArrayList<String> inputsID = new ArrayList<String>();
	public zCraftingBreweryLiquidInput liquidInput = new zCraftingBreweryLiquidInput();
	public zCraftingBreweryLiquidOutput liquidOutput = new zCraftingBreweryLiquidOutput();
	public int level = 1;
	public double xpGain = 0;
	public transient ArrayList<AbstractItem> inputs = new ArrayList<AbstractItem>();
	
	
	
}
