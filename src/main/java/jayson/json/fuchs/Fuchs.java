
package jayson.json.fuchs;
import jayson.json.fuchs.commands.*;
import jayson.json.fuchs.data.zArea;
import jayson.json.fuchs.events.*;
import jayson.json.fuchs.events.block.BlockBreak;
import jayson.json.fuchs.events.block.BlockPlace;
import jayson.json.fuchs.events.entity.EntityDamage;
import jayson.json.fuchs.events.entity.EntityDeath;
import jayson.json.fuchs.events.entity.MobSpawn;
import jayson.json.fuchs.events.entity.player.*;
import jayson.json.fuchs.events.inventory.ItemClick;
import jayson.json.fuchs.events.item.*;
import jayson.json.fuchs.io.DataHandler;
import jayson.json.fuchs.items.lists.ItemRegistry;
import jayson.json.fuchs.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class Fuchs extends JavaPlugin {

    public static Fuchs INSTANCE;
    public ArrayList<zArea> areas = new ArrayList<>();
    public Fuchs() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        new File(DataHandler.PLAYER_DIR).mkdirs();
        new File(DataHandler.AREA_DIR).mkdirs();
        new File(DataHandler.GUILD_DIR).mkdirs();
        new File(DataHandler.BACKPACK_DIR).mkdirs();
        new File(DataHandler.MOBDROPS_DIR).mkdirs();
        new File(DataHandler.BREWERY_DIR).mkdirs();
        //DataHandler.createMobDrop();
        ItemRegistry.reloadItems();
        References.reloadDrops();
        Utility.reloadAreas();
        NPC.loadNPCS();
        DataHandler.createBreweryCrafting();
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
        this.getCommand("classlist").setExecutor(new ClassListCommand());
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
