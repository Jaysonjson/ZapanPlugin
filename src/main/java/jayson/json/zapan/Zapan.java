package jayson.json.zapan;

import jayson.json.zapan.commands.*;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.events.*;
import jayson.json.zapan.events.inventory.AreaEditClick;
import jayson.json.zapan.events.inventory.ItemClick;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.npc.NPC;
import net.minecraft.server.v1_16_R2.EntityPlayer;
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
        new File(DataHandler.GUILD_DIR).mkdirs();
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new MobSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new CraftItem(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new DropItem(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
        Bukkit.getPluginManager().registerEvents(new AreaEditClick(), this);
        Bukkit.getPluginManager().registerEvents(new ItemClick(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        this.getCommand("sethealth").setExecutor(new SetHealthCommand());
        this.getCommand("area").setExecutor(new CreateAreaCommand());
        this.getCommand("guild").setExecutor(new CreateGuildCommand());
        this.getCommand("giveItem").setExecutor(new GiveCustomItem());
        this.getCommand("items").setExecutor(new ItemCommand());
        this.getCommand("npc").setExecutor(new CreateNPCCommand());
        this.getCommand("gba").setExecutor(new SetGuildBannerCommand());

        Utility.reloadAreas();
        NPC.loadNPCS();
    }

    @Override
    public void onDisable() {
    }


}
