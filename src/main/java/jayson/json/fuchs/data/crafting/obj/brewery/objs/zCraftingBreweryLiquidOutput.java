package jayson.json.fuchs.data.crafting.obj.brewery.objs;

import jayson.json.fuchs.objects.items.AbstractItem;

public class zCraftingBreweryLiquidOutput {
    public String liquidOutputID = "";
    public double liquidAmount = 0.0;


    private transient AbstractItem liquidOutput;
    
    public AbstractItem getLiquidOutput() {
		return liquidOutput;
	}
}
