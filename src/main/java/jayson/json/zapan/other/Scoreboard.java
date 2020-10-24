package jayson.json.zapan.other;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zPlayer;
import jayson.json.zapan.data.zareaobj.zLocation;
import jayson.json.zapan.io.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboard {

    public static void updateScoreboard(Player player) {
        sendScoreboard(player, DataHandler.loadPlayer(player.getUniqueId()), Utility.countMoneyBackpack(player.getInventory()), Utility.countZoryhaShardBackpack(player.getInventory()), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()).displayName, Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void updateScoreboard(Player player, zPlayer zPlayer) {
        sendScoreboard(player, zPlayer, Utility.countMoneyBackpack(player.getInventory()), Utility.countZoryhaShardBackpack(player.getInventory()), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()).displayName, Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void sendScoreboard(Player player, zPlayer zPlayer, double money, double zoryhaShardValue, String area, zLocation areaDistance) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective score = scoreboard.registerNewObjective("Zapan", "dummy", "Zapan");

        score.setDisplayName(player.getDisplayName());
        score.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineSpace0 = score.getScore("-0------=0=------0-");
        String moneySTR = money + "";
        String zoryhaShardSTR = zoryhaShardValue + "";

        if(money > 10000) {
            moneySTR = Utility.formatInteger((int) money);
        }

        if(zoryhaShardValue > 10000) {
            zoryhaShardSTR = Utility.formatInteger((int) zoryhaShardValue);
        }
        Score hackSilver1Score = score.getScore("-//-");
        Score zoryhaShard1Score = score.getScore("-//-");
        if(Utility.convertZoryhaShardToHacksilver(zoryhaShardValue) > 0) {
            hackSilver1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (money + Utility.convertZoryhaShardToHacksilver(zoryhaShardValue)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        }
        Score hackSilverScore = score.getScore(ChatColor.GRAY + "Hacksilber" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + moneySTR + "Φ");
        if(Utility.convertHacksilverToZoryhaShard(money) > 0) {
            zoryhaShard1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (zoryhaShardValue + Utility.convertHacksilverToZoryhaShard(money)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        }
        Score zoryhaShardScore = score.getScore(ChatColor.AQUA + "Zoryha Bruckstücke" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + zoryhaShardSTR + "¢");
        Score lineSpace1 = score.getScore("-0------=1=------0-");
        Score areaScore = score.getScore(ChatColor.GOLD + area + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int)areaDistance.x) + ChatColor.RESET + ", " +  ChatColor.BOLD + Utility.formatInteger((int) areaDistance.z) + ChatColor.RESET + ")");
        Score lineSpace2 = score.getScore("-0------=2=------0-");
        Score levelScore = score.getScore("LvL: " + zPlayer.getLevel().level);
        Score lineSpace3 = score.getScore("-0------=3=------0-");
        Score classSore = score.getScore(ChatColor.RED + zPlayer.getPlayerClass().type.getName());
        lineSpace0.setScore(10);
        zoryhaShardScore.setScore(9);
        zoryhaShard1Score.setScore(8);
        hackSilverScore.setScore(7);
        hackSilver1Score.setScore(6);
        lineSpace1.setScore(5);
        areaScore.setScore(4);
        lineSpace2.setScore(3);
        levelScore.setScore(2);
        lineSpace3.setScore(1);
        classSore.setScore(0);
        player.setScoreboard(scoreboard);
    }
}
