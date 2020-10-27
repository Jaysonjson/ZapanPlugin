package jayson.json.zapan.other;

import jayson.json.zapan.Utility;
import jayson.json.zapan.data.zArea;
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
        sendScoreboard(player, DataHandler.loadPlayer(player.getUniqueId()), Utility.countMoneyBackpack(player.getInventory()), Utility.countZoryhaShardBackpack(player.getInventory()), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()), Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void updateScoreboard(Player player, zPlayer zPlayer) {
        sendScoreboard(player, zPlayer, Utility.countMoneyBackpack(player.getInventory()), Utility.countZoryhaShardBackpack(player.getInventory()), Utility.getNearestArea(player.getWorld().getEnvironment(), player.getLocation()), Utility.getNearestAreaDistance(player.getWorld().getEnvironment(), player.getLocation()));
    }

    public static void sendScoreboard(Player player, zPlayer zPlayer, double money, double zoryhaShardValue, zArea area, zLocation areaDistance) {
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
        String areaColor = ChatColor.GOLD + "";
        Score nextAreaScore = null;
        boolean inArea = Utility.isInArea(area, player);
        if(inArea) {
            zArea nextArea = Utility.getNearestAreaOutsidePlayer(player);
            zLocation nextAreaLoc = Utility.getNearestAreaDistanceOutsidePlayer(player);
            nextAreaScore = score.getScore(ChatColor.GOLD + nextArea.displayName + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int)nextAreaLoc.x) + ChatColor.RESET + ", " +  ChatColor.BOLD + Utility.formatInteger((int) nextAreaLoc.z) + ChatColor.RESET + ")");
            areaColor = ChatColor.DARK_PURPLE + "";
        }
        Score areaScore = score.getScore(areaColor + area.displayName + ChatColor.RESET + " (" + ChatColor.BOLD + Utility.formatInteger((int)areaDistance.x) + ChatColor.RESET + ", " +  ChatColor.BOLD + Utility.formatInteger((int) areaDistance.z) + ChatColor.RESET + ")");
        Score lineSpace2 = score.getScore("-0------=2=------0-");
        Score levelScore = score.getScore("LvL: " + zPlayer.getLevel().level);
        Score lineSpace3 = score.getScore("-0------=3=------0-");
        Score classSore = score.getScore(ChatColor.RED + zPlayer.getPlayerClass().current.getName());
        setScores(
                classSore,
                lineSpace3,
                levelScore,
                lineSpace2,
                nextAreaScore,
                areaScore,
                lineSpace1,
                hackSilver1Score,
                hackSilverScore,
                zoryhaShard1Score,
                zoryhaShardScore,
                lineSpace0
        );
        player.setScoreboard(scoreboard);
    }

    private static void setScores(Score... scores) {
        int i = 0;
        for (Score score : scores) {
            if(score != null) {
                score.setScore(i);
                i++;
            }
        }
    }
}
