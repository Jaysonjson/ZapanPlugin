package jayson.json.fuchs.objects.liquid;


import javax.annotation.Nullable;

import org.bukkit.Material;

import jayson.json.fuchs.objects.liquid.interfaces.IzLiquidRegistry;
import jayson.json.fuchs.objects.liquid.obj.*;
import jayson.json.fuchs.objects.liquid.obj.metal.*;

public enum zLiquid implements IzLiquidRegistry {

	NONE(new NoneLiquid(), null),
	BEER(new BeerLiquid(), null),
	ETHANOL(new EthanolLiquid(), null),
	MIXXED(new MixxedLiquid(), null),
	MOLTEN_COPPER(new MoltenCopperLiquid(), null),
	MOLTEN_IRON(new MoltenIronLiquid(), null),
	MOLTEN_GOLD(new MoltenGoldLiquid(), null),
	LAVA(new LavaLiquid(), Material.LAVA),
	WATER(new WaterLiquid(), Material.WATER);
	
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
