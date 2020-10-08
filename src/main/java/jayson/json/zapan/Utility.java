package jayson.json.zapan;

import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.*;
import jayson.json.zapan.items.interfaces.IzItem;
import jayson.json.zapan.items.interfaces.IzItemRegistry;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.items.lists.zItem;
import jayson.json.zapan.items.lists.zItemAbility;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

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


    public static zArea getNearestArea(World.Environment environment, Location location) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
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

    public static boolean areaOverlap(Location locationP1, Location locationP2, Location locationBP1, Location locationBP2)
    {
        boolean XCol = isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP1.getX()) || isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP2.getX());
        boolean YCol = isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP1.getY()) || isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP2.getY());
        boolean ZCol = isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP1.getZ()) || isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP2.getZ());

        return XCol && YCol && ZCol;
    }

    public static boolean isAreaBetween(double point1, double point2, double target) {
        if(point1 < point2) {
            return target >= point1 && target <= point2;
        } else {
            return target >= point2 && target <= point1;
        }
    }

    public static boolean isInSpawnArea(Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        if(area.name.toLowerCase().equals("spawn")) {
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

    public static void spawnCustomItem(IzItem item, World world, Location location) {
        world.dropItemNaturally(location, item.getItem());
    }

    public static double countMoney(Player player) {
        return countMoney(player.getInventory());
    }

    public static double countMoney(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(content);
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if (tag.hasKey(zItemNBT.CONST_CURRENCY_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.CONST_CURRENCY_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    public static double countEmerald(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(content);
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if (tag.hasKey(zItemNBT.CONST_EMERALD_AMOUNT)) {
                        amount += (tag.getDouble(zItemNBT.CONST_EMERALD_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    public static double countEmerald(Player player) {
        return countEmerald(player.getInventory());
    }

    public static double convertHacksilverToEmerald(double hacksilverAmount) {
        return hacksilverAmount / Constant.HACKSILVER_TO_EMERALD_VALUE;
    }

    public static double convertEmeraldToHacksilver(double emeraldAmount) {
        return emeraldAmount * Constant.HACKSILVER_TO_EMERALD_VALUE;
    }

    public static void reloadAreas() {
        Zapan.INSTANCE.areas.clear();

        for (File file : new File(DataHandler.AREA_DIR).listFiles()) {
            zArea area = DataHandler.loadArea(file.getName().replaceAll(".json", ""));
            Zapan.INSTANCE.areas.add(area);
            DataHandler.saveArea(area);
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

    public static boolean areaExists(String name) {
        return new File(DataHandler.AREA_DIR + name.toLowerCase() + ".json").exists();
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
    public static AbstractzItem getAbstractItemByID(String id) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().getId().equalsIgnoreCase(id)) {
                return item.getAbstractItem();
            }
        }
        return null;
    }

    public static boolean itemIDExists(String id) {
        for (zItem value : zItem.values()) {
            if(value.getAbstractItem().getId().equalsIgnoreCase(id)) {
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

    public static net.minecraft.server.v1_16_R2.ItemStack createNMSCopy(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }

    @Deprecated
    public static boolean isValidAbilityItem(ItemStack itemStack) {
        for (zItemAbility value : zItemAbility.values()) {
            if(value.getAbstractItem().getItem(null).isSimilar(itemStack)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean isAbilityItem(ItemStack itemStack) {
        return isAbilityItem(null, itemStack);
    }

    public static boolean isAbilityItem(Player player, ItemStack itemStack) {
        for (zItemAbility value : zItemAbility.values()) {
            if(value.getAbstractItem().getItem(player).getType().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAbilityItemAll(Player player, ItemStack itemStack) {
        for (IzItemRegistry value : ItemRegistry.items) {
            if(value.getAbstractItem().getItem(player).getType().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean iszItem(ItemStack itemStack) {
        for (IzItemRegistry item : ItemRegistry.items) {
            if(item.getAbstractItem().getItem().getType().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static void updatePlayerInventory(Player player) {
        for (ItemStack content : player.getInventory().getContents()) {
            if(iszItem(content)) {
                AbstractzItem abstractzItem = getAbstractItemFromNMS(content);
                if(abstractzItem != null) {
                    abstractzItem.update(player);
                }
            }
        }
    }

    @Nullable
    public static AbstractzItem getAbstractItemFromNMS(ItemStack itemStack) {
        net.minecraft.server.v1_16_R2.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        if(tag.hasKey(zItemNBT.CONST_ITEM_ID)) {
           return getAbstractItemByID(tag.getString(zItemNBT.CONST_ITEM_ID));
        }
        return null;
    }

    public static String createInventoryContent(ItemStack[] itemStacks) {
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
        return "";
    }

    public static ItemStack[] generateInventoryContent(String inventoryContent) {
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


}
