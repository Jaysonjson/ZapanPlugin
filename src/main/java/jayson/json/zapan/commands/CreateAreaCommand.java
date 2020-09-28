package jayson.json.zapan.commands;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player && commandSender.isOp()) {
            if(args.length >= 2) {
                if(!Utility.AreaExists(args[0])) {
                    Player player = (Player) commandSender;
                    int size = Integer.parseInt(args[1]);
                    if(size < 50000) {
                        zArea area = new zArea();
                        area.owner = ((Player) commandSender).getUniqueId();
                        area.name = args[0];
                        area.size = size;
                        Location location = ((Player) commandSender).getLocation();
                        area.location = new zLocation(location.getX(), location.getY(), location.getZ());
                        DataHandler.SaveArea(area);
                        player.sendMessage("Gebiet " + args[0] + " erstellt!");
                        Zapan.INSTANCE.areas.add(area);
                        //player.sendMessage(Utility.AreaOverlap(area.CreateLocation(player.getWorld()).add(Utility.GetNearestArea(player.getLocation()).size, Utility.GetNearestArea(player.getLocation()).size, Utility.GetNearestArea(player.getLocation()).size), area.CreateLocation(player.getWorld()).subtract(Utility.GetNearestArea(player.getLocation()).size, Utility.GetNearestArea(player.getLocation()).size, Utility.GetNearestArea(player.getLocation()).size), player.getLocation().add(size, size, size), player.getLocation().subtract(size, size, size)) + "");
                        } else {
                        commandSender.sendMessage("Gebiet ist zu groß! Max: 50000");
                    }
                } else {
                    commandSender.sendMessage("Gebiet existiert bereits!");
                }
                return true;
            } else {
                if(args[0].equalsIgnoreCase("Reload")) {
                    Utility.ReloadAreas();
                    commandSender.sendMessage("Gebiete wurden Neugeladen!");
                    return true;
                }
            }
        }
        return false;
    }
}
