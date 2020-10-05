package jayson.json.zapan.commands;

import jayson.json.zapan.data.zGuild;
import jayson.json.zapan.io.DataHandler;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.block.banner.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class SetGuildBannerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
       /* if(player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta) {
            BannerMeta im = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();
            zGuild zGuild = new zGuild();
            assert im != null;
            if(im.getPatterns() != null) {
                System.out.println("PATTERNS: " + im.getPatterns());
                zGuild.banner.pattern = (ArrayList<Pattern>) im.getPatterns();
            }
            zGuild.uuid = UUID.randomUUID();
            DataHandler.saveGuild(zGuild);
            return false;
        }
        */
        BannerMeta im = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();
        zGuild zGuild = DataHandler.loadGuild(UUID.fromString("54a8dd44-c1ce-458a-9f34-beffb1b72d65"));
        im.setPatterns(zGuild.banner.pattern);
        player.getInventory().getItemInMainHand().setItemMeta(im);
        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
        return false;
    }
}
