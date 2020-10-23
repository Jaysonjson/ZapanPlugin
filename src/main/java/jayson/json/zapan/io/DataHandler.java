package jayson.json.zapan.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jayson.json.zapan.Utility;
import jayson.json.zapan.data.*;
import jayson.json.zapan.data.zdropobj.zMobDrop;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

public class DataHandler {

    public static String ROOT = "plugins/zapan/";
    public static String PLAYER_DIR = ROOT + "/players/";
    public static String AREA_DIR = ROOT + "/areas/";
    public static String GUILD_DIR = ROOT + "/guilds/";
    public static String SERVER_DIR = ROOT;
    public static String BACKPACK_DIR = PLAYER_DIR + "/backpacks/";
    public static String MOBDROPS_DIR = ROOT + "/drops/mobs/";
    private static final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    private static final Gson gson = new Gson();


    public static zPlayer loadPlayer(UUID uuid) {
        File file = new File(PLAYER_DIR + uuid.toString() + ".json");
        zPlayer player;
        if(!file.exists()) {
            player = new zPlayer();
            player.setUUID(uuid);
            savePlayer(player);
        } else {
            player = gson.fromJson(readData(file), zPlayer.class);
        }
        player.setUUID(uuid);
        return player;
    }

    public static void savePlayer(zPlayer player) {
        String json = gsonBuilder.toJson(player);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PLAYER_DIR + player.getUUID().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zBackPack loadBackPack(UUID uuid) {
        File file = new File(BACKPACK_DIR + uuid.toString() + ".json");
        zBackPack backPack;
        if(!file.exists()) {
            backPack = new zBackPack();
            backPack.setUUID(uuid);
            saveBackPack(backPack);
        } else {
            backPack = gson.fromJson(readData(file), zBackPack.class);
        }
        backPack.setUUID(uuid);
        return backPack;
    }

    public static void saveBackPack(zBackPack backPack) {
        String json = gsonBuilder.toJson(backPack);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(BACKPACK_DIR + backPack.getUUID().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void saveServer(zServer server) {
        String json = gsonBuilder.toJson(server);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(SERVER_DIR + "server.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zServer loadServer() {
        File file = new File(SERVER_DIR + "server.json");
        zServer server;
        if(!file.exists()) {
            server = new zServer();
            saveServer(server);
        } else {
            server = gson.fromJson(readData(file), zServer.class);
        }
        return server;
    }

    public static void saveDiscord(zDiscord discord) {
        String json = gsonBuilder.toJson(discord);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(SERVER_DIR + "discord.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zDiscord loadDiscord() {
        File file = new File(SERVER_DIR + "discord.json");
        zDiscord discord;
        if(!file.exists()) {
            discord = new zDiscord();
            saveDiscord(discord);
        } else {
            discord = gson.fromJson(readData(file), zDiscord.class);
        }
        return discord;
    }



    public static void saveArea(zArea area) {
        String json = gsonBuilder.toJson(area);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(AREA_DIR + area.name.toLowerCase() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zArea loadArea(String name) {
        File file = new File(AREA_DIR + name.toLowerCase() + ".json");
        zArea area;
        if(!file.exists()) {
            area = new zArea();
            area.name = name;
            saveArea(area);
        } else {
            area = gson.fromJson(readData(file), zArea.class);
        }
        return area;
    }

    public static void saveGuild(zGuild guild) {
        String json = gsonBuilder.toJson(guild);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(GUILD_DIR + guild.uuid.toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zGuild loadGuild(UUID uuid) {
        return loadGuild(uuid.toString());
    }

    public static zGuild loadGuild(String uuid) {
        File file = new File(GUILD_DIR + uuid.toString() + ".json");
        zGuild guild;
        if(!file.exists()) {
            guild = new zGuild();
            guild.name = "Unbekannt";
            saveGuild(guild);
        } else {
            guild = gson.fromJson(readData(file), zGuild.class);
        }
        return guild;
    }

    public static zDrops loadDrops() {
        File mobDrops = new File(MOBDROPS_DIR);
        zDrops zDrops = new zDrops();
        for (File file : mobDrops.listFiles()) {
            if(file.getName().toLowerCase().contains("json")) {
                zMobDrop mobDrop = gson.fromJson(readData(file), zMobDrop.class);
                for (String s : mobDrop.itemDropsID.keySet()) {
                    if(Utility.itemIDExists(s)) {
                        mobDrop.itemDrops.put(Utility.getAbstractItemByID(s), mobDrop.itemDropsID.get(s));
                        System.out.println("[Zapan {MobDrops}] " + mobDrop.itemDrops);
                    } else {
                        System.out.println("[Zapan {MobDrops}] Item mit der ID " + s + " existiert nicht, überspringen...");
                    }
                }
                zDrops.getMobDrops().add(mobDrop);
            }
        }
        return zDrops;
    }

    public static void createMobDrop() {
        zMobDrop mobDrop = new zMobDrop();
        mobDrop.type = EntityType.ZOMBIFIED_PIGLIN;
        mobDrop.itemDropsID.put("scrapItem", 2);
        String json = gsonBuilder.toJson(mobDrop);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(MOBDROPS_DIR + "test.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readData(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

}
