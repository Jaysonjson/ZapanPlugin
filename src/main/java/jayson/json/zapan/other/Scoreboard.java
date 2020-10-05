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
        sendScoreboard(player, DataHandler.loadPlayer(player.getUniqueId()),Utility.countMoney(player), Utility.countEmerald(player), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()).name, Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void sendScoreboard(Player player, zPlayer zPlayer, double money, double emeraldValue, String area, zLocation areaDistance) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective score = scoreboard.registerNewObjective("Zapan", "dummy", "Zapan");

        score.setDisplayName(player.getDisplayName());
        score.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineSpace0 = score.getScore("-0------=0=------0-");
        String moneySTR = money + "";
        String emeraldSTR = emeraldValue + "";

        if(money > 10000) {
            moneySTR = Utility.formatInteger((int) money);
        }

        if(emeraldValue > 10000) {
            emeraldSTR = Utility.formatInteger((int) emeraldValue);
        }

        Score geldScore = score.getScore(ChatColor.GRAY + "Hacksilber" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + moneySTR + "Φ");
        Score emeraldScore = score.getScore(ChatColor.DARK_GREEN + "Smaragde" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + emeraldSTR + "¢");
        Score lineSpace1 = score.getScore("-0------=1=------0-");
        Score areaScore = score.getScore(ChatColor.GOLD + area + ChatColor.RESET + " (" + ChatColor.BOLD + (int)areaDistance.x + ChatColor.RESET + ", " +  ChatColor.BOLD + (int)areaDistance.z + ChatColor.RESET + ")");
        Score lineSpace2 = score.getScore("-0------=2=------0-");
        Score levelScore = score.getScore("LvL: " + zPlayer.getLevel().level);

        lineSpace0.setScore(6);
        emeraldScore.setScore(5);
        geldScore.setScore(4);
        lineSpace1.setScore(3);
        areaScore.setScore(2);
        lineSpace2.setScore(1);
        levelScore.setScore(0);
        player.setScoreboard(scoreboard);
    }
}
