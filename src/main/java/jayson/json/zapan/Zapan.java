package jayson.json.zapan;

import jayson.json.zapan.commands.CreateAreaCommand;
import jayson.json.zapan.commands.SetHealthCommand;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.events.*;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class Zapan extends JavaPlugin {

    public static Zapan INSTANCE;
    public ArrayList<zArea> areas = new ArrayList<>();
    public Zapan() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        new File(DataHandler.PLAYER_DIR).mkdirs();
        new File(DataHandler.AREA_DIR).mkdirs();

        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new MobSpawn(), this);
        this.getCommand("sethealth").setExecutor(new SetHealthCommand());
        this.getCommand("area").setExecutor(new CreateAreaCommand());

        areas.clear();

        for (File file : new File(DataHandler.AREA_DIR).listFiles()) {
            areas.add(DataHandler.LoadArea(file.getName().replaceAll(".json", "")));
        }
    }

    @Override
    public void onDisable() {
    }


}
