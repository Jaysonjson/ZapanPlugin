package jayson.json.fuchs.items;

import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jayson.json.fuchs.Utility;
import net.minecraft.server.v1_16_R2.NBTTagCompound;

public class FuchsItem {
	
	private AbstractItem item;
	private int amount = 1;
	private Player player = null;
	private ItemStack original = null;
	private boolean vanillaOverride = false;
	public FuchsItem(AbstractItem item) {
		this.item = item;
	}
	
	public boolean isVanillaOverride() {
		return vanillaOverride;
	}
	
	public void setVanillaOverride(boolean vanillaOverride) {
		this.vanillaOverride = vanillaOverride;
	}
	
	public FuchsItem(AbstractItem item, Player player) {
		this.item = item;
		this.player = player;
	}
	
	public FuchsItem(AbstractItem item, ItemStack itemStack) {
		this.item = item;
		this.original = itemStack;
	}
	
	public FuchsItem(AbstractItem item, Player player, ItemStack itemStack) {
		this.item = item;
		this.player = player;
		this.original = itemStack;
	}
	
	public void changeStringTag(String key, String value) {
        net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(original);
        NBTTagCompound tagI = nmsStack.getTag();
        tagI.setString(key, value);
        nmsStack.setTag(tagI);
        original = CraftItemStack.asBukkitCopy(nmsStack);
        original = item.createItem(original);
	}
	
	public void changeIntTag(String key, int value) {
        net.minecraft.server.v1_16_R2.ItemStack nmsStack = Utility.createNMSCopy(original);
        NBTTagCompound tagI = nmsStack.getTag();
        tagI.setInt(key, value);
        nmsStack.setTag(tagI);
        original = CraftItemStack.asBukkitCopy(nmsStack);
        original = item.createItem(original);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setOriginal(ItemStack original) {
		this.original = original;
	}
	
	public ItemStack getOriginal() {
		return original;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public ItemStack getItemStack() {
		ItemStack itemStack = item.createItem(player, original);
		itemStack.setAmount(amount);
		return itemStack;
	}
	
	public void setItem(AbstractItem item) {
		this.item = item;
	}
	
	public AbstractItem getItem() {
		return item;
	}
	
}
