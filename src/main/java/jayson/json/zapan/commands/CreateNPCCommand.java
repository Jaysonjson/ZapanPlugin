package jayson.json.zapan.commands;

import jayson.json.zapan.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        NPC.createNPC((Player) commandSender, strings[0], strings[1]);
        //NPC.createNPC((Player) commandSender, "NPC", "");

        return false;
    }
}
