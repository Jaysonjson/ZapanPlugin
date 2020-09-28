package jayson.json.zapan.other;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zareaobj.zLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboard {

    public static void updateScoreboard(Player player) {
        sendScoreboard(player, Utility.CountMoney(player), Utility.GetNearestArea(player.getLocation()).name, Utility.GetNearestAreaDistance(player.getLocation()));
    }

    public static void sendScoreboard(Player player, double money, String area, zLocation areaDistance) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective score = scoreboard.registerNewObjective("Zapan", "dummy", "Zapan");

        score.setDisplayName("Zapan");
        score.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineSpace0 = score.getScore("-0-----=0=-----0-");
        Score geldScore = score.getScore(ChatColor.DARK_GREEN + "Geld" + ChatColor.GRAY + ": " + ChatColor.GOLD + money);
        Score lineSpace1 = score.getScore("-0-----=0=-----0-");
        Score areaScore = score.getScore(ChatColor.GOLD + area + ChatColor.RESET + " (" + ChatColor.BOLD + (int)areaDistance.x + ChatColor.RESET + ", " +  ChatColor.BOLD + (int)areaDistance.z + ChatColor.RESET + ")");

        lineSpace0.setScore(3);
        geldScore.setScore(2);
        lineSpace1.setScore(1);
        areaScore.setScore(0);
        player.setScoreboard(scoreboard);
    }
}
