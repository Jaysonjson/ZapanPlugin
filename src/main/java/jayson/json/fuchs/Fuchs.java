
package jayson.json.fuchs;
import jayson.json.fuchs.commands.*;
import jayson.json.fuchs.commands.area.AreaCommand;
import jayson.json.fuchs.commands.area.CreateAreaCommand;
import jayson.json.fuchs.commands.guild.CreateGuildCommand;
import jayson.json.fuchs.commands.item.GiveCustomItem;
import jayson.json.fuchs.commands.item.ItemCommand;
import jayson.json.fuchs.commands.item.ItemIDCommand;
import jayson.json.fuchs.data.area.data.zArea;
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
import jayson.json.fuchs.io.FileHandler;
import jayson.json.fuchs.objects.zRegistry;
import jayson.json.fuchs.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Fuchs extends JavaPlugin {

    public static Fuchs INSTANCE;
    public ArrayList<zArea> areas = new ArrayList<>();
    public static double version = 0.1;
    public Fuchs() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        try {
			FileHandler.createDirectories();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //DataHandler.createMobDrop();
        zRegistry.addFuchsItems();
        zRegistry.addFuchsLiquids();
        zRegistry.addFuchsGasses();
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
                new Smelting(),
                new PlayerSleep()
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
        this.getCommand("fuchs").setExecutor(new FuchsCommand());

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
