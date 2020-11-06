package jayson.json.zapan.commands;

import jayson.json.zapan.Utility;
import jayson.json.zapan.Zapan;
import jayson.json.zapan.data.zArea;
import jayson.json.zapan.data.zareaobj.zWorld;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class CreateAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player && commandSender.isOp()) {
            if(args.length >= 2) {
               /* if(args[0].equalsIgnoreCase("edit")) {
                    if(Utility.areaExists(args[1])) {
                        AreaInventory areaInventory = new AreaInventory();
                        areaInventory.openInventory((Player) commandSender, Utility.getNearestArea(World.Environment.NORMAL, ((Player) commandSender).getLocation()).displayName);
                    } else {
                        commandSender.sendMessage("Gebiet existiert nicht!");
                    }
                }*/
                if(!Utility.areaExists(args[0])) {
                    Player player = (Player) commandSender;
                    int size = Integer.parseInt(args[1]);
                    if(size < 50000) {
                        zArea nearestArea = Utility.getNearestArea(player);
                        zArea area = new zArea();
                        area.size = size;
                        if(!area.canOverlap(player) && Utility.areaOverlap(player.getWorld(), area, nearestArea)) {
                            player.sendMessage("Konnte Gebiet nicht erstellen! Es würde " + nearestArea.displayName + " überlappen und dies Erlaubt " + nearestArea.displayName + " nicht!");
                        }
                        area.owner = ((Player) commandSender).getUniqueId();
                        area.uuid = UUID.randomUUID();
                        area.displayName = args[0];
                        Location location = player.getLocation();
                        area.location = new zLocation(location.getX(), location.getY(), location.getZ());
                        DataHandler.saveArea(area);
                        player.sendMessage("Gebiet " + args[0] + " erstellt!");
                        Zapan.INSTANCE.areas.add(area);
                        area.world = zWorld.OVERWORLD;
                        if(player.getWorld().getEnvironment() == World.Environment.NETHER) {
                            area.world = zWorld.NETHER;
                        }
                        if(player.getWorld().getEnvironment() == World.Environment.THE_END) {
                            area.world = zWorld.END;
                        }

                        //player.sendMessage(Utility.areaOverlap(player.getWorld(), area, nearestArea) + "_2");
                    } else {
                        commandSender.sendMessage("Gebiet ist zu groß! Max: 50000");
                    }
                } else {
                    commandSender.sendMessage("Gebiet existiert bereits!");
                }
                return true;
            } else {
                if(args[0].equalsIgnoreCase("Reload")) {
                    Utility.reloadAreas();
                    commandSender.sendMessage("Gebiete wurden Neugeladen!");
                    return true;
                }

                if(args[0].equalsIgnoreCase("delete")) {
                    Utility.deleteArea(args[1]);
                    Utility.reloadAreas();
                    commandSender.sendMessage("Gebiet wurde entfernt!");
                    return true;
                }
            }
        }
        return false;
    }

    private ItemStack GetWoolColor(boolean bool, String name) {
        ItemStack wool = new ItemStack(Material.RED_WOOL);
        ItemMeta woolMeta = wool.getItemMeta();
        woolMeta.setDisplayName(ChatColor.DARK_RED + name);
        if (bool) {
            wool = new ItemStack(Material.GREEN_WOOL);
            woolMeta.setDisplayName(ChatColor.DARK_GREEN + name);
        }
        wool.setItemMeta(woolMeta);
        return wool;
    }
}
