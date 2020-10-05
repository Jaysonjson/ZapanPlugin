package jayson.json.zapan.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zServer;

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

    public static String PLAYER_DIR = "plugins/zapan/players/";
    public static String AREA_DIR = "plugins/zapan/areas/";
    public static String GUILD_DIR = "plugins/zapan/guilds/";
    public static String SERVER_DIR = "plugins/zapan/";
    private static final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    private static final Gson gson = new Gson();


    public static zPlayer loadPlayer(UUID uuid) {
        File file = new File(PLAYER_DIR + uuid.toString() + ".json");
        zPlayer player;
        if(!file.exists()) {
            player = new zPlayer();
            player.setUuid(uuid);
            savePlayer(player);
        } else {
            player = gson.fromJson(readData(file), zPlayer.class);
        }
        player.setUuid(uuid);
        return player;
    }

    public static void savePlayer(zPlayer player) {
        String json = gsonBuilder.toJson(player);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PLAYER_DIR + player.getUuid().toString() + ".json"));
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

    private static String readData(File file)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

}
