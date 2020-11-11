package jayson.json.fuchs.data.crafting.obj.brewery.objs;

import jayson.json.fuchs.objects.items.AbstractItem;

public class zCraftingBreweryLiquidInput {

	public String liquidInputID = "";
	public double liquidAmount = 0.0;
	
	
	private transient AbstractItem liquidInput;
	
	public AbstractItem getLiquidInput() {
		return liquidInput;
	}
}
