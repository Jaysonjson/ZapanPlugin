package jayson.json.fuchs.data.crafting.obj.smeltery;

import java.util.ArrayList;

import jayson.json.fuchs.objects.items.AbstractItem;

public class zCraftingSmeltery {

	
	public ArrayList<String> inputID = new ArrayList<String>();
	public String outputID = "";
	
	public transient ArrayList<AbstractItem> input = new ArrayList<AbstractItem>();
	public transient AbstractItem output = null;
	
}
