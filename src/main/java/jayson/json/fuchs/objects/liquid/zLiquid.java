package jayson.json.fuchs.objects.liquid;


import javax.annotation.Nullable;

import org.bukkit.Material;

import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;
import jayson.json.fuchs.objects.liquid.obj.*;
public enum zLiquid implements IzLiquidRegistry {

	NONE(new NoneLiquid(), null),
	BEER(new BeerLiquid(), null),
	WATER(null, Material.WATER);
	
	Material minecraftEquivalent;
	AbstractLiquid liquid;
	zLiquid(AbstractLiquid liquid, Material minecraftEquivalent) {
		this.liquid = liquid;
		this.minecraftEquivalent = minecraftEquivalent;
	}
	
	@Override
	@Nullable
	public Material getMinecraftEquivalent() {
		return minecraftEquivalent;
	}
	
	@Override
	@Nullable
	public AbstractLiquid getLiquid() {
		return liquid;
	}
}
