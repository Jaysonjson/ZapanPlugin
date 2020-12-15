package jayson.json.fuchs.objects.liquid.interfaces;

import javax.annotation.Nullable;

import org.bukkit.Material;

import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public interface IzLiquidRegistry {
	
	AbstractLiquid getLiquid();
	@Nullable
	Material getMinecraftEquivalent();
	
}
