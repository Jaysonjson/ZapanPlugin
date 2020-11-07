package jayson.json.fuchs.data.zcraftingobj.smeltery;

import java.util.ArrayList;

import jayson.json.fuchs.items.AbstractItem;

public class zCraftingSmeltery {

	
	public ArrayList<String> inputID = new ArrayList<String>();
	public String outputID = "";
	
	public transient ArrayList<AbstractItem> input = new ArrayList<AbstractItem>();
	public transient AbstractItem output = null;
	
}
