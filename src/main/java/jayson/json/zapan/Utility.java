package jayson.json.zapan;

import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.*;
import jayson.json.zapan.items.interfaces.IzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.BannedItems;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.items.lists.zItem;
import jayson.json.zapan.items.lists.zItemAbility;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class Utility {

    public static Utility INSTANCE;

    public Utility() {
        INSTANCE = this;
    }

    public static void refreshHearts(Player player) {
        zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        refreshHearts(player, zPlayer);
    }

    public static void refreshHearts(Player player, zPlayer zPlayer) {
        if(zPlayer.getHealth().health > 2048) {
            zPlayer.getHealth().health = 2048;
        }
        if(zPlayer.getHealth().health <= 0) {
            zPlayer.getHealth().health = 2;
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(zPlayer.getHealth().health);
        player.setHealth(zPlayer.getHealth().health);
    }

    public static void decreasePlayerHearts(Player player, zPlayer zPlayer, int amount) {
        zPlayer.getHealth().health = zPlayer.getHealth().health - amount;
        refreshHearts(player, zPlayer);
    }
    
    public static void increasePlayerHearts(Player player, zPlayer zPlayer, int amount) {
        decreasePlayerHearts(player, zPlayer,amount / -1);
    }

    public static boolean isInArea(Location location, Location locationP0, Location locationP1) {
        double maxX = (Math.max(locationP0.getX(), locationP1.getX()));
        double minX = (Math.min(locationP0.getX(), locationP1.getX()));

        double maxY = (Math.max(locationP0.getY(), locationP1.getY()));
        double minY = (Math.min(locationP0.getY(), locationP1.getY()));

        double maxZ = (Math.max(locationP0.getZ(), locationP1.getZ()));
        double minZ = (Math.min(locationP0.getZ(), locationP1.getZ()));

        if(location.getX() <= maxX && location.getX() >= minX) {
            if(location.getY() <= maxY && location.getY() >= minY) {
                return location.getZ() <= maxZ && location.getZ() >= minZ;
            }
        }
        return false;
    }

    public static boolean isInArea(zArea area, Player player) {
        Location p1 = area.createLocation(player.getWorld()).add(area.size, area.size, area.size);
        Location p2 = area.createLocation(player.getWorld()).subtract(area.size, area.size, area.size);
        return isInArea(player.getLocation(), p1, p2);
    }

    public static zArea getNearestArea(World.Environment environment, Location location) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        if(Zapan.INSTANCE.areas.size() < 1) {
            return new zArea();
        }
        for (zArea areas : Zapan.INSTANCE.areas) {
           // distances.put((location.getX() - areas.location.x), areas);
           // distancesD.add((location.getX() - areas.location.x));
            if(areas.world.getEnvironment().equals(environment)) {
                double distance = location.distance(areas.createLocation(location.getWorld()));
                distances.put(distance, areas);
                distancesD.add(distance);
            }
        }
        Collections.sort(distancesD);
        //Collections.reverse(distancesD);
        return distances.get(distancesD.get(0));
    }

    public static zArea getNearestAreaOutsidePlayer(Player player) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        if(Zapan.INSTANCE.areas.size() < 1) {
            return new zArea();
        }
        for (zArea areas : Zapan.INSTANCE.areas) {
            if(!isInArea(areas, player)) {
                if (areas.world.getEnvironment().equals(player.getWorld().getEnvironment())) {
                    double distance = player.getLocation().distance(areas.createLocation(player.getWorld()));
                    distances.put(distance, areas);
                    distancesD.add(distance);
                }
            }
        }
        Collections.sort(distancesD);
        return distances.get(distancesD.get(0));
    }

    public static zArea getNearestArea(Player player) {
        return getNearestArea(player.getWorld().getEnvironment(), player.getLocation());
    }

    @Deprecated
    public static zLocation getNearestAreaDistanceDEP(Location location) {
        ArrayList<Double> distancesX = new ArrayList<>();
        ArrayList<Double> distancesZ = new ArrayList<>();
        for (zArea areas : Zapan.INSTANCE.areas) {
            distancesX.add(location.getX() - areas.location.x);
            distancesZ.add(location.getZ() - areas.location.z);
        }
        Collections.sort(distancesX);
        Collections.reverse(distancesX);
        Collections.sort(distancesZ);
        Collections.reverse(distancesZ);
        return new zLocation(distancesX.get(0), 0, distancesZ.get(0));
    }

    public static zLocation getNearestAreaDistance(World.Environment environment, Location location) {
        zArea area = getNearestArea(environment, location);
        //return new zLocation(area.location.x - location.getX() - area.size, 0, area.location.z - location.getZ() - area.size);
        return new zLocation(area.location.x - location.getX(), 0, area.location.z - location.getZ());
    }

    public static zLocation getNearestAreaDistanceOutsidePlayer(Player player) {
        zArea area = getNearestAreaOutsidePlayer(player);
        return new zLocation(area.location.x - player.getLocation().getX(), 0, area.location.z - player.getLocation().getZ());
    }

    public static boolean areaOverlap(Location locationP1, Location locationP2, Location locationBP1, Location locationBP2)
    {
        boolean XCol = isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP1.getX()) || isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP2.getX());
        boolean YCol = isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP1.getY()) || isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP2.getY());
        boolean ZCol = isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP1.getZ()) || isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP2.getZ());

        return XCol && YCol && ZCol;
    }

    public static boolean areaOverlap(World world, zArea area1, zArea area2) {
        Location p1 = area1.createLocation(world);
        p1.add(area1.size, area1.size, area1.size);
        Location p2 = area1.createLocation(world);
        p2.subtract(area1.size, area1.size, area1.size);
        Location pb1 = area2.createLocation(world);
        pb1.add(area2.size, area2.size, area2.size);
        Location pb2 = area2.createLocation(world);
        pb2.subtract(area2.size, area2.size, area2.size);
        return areaOverlap(p1, p2, pb1, pb2);
    }

    public static boolean isAreaBetween(double point1, double point2, double target) {
        if(point1 < point2) {
            return target >= point1 && target <= point2;
        } else {
            return target >= point2 && target <= point1;
        }
    }
    //Einfach boolean isSpawn --> Sinnlos
    @Deprecated
    public static boolean isInSpawnArea(Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        if(area.displayName.toLowerCase().equals("spawn")) {
            Location locationP0 = area.createLocation(world).add(area.size, area.size, area.size);
            Location locationP1 = area.createLocation(world).subtract(area.size, area.size, area.size);
            return Utility.isInArea(location, locationP0, locationP1);
        }
        return false;
    }

    public static boolean canBreakBlock(Player player, Location location, World world) {
        zArea area = getNearestArea(player.getWorld().getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.createLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.breakBlocks || player.isOp();
        }
        return true;
    }

    public static boolean canPlaceBlock(Player player, Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.createLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.placeBlocks || player.isOp();
        }
        return true;
    }

    public static boolean canEntitySpawn(Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.createLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.spawnMobs;
        }
        return true;
    }

    public static boolean canDropItem(Player player, Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.size, area.size, area.size);
        Location locationP1 = area.createLocation(world).subtract(area.size, area.size, area.size);
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.dropItems || player.isOp();
        }
        return true;
    }

    public static void spawnCustomItem(Player player, AbstractItem item, World world, Location location) {
        world.dropItemNaturally(location, item.createItem(player, null, null));
    }

    public static double countMoney(Player player) {
        return countMoney(player.getInventory());
    }

    public static double countMoney(Inventory inventory) {
        return countMoney(inventory.getContents());
    }

    public static double countMoney(ItemStack[] contents) {
        double amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.HACKSILVER_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    public static double countMoneyBackpack(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(zItemNBT.HACKSILVER_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.HACKSILVER_AMOUNT) * content.getAmount());
                    }
                    if(tag.hasKey(zItemNBT.IS_BACKPACK)) {
                        ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(zItemNBT.ITEM_UUID))).inventoryContent);
                        amount += countMoney(contents);
                    }
                }
            }
        }
        return amount;
    }

    public static double countZoryhaShard(Inventory inventory) {
        return countZoryhaShard(inventory.getContents());
    }

    public static double countZoryhaShard(ItemStack[] contents) {
        double amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(zItemNBT.ZORYHASHARD_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.ZORYHASHARD_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    public static double countZoryhaShardBackpack(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(zItemNBT.ZORYHASHARD_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.ZORYHASHARD_AMOUNT) * content.getAmount());
                    }
                    if(tag.hasKey(zItemNBT.IS_BACKPACK)) {
                        ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(zItemNBT.ITEM_UUID))).inventoryContent);
                        amount += countZoryhaShard(contents);
                    }
                }
            }
        }
        return amount;
    }

    public static double countZoryhaShard(Player player) {
        return countZoryhaShard(player.getInventory());
    }

    public static double convertHacksilverToZoryhaShard(double hacksilverAmount) {
        return hacksilverAmount / Constant.HACKSILVER_TO_ZORYHASHARD_VALUE;
    }

    public static double convertZoryhaShardToHacksilver(double zoryhaShardAmount) {
        return zoryhaShardAmount * Constant.HACKSILVER_TO_ZORYHASHARD_VALUE;
    }

    public static void reloadAreas() {
        Zapan.INSTANCE.areas.clear();
        HashMap<String, zArea> areaHash = new HashMap<>();
        ArrayList<String> sortedHash = new ArrayList<>();
        for (File file : new File(DataHandler.AREA_DIR).listFiles()) {
            ///^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/
            try {
                zArea area = DataHandler.loadArea(UUID.fromString(file.getName().replaceAll(".json", "")));
                areaHash.put(area.priority + "_" + area.displayName, area);
                sortedHash.add(area.priority + "_" + area.displayName);
                DataHandler.saveArea(area);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(sortedHash);
        Collections.reverse(sortedHash);
        for (String hash : sortedHash) {
            Zapan.INSTANCE.areas.add(areaHash.get(hash));
        }
    }

    public static boolean guildExists(UUID uuid) {
        return new File(DataHandler.GUILD_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean guildExists(String name) {
        for (File file : new File(DataHandler.GUILD_DIR).listFiles()) {
            zGuild guild = DataHandler.loadGuild(file.getName().replaceAll(".json", ""));
            if(guild.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areaExistsUUID(UUID uuid) {
        return new File(DataHandler.AREA_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean areaExists(String name) {
        for (zArea area : Zapan.INSTANCE.areas) {
            if(area.displayName.toLowerCase().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    @Deprecated
    @Nullable
    public static IzItem getItemByID(String id) {

        IzItem item = null;

        for (zItem value : zItem.values()) {
            if(value.getAbstractItem().getId().equalsIgnoreCase(id)) {
                item = value.getAbstractItem();
                break;
            }
        }

        for (zItemAbility value : zItemAbility.values()) {
            if(value.getAbstractItem().getId().equalsIgnoreCase(id)) {
                item = value.getAbstractItem();
                break;
            }
        }

        return item;
    }

    @Nullable
    public static AbstractItem getAbstractItemByID(String id) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().getId().equalsIgnoreCase(id)) {
                return item.getAbstractItem();
            }
        }
        return null;
    }

    @Deprecated
    public static boolean itemIDExists(String id) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteArea(String name) {
        if(areaExists(name)) {
            new File(DataHandler.AREA_DIR + name.toLowerCase() + ".json").delete();
        }
    }

    public static String formatInteger(Integer integer) {
        String string = String.valueOf(integer);
        if(integer > 1000) string = String.format("%.2fk", integer / 1000.0);
        if(integer > 1000000) string = String.format("%.2fM", integer / 1000000.0);
        if(integer > 1000000000) string = String.format("%.2fG", integer / 1000000000.0);
        return string;
    }

    public static String formatTime(Integer integer) {
        String string = integer + "s";
        if(integer > 60) string = String.format("%.1fm", (integer / 60.0) % 60);
        return string;
    }

    public static void updateSkillBook(BookMeta bookMeta) {

    }

    public static NBTTagCompound getItemTag(net.minecraft.server.v1_16_R2.ItemStack itemStack) {
        return itemStack.hasTag() ? itemStack.getTag() : new NBTTagCompound();
    }

    public static NBTTagCompound getItemTag(ItemStack itemStack) {
        return getItemTag(createNMSCopy(itemStack));
    }

    public static net.minecraft.server.v1_16_R2.ItemStack createNMSCopy(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }

    @Deprecated
    public static boolean isValidAbilityItem(ItemStack itemStack) {
        for (zItemAbility value : zItemAbility.values()) {
            if(value.getAbstractItem().createItem(itemStack).isSimilar(itemStack)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean isAbilityItemINEF(ItemStack itemStack) {
        return isAbilityItemINEFF(itemStack);
    }

    @Deprecated
    public static boolean isAbilityItemINEFF(ItemStack itemStack) {
        for (zItemAbility value : zItemAbility.values()) {
            if(value.getAbstractItem().getMaterial().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }



    @Deprecated
    public static boolean isAbilityItemAllINEFF(Player player, ItemStack itemStack) {
        for (IzItemRegistry value : ItemRegistry.items) {
            if(value.getAbstractItem().createItem(player, itemStack, null).getType().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAbilityItemAll(Player player, ItemStack itemStack) {
        for (IzItemRegistry value : ItemRegistry.items) {
            if(value.getAbstractItem().getMaterial().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static void makeDrunk(Player player, zPlayer zPlayer) {
        if(zPlayer.getPlayerSpecial().alcohol > 0.5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (zPlayer.getPlayerSpecial().alcohol * 740), 0));
        }
        if(zPlayer.getPlayerSpecial().alcohol > 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (zPlayer.getPlayerSpecial().alcohol * 100),0));
        }
        if(zPlayer.getPlayerSpecial().alcohol > 2) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (zPlayer.getPlayerSpecial().alcohol * 60),0));
        }
        if(zPlayer.getPlayerSpecial().alcohol > 3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (zPlayer.getPlayerSpecial().alcohol * 700),2));
        }
    }

    @Deprecated
    public static boolean iszItem(ItemStack itemStack) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().createItem(null, itemStack, null).getType().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAbstractItem(ItemStack itemStack) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().getMaterial().equals(itemStack.getType())) {
                if(Utility.getItemTag(Utility.createNMSCopy(itemStack)).hasKey(zItemNBT.ITEM_ID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAbstractVanillaItem(ItemStack itemStack) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().isVanillaOverride()) {
                if(item.getAbstractItem().defaultVanillaOverride().equals(itemStack.getType())) {
                    if(!isBannedItem(itemStack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public static AbstractItem getAbstractVanillaOverride(ItemStack itemStack) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().isVanillaOverride()) {
                if(item.getAbstractItem().defaultVanillaOverride().equals(itemStack.getType())) {
                    return item.getAbstractItem();
                }
            }
        }
        return null;
    }

    public static boolean isBannedItem(ItemStack itemStack) {
        for (BannedItems value : BannedItems.values()) {
            if(value.getMaterial().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static void updatePlayerInventory(Player player) {
        int i = 0;
        for (ItemStack content : player.getInventory().getContents()) {
            i++;
            if(isAbstractItem(content)) {
                AbstractItem abstractItem = getAbstractItemFromNMS(content);
                if(abstractItem != null) {
                   player.getInventory().setItem(i, abstractItem.update(player, content, null));
                }
            }
        }
    }

    @Nullable
    public static AbstractItem getAbstractItemFromNMS(ItemStack itemStack) {
        net.minecraft.server.v1_16_R2.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        if(tag.hasKey(zItemNBT.ITEM_ID)) {
           return getAbstractItemByID(tag.getString(zItemNBT.ITEM_ID));
        }
        return null;
    }

    public static String createInventoryContent(ItemStack[] itemStacks) {
        if(itemStacks.length > 0) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
                dataOutput.writeInt(itemStacks.length);
                for (ItemStack itemStack : itemStacks) {
                    dataOutput.writeObject(itemStack);
                }
                dataOutput.close();
                return Base64Coder.encodeLines(outputStream.toByteArray());
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return "";
    }

    public static ItemStack[] generateInventoryContent(String inventoryContent) {
        if (inventoryContent.length() > 0) {
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inventoryContent));
                BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
                ItemStack[] items = new ItemStack[dataInput.readInt()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = (ItemStack) dataInput.readObject();
                }
                dataInput.close();
                return items;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return new ItemStack[0];
        }
        return new ItemStack[0];
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName) {
        ItemStack itemstack = new ItemStack(material);
        itemstack.setAmount(amount);
        ItemMeta itemstackMeta = itemstack.getItemMeta();
        itemstackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemstackMeta.setDisplayName(displayName);
        itemstack.setItemMeta(itemstackMeta);
        return itemstack;
    }

    public static ItemStack createInventoryStack(ItemStack itemstack, int amount, String displayName) {
        itemstack.setAmount(amount);
        ItemMeta itemstackMeta = itemstack.getItemMeta();
        itemstackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemstackMeta.setDisplayName(displayName);
        itemstack.setItemMeta(itemstackMeta);
        return itemstack;
    }

    public static ItemStack createInventoryWoolColor(boolean bool, String displayName, int amount) {
        return bool ? createInventoryStack(Material.GREEN_WOOL, amount, displayName) : createInventoryStack(Material.RED_WOOL, amount, displayName);
    }

    public static boolean isTopInventory(InventoryClickEvent event) {
        return event.getRawSlot() < event.getView().getTopInventory().getSize();
    }

    public static void createInventoryBorder(Inventory inventory, int rowSize, ItemStack itemStack) {
        for (int i = 0; i < rowSize; i++) {
            int j = i * 9;
            for (int slot = j; slot < j + 9; slot++) {
                if (i == 0 || i == 5 || j == slot || slot == j + 8) {
                    inventory.setItem(slot, itemStack);
                }
            }
        }
    }

    public static void createInventoryBorder(Inventory inventory, int rowSize, Material material) {
        createInventoryBorder(inventory, rowSize, new ItemStack(material));
    }

    public static void createInventoryBorder(Inventory inventory, Material material) {
        createInventoryBorder(inventory, (inventory.getSize() + 1) / 9, material);
    }

    public static void createInventoryBorder(Inventory inventory, ItemStack itemStack) {
        createInventoryBorder(inventory, (inventory.getSize() + 1) / 9, itemStack);
    }

    public static int addAmount(ItemStack original, ItemStack addition) {
        int amount = 0;
        NBTTagCompound tag1 = getItemTag(original);
        if(tag1.hasKey(zItemNBT.ITEM_AMOUNT)) {
            amount = tag1.getInt(zItemNBT.ITEM_AMOUNT);
            if(addition != null) {
                NBTTagCompound tag2 = getItemTag(addition);
                if (tag2.hasKey(zItemNBT.ITEM_AMOUNT)) {
                    amount += tag2.getInt(zItemNBT.ITEM_AMOUNT);
                }
            }
        }
        return amount;
    }
}
