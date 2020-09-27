package jayson.json.zapan.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zPlayer;

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

    public static zPlayer LoadPlayer(UUID uuid) {
        Gson gson = new Gson();
        File file = new File(PLAYER_DIR + uuid.toString() + ".json");
        zPlayer player;
        if(!file.exists()) {
            player = new zPlayer();
            player.SetUuid(uuid);
            SavePlayer(player);
        } else {
            player = gson.fromJson(ReadData(file), zPlayer.class);
        }
        player.SetUuid(uuid);
        return player;
    }

    public static void SavePlayer(zPlayer player) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
        String json = gson.toJson(player);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(PLAYER_DIR + player.GetUuid().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void SaveArea(zArea area) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(area);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(AREA_DIR + area.name.toLowerCase() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zArea LoadArea(String name) {
        Gson gson = new Gson();
        File file = new File(AREA_DIR + name.toLowerCase() + ".json");
        zArea area;
        if(!file.exists()) {
            area = new zArea();
            area.name = name;
            SaveArea(area);
        } else {
            area = gson.fromJson(ReadData(file), zArea.class);
        }
        return area;
    }

    public static boolean AreaExists(String name) {
        return new File(AREA_DIR + name.toLowerCase() + ".json").exists();
    }


    private static String ReadData(File file)
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
