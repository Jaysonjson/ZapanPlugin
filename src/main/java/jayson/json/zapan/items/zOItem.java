package jayson.json.zapan.items;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.nbt.INBTObject;
import jayson.json.zapan.items.nbt.NBTType;
import jayson.json.zapan.skillclass.alchemist.zAlchemistClass;
import jayson.json.zapan.skillclass.zClass;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class zOItem {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public String id;
    public net.minecraft.server.v1_16_R2.ItemStack nmsCopy;
    public AbstractItem zItem;
    public Player player = null;
    public Boolean textureDamage = false;
    NBTTagCompound preTag;

    HashMap<String, String> strings = new HashMap<>();
    HashMap<String, Integer> ints = new HashMap<>();
    HashMap<String, UUID> uuids = new HashMap<>();
    HashMap<String, Double> doubles = new HashMap<>();
    HashMap<String, Boolean> booleans = new HashMap<>();

    @Deprecated
    public zOItem(AbstractItem zItem, ItemStack itemStack, ArrayList<String> lore, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = lore;
        this.id = id;
        this.zItem = zItem;
    }

    @Deprecated
    public zOItem(AbstractItem zItem, ItemStack itemStack, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
        this.zItem = zItem;
    }

    @Deprecated
    public zOItem(AbstractItem zItem, Player player, ItemStack itemStack, String id) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
        this.zItem = zItem;
        this.player = player;
    }

    public zOItem(AbstractItem zItem, Player player, ItemStack itemStack, String id, Boolean textureDamage) {
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = id;
        this.zItem = zItem;
        this.player = player;
        this.textureDamage = textureDamage;
    }

    @Deprecated
    public zOItem(AbstractItem zItem, Player player, Boolean textureDamage) {
        this.item = new ItemStack(zItem.getMaterial());
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = zItem.getId();
        this.zItem = zItem;
        this.player = player;
        this.textureDamage = textureDamage;
    }

    @Deprecated
    public zOItem(AbstractItem zItem, Player player) {
        this.item = new ItemStack(zItem.getMaterial());
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = zItem.getId();
        this.zItem = zItem;
        this.player = player;
        this.textureDamage = false;
    }

    @Deprecated
    public void init() {
        nmsCopy = createNMSCopy();
    }


    //@Deprecated
    public void setItem(String displayName) {
        preTag = Utility.getItemTag(item);
        //item = CraftItemStack.asBukkitCopy(nmsCopy);
            try {
                /*
                switch (zItem.getItemUseType()) {
                    case CRAFTING:
                        lore.add(ChatColor.AQUA + "Herstellungsmaterial");
                        break;
                    case CURRENCY:
                        lore.add(ChatColor.AQUA + "Währung");
                        break;
                    case ABILITY:
                        lore.add(ChatColor.AQUA + "Benutzbar");
                        break;
                }
                 */
                if(!zItem.getItemUseType().getLoreText().equalsIgnoreCase("")) {
                    lore.add(zItem.getItemUseType().getLoreText());
                }
                if (player != null) {
                    zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
                    if(zItem.requiredIntelligence() > 0) {
                        if(zPlayer.getStats().getIntelligence() >= 5) {
                            lore.add(ChatColor.GREEN + "Benötigt Intelligenz " + zItem.requiredIntelligence());
                        } else {
                            lore.add(ChatColor.RED + "Benötigt Intelligenz " + zItem.requiredIntelligence() + " (Du hast: " + zPlayer.getStats().getIntelligence() + ")");
                        }
                    }
                    if (zPlayer.getPlayerClass().current.equals(zClass.ALCHEMIST)) {
                        lore.add("");
                        zAlchemistClass alchemistClass = zPlayer.getPlayerClass().getAlchemistData();
                        if (alchemistClass.getLearnedItems().contains(id)) {
                            if (zItem.getEarthValue() > 0) {
                                lore.add(ChatColor.GREEN + "»Erde: " + zItem.getEarthValue());
                            }
                            if (zItem.getWaterValue() > 0) {
                                lore.add(ChatColor.AQUA + "»Wasser: " + zItem.getWaterValue());
                            }
                            if(zItem.getFireValue() > 0) {
                                lore.add(ChatColor.RED + "»Feuer: " + zItem.getFireValue());
                            }
                            if(zItem.getMetalValue() > 0) {
                                lore.add(ChatColor.GRAY + "»Metal: " + zItem.getMetalValue());
                            }
                        } else {
                            lore.add("»Item noch nicht gelernt!");
                        }
                        lore.add("");
                    }
                    if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR) || preTag.hasKey(zItemNBT.CREATIVE_GET)) {
                        if(!preTag.hasKey(zItemNBT.CREATIVE_GET)) {
                            booleans.put(zItemNBT.CREATIVE_GET, true);
                            strings.put(zItemNBT.CREATIVE_GET_USER, player.getDisplayName());
                            preTag.setString(zItemNBT.CREATIVE_GET_USER, player.getDisplayName());
                        }
                        lore.add(ChatColor.LIGHT_PURPLE + "In Kreativ bekommen");
                        lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + preTag.getString(zItemNBT.CREATIVE_GET_USER) + "«");
                    }
                } else {
                    if(zItem.requiredIntelligence() > 0) {
                        lore.add(ChatColor.GRAY + "Benötigt Intelligenz " + zItem.requiredIntelligence());
                    }
                }
                String itemVersion = zItem.itemVersion() + "";
                if(preTag.hasKey(zItemNBT.ITEM_VERSION))  {
                    itemVersion = preTag.getString(zItemNBT.ITEM_VERSION);
                } else {
                    strings.put(zItemNBT.ITEM_VERSION, itemVersion);
                }
                lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id + " [" + itemVersion + "]");

                if (textureDamage) {
                    Damageable damageable = (Damageable) itemMeta;
                    damageable.setDamage(zItem.getDamageValue());
                    itemMeta.setUnbreakable(true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                }
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(displayName);
                item.setItemMeta(itemMeta);
                //nmsCopy = createNMSCopy();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Fehler beim Item: " + zItem.getId());
            }
        }

    public net.minecraft.server.v1_16_R2.ItemStack createNMSCopy() {
        nmsCopy = CraftItemStack.asNMSCopy(item);
        return nmsCopy;
    }

    public void setTagCompound() {
        NBTTagCompound tag = getTagCompound();

        for (String s : zItem.getNBTObjects().keySet()) {
            INBTObject nbt = zItem.getNBTObjects().get(s);
            boolean changeTag = false;
            if(!nbt.changeAble()) {
                if(!tag.hasKey(s)) {
                    changeTag = true;
                }
            }
            NBTType type = nbt.getType();
            switch (type) {
                case UUID:
                case STRING:
                    tag.setString(s, (String) nbt.defaultValue());
                    break;
                case DOUBLE:
                    tag.setDouble(s, (Double) nbt.defaultValue());
                    break;
                case BOOLEAN:
                    tag.setBoolean(s, (Boolean) nbt.defaultValue());
                    break;
                case INTEGER:
                    tag.setInt(s, (Integer) nbt.defaultValue());
                    break;
            }
        }

        //tag.setString(zItemNBT.CONST_ITEM_ID, id);
        //tag.setDouble(zItemNBT.CONST_ITEM_VERSION, zItem.itemVersion());
        /*zItem.getNBTStrings().keySet().forEach((s -> tag.setString(s, zItem.getNBTStrings().get(s))));
        zItem.getNBTUUIDS().keySet().forEach((s -> tag.setString(s, zItem.getNBTUUIDS().get(s).toString())));
        zItem.getNBTInts().keySet().forEach((s -> tag.setInt(s, zItem.getNBTInts().get(s))));
        zItem.getNBTDoubles().keySet().forEach((s -> tag.setDouble(s, zItem.getNBTDoubles().get(s))));
        zItem.getNBTBooleans().keySet().forEach((s -> tag.setBoolean(s, zItem.getNBTBooleans().get(s))));

         */
    }

    @Deprecated
    public NBTTagCompound tagCompound() {
        return getTagCompound();
    }

    public NBTTagCompound getTagCompound() {
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        tag.setString(zItemNBT.ITEM_ID, id);
        tag.setDouble(zItemNBT.ITEM_VERSION, zItem.itemVersion());

        /*strings.keySet().forEach((s -> tag.setString(s, strings.get(s))));
        uuids.keySet().forEach((s -> tag.setString(s, uuids.get(s).toString())));
        ints.keySet().forEach((s -> tag.setInt(s, ints.get(s))));
        doubles.keySet().forEach((s -> tag.setDouble(s, doubles.get(s))));
        booleans.keySet().forEach((s -> tag.setBoolean(s, booleans.get(s)));
         */
        return tag;
    }
}
