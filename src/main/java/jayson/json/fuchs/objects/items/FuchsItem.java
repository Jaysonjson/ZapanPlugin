package jayson.json.fuchs.objects.items;

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
	private net.minecraft.server.v1_16_R2.ItemStack nmsStack = null;
	
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
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setString(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeIntTag(String key, int value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setInt(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeDoubleTag(String key, Double value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setDouble(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeBooleanTag(String key, Boolean value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setBoolean(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeFloatTag(String key, Float value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setFloat(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeByteTag(String key, Byte value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setByte(key, value);
        updateOriginalTag(tagI);
	}
	
	public void changeShortTag(String key, Short value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setShort(key, value);
        updateOriginalTag(tagI);
	}
	
	public NBTTagCompound getTagFromOriginal() {
        updateNMSStack();
        NBTTagCompound tag = nmsStack.getTag();
        return tag;
	}
	
	public double getDoubleFromTag(String key) {
		NBTTagCompound tagI = getTagFromOriginal();
		return tagI.getDouble(key);
	}
	
	public int getIntFromTag(String key) {
		NBTTagCompound tagI = getTagFromOriginal();
		return tagI.getInt(key);
	}
	
	public String getStringFromTag(String key) {
		NBTTagCompound tagI = getTagFromOriginal();
		return tagI.getString(key);
	}
	
	public float getFloatFromTag(String key) {
		NBTTagCompound tagI = getTagFromOriginal();
		return tagI.getFloat(key);
	}
	
	public byte getByteFromTag(String key) {
		NBTTagCompound tagI = getTagFromOriginal();
		return tagI.getByte(key);
	}
	
	public void updateOriginalTag(NBTTagCompound tagCompound) {
        nmsStack.setTag(tagCompound);
        original = CraftItemStack.asBukkitCopy(nmsStack);
        original = item.createItem(original);
	}
	
	public void updateNMSStack() {
		 nmsStack = Utility.createNMSCopy(original);
	}
	
	public net.minecraft.server.v1_16_R2.ItemStack getNmsStack() {
		return nmsStack;
	}
	
	public void setNmsStack(net.minecraft.server.v1_16_R2.ItemStack nmsStack) {
		this.nmsStack = nmsStack;
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
