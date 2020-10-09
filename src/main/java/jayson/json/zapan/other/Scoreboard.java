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
        sendScoreboard(player, DataHandler.loadPlayer(player.getUniqueId()),Utility.countMoneyBackpack(player.getInventory()), Utility.countEmeraldBackpack(player.getInventory()), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()).name, Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
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

        Score hackSilverScore = score.getScore(ChatColor.GRAY + "Hacksilber" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + moneySTR + "Φ");
        Score hackSilver1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (money + Utility.convertEmeraldToHacksilver(emeraldValue)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        Score emerald1Score = score.getScore(ChatColor.GRAY + "(" + ChatColor.RESET + (Utility.formatInteger((int) (emeraldValue + Utility.convertHacksilverToEmerald(money)))) + ChatColor.RESET + ChatColor.GRAY + ")");
        Score emeraldScore = score.getScore(ChatColor.DARK_GREEN + "Smaragde" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + emeraldSTR + "¢");
        Score lineSpace1 = score.getScore("-0------=1=------0-");
        Score areaScore = score.getScore(ChatColor.GOLD + area + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int)areaDistance.x) + ChatColor.RESET + ", " +  ChatColor.BOLD + Utility.formatInteger((int) areaDistance.z) + ChatColor.RESET + ")");
        Score lineSpace2 = score.getScore("-0------=2=------0-");
        Score levelScore = score.getScore("LvL: " + zPlayer.getLevel().level);
        Score lineSpace3 = score.getScore("-0------=3=------0-");
        Score classSore = score.getScore(ChatColor.RED + zPlayer.getPlayerClass().type.getName());
        lineSpace0.setScore(10);
        emeraldScore.setScore(9);
        emerald1Score.setScore(8);
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
