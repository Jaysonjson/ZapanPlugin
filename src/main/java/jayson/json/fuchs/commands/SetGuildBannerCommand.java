package jayson.json.fuchs.commands;

import jayson.json.fuchs.data.zGuild;
import jayson.json.fuchs.io.DataHandler;
import org.bukkit.block.banner.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class SetGuildBannerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
      if(player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta) {
            BannerMeta im = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();
            zGuild zGuild = new zGuild();
            assert im != null;
            if(im.getPatterns() != null) {
                zGuild.banner.color = im.getBaseColor();
                zGuild.banner.pattern = (ArrayList<Pattern>) im.getPatterns();
            }
            zGuild.uuid = UUID.randomUUID();
            DataHandler.saveGuild(zGuild);
            return false;
        }

      /*  BannerMeta im = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();
        zGuild zGuild = DataHandler.loadGuild(UUID.fromString("54a8dd44-c1ce-458a-9f34-beffb1b72d65"));
        im.setPatterns(zGuild.banner.pattern);
        player.getInventory().getItemInMainHand().setItemMeta(im);
        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());

       */
        return false;
    }
}
