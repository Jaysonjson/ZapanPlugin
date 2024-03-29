package jayson.json.fuchs.inventories.guild;

import jayson.json.fuchs.Utility;
import jayson.json.fuchs.Fuchs;
import jayson.json.fuchs.data.guild.data.zGuild;
import jayson.json.fuchs.data.player.data.zPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import javax.annotation.Nullable;

public class GuildInventory implements Listener {

    @Nullable
    public Inventory inventory;
    @Nullable
    public zGuild guild;
    @Nullable
    public zPlayer zPlayer;
    public GuildInventory(zGuild guild, zPlayer player) {
        this.guild = guild;
        this.zPlayer = player;
        Bukkit.getPluginManager().registerEvents(this, Fuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, 54, guild.getName());
        setContents();
        player.openInventory(inventory);
    }

    @SuppressWarnings("deprecation")
	public void setContents() {
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        ItemStack bannerItem = new ItemStack(Material.WHITE_BANNER);
        BannerMeta im = (BannerMeta) bannerItem.getItemMeta();
        im.setBaseColor(guild.getBanner().color);
        im.setPatterns(guild.getBanner().pattern);
        bannerItem.setItemMeta(im);
        inventory.setItem(31, bannerItem);
        inventory.setItem(10, Utility.createInventoryStack(Material.PLAYER_HEAD, 1, "Spieler"));
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Spieler")) {
                GuildPlayersInventory guildPlayersInventory = new GuildPlayersInventory(this);
                guildPlayersInventory.openInventory((Player) event.getWhoClicked());
            }
        }
    }
}
