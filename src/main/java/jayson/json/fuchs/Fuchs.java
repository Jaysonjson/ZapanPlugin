
package jayson.json.fuchs;
import jayson.json.fuchs.commands.*;
import jayson.json.fuchs.commands.area.AreaCommand;
import jayson.json.fuchs.commands.area.CreateAreaCommand;
import jayson.json.fuchs.commands.guild.CreateGuildCommand;
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
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.data.io.FileHandler;
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

        registerCommands(
        		new SpigotCommand("areas", new AreaCommand()),
        		new SpigotCommand("sethealth", new SetHealthCommand()),
                new SpigotCommand("area", new CreateAreaCommand()),
                new SpigotCommand("guild", new CreateGuildCommand()),
                new SpigotCommand("items", new ItemCommand()),
                new SpigotCommand("npc", new CreateNPCCommand()),
                new SpigotCommand("gba", new SetGuildBannerCommand()),
                new SpigotCommand("gmc", new GamemodeCommand()),
                new SpigotCommand("gms", new GamemodeCommand()),
                new SpigotCommand("gma", new GamemodeCommand()),
                new SpigotCommand("gmsp", new GamemodeCommand()),
                new SpigotCommand("discordlink", new DiscordLinkCommand()),
                new SpigotCommand("itemids", new ItemIDCommand()),
                new SpigotCommand("classlist", new ClassListCommand()),
                new SpigotCommand("fuchs", new FuchsCommand())
        );
    }

    @Override
    public void onDisable() {
    }

    public static void registerEvents(Listener... listener) {
        for (Listener listener1 : listener) {
            Bukkit.getPluginManager().registerEvents(listener1, INSTANCE);
        }
    }
    
    public void registerCommands(SpigotCommand... commands) {
    	for(SpigotCommand command : commands) {
    		this.getCommand(command.getCommand()).setExecutor(command.getCommandExecutor());
    	}
    }

}
