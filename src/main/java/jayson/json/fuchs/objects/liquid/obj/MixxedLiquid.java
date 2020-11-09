package jayson.json.fuchs.objects.liquid.obj;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.objects.liquid.AbstractLiquid;

public class MixxedLiquid extends AbstractLiquid {

	private ArrayList<String> containedLiquids = new ArrayList<>();
	
	public ArrayList<String> getContainedLiquids() {
		return containedLiquids;
	}
	
	public void setContainedLiquids(ArrayList<String> containedLiquids) {
		this.containedLiquids = containedLiquids;
	}
	
	public ArrayList<AbstractLiquid> getContainedAbstractLiquids() {
		ArrayList<AbstractLiquid> liquids = new ArrayList<>();
		for(String id : containedLiquids) {
			if(Utility.liquidExists(id)) {
				liquids.add(Utility.getLiquidByID(id));
			}
		}
		return liquids;
	}
	
	@Override
	public String getId() {
		return "mixxed";
	}

	@Override
	public String getDisplayName() {
		String displayName = ChatColor.RESET + "" + ChatColor.WHITE + "Gemischt";
		for(AbstractLiquid liquid : getContainedAbstractLiquids()) {
			displayName += "\n" + liquid.getDisplayName();
		}
		return displayName;
	}
	
	@Override
	public int getDamageValue() {
		return 16;
	}

}
