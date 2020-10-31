
package jayson.json.zapan;
import jayson.json.zapan.commands.*;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zDrops;
import jayson.json.zapan.events.*;
import jayson.json.zapan.events.inventory.ItemClick;
import jayson.json.zapan.inventories.AreaListInventory;
import jayson.json.zapan.io.DataHandler;
import jayson.json.zapan.items.lists.ItemRegistry;
import jayson.json.zapan.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
        new File(DataHandler.BACKPACK_DIR).mkdirs();
        new File(DataHandler.MOBDROPS_DIR).mkdirs();
        //DataHandler.createMobDrop();
        ItemRegistry.reloadItems();
        References.reloadDrops();
        Utility.reloadAreas();
        NPC.loadNPCS();
        registerEvents(
                new PlayerJoin(),
                new PlayerDeath(),
                new PlayerRespawn(),
                new PlayerMove(),
                new MobSpawn(),
                new CraftItem(),
                new BlockBreak(),
                new BlockPlace(),
                new DropItem(),
                new EntityDeath(),
                new ItemClick(),
                new ChatEvent(),
                new ItemUse(),
                new EntityDamage(),
                new BanPlayer(),
                new ItemPickup(),
                new ItemDespawn(),
                new Smelting()
        );
        this.getCommand("areas").setExecutor(new AreaCommand());
        this.getCommand("sethealth").setExecutor(new SetHealthCommand());
        this.getCommand("area").setExecutor(new CreateAreaCommand());
        this.getCommand("guild").setExecutor(new CreateGuildCommand());
        this.getCommand("giveItem").setExecutor(new GiveCustomItem());
        this.getCommand("items").setExecutor(new ItemCommand());
        this.getCommand("npc").setExecutor(new CreateNPCCommand());
        this.getCommand("gba").setExecutor(new SetGuildBannerCommand());
        this.getCommand("gmc").setExecutor(new GamemodeCommand());
        this.getCommand("gms").setExecutor(new GamemodeCommand());
        this.getCommand("gma").setExecutor(new GamemodeCommand());
        this.getCommand("gmsp").setExecutor(new GamemodeCommand());
        this.getCommand("discordlink").setExecutor(new DiscordLinkCommand());
        this.getCommand("itemids").setExecutor(new ItemIDCommand());
    }

    @Override
    public void onDisable() {
    }

    public static void registerEvents(Listener... listener) {
        for (Listener listener1 : listener) {
            Bukkit.getPluginManager().registerEvents(listener1, INSTANCE);
        }
    }

}
