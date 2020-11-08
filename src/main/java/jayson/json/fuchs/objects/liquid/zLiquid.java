package jayson.json.fuchs.objects.liquid;

import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;

public enum zLiquid implements IzLiquidRegistry {

	WATER(null);
	
	AbstractLiquid liquid;
	zLiquid(AbstractLiquid liquid) {
		this.liquid = liquid;
	}
	
	@Override
	public AbstractLiquid getLiquid() {
		return liquid;
	}
}
