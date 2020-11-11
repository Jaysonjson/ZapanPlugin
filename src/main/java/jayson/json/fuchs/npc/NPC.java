package jayson.json.fuchs.npc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jayson.json.fuchs.data.server.data.zServer;
import jayson.json.fuchs.data.server.obj.zNPC;
import jayson.json.fuchs.io.DataHandler;
import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class NPC {

    public static ArrayList<EntityPlayer> NPC = new ArrayList<>();


    public static void createNPC(Player player, String name, String skinName) {
        zServer zServer = DataHandler.loadServer();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        zNPC zNPC = new zNPC();
        if(skinName != null) {
            String[] textures = getTextureData(skinName);
            gameProfile.getProperties().put("textures", new Property("textures", textures[0], textures[1]));
            zNPC.texture = textures[0];
            zNPC.signature = textures[1];
        }
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        zNPC.skinName = skinName;
        zNPC.name = name;
        zNPC.pitch = npc.pitch;
        zNPC.world = npc.getWorld().getWorld().getName();
        zNPC.yaw = npc.yaw;
        zNPC.x = (int) npc.locX();
        zNPC.y = (int) npc.locY();
        zNPC.z = (int) npc.locZ();
        zNPC.uuid = npc.getUniqueID();
        NPC.add(npc);
        zServer.zNPC.add(zNPC);
        DataHandler.saveServer(zServer);
        sendPackets();
    }

    public static void sendPackets() {
        for (EntityPlayer npc : NPC) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
            }
        }
    }

    public static void sendSinglePacket(Player player) {
        for (EntityPlayer npc : NPC) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        }
    }

    public static String[] getTextureData(String playerName) {
        try {
            URL apiURL = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            InputStreamReader apiReader = new InputStreamReader(apiURL.openStream());
            String uuid = new JsonParser().parse(apiReader).getAsJsonObject().get("id").getAsString();
            URL sessionURL = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader sessionReader = new InputStreamReader(sessionURL.openStream());
            JsonObject properties = new JsonParser().parse(sessionReader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = properties.get("value").getAsString();
            String signature = properties.get("signature").getAsString();
            return new String[] {texture,signature};
        } catch (Exception exception) {
            exception.printStackTrace();
            return new String[] {"noData", "noData"};
        }
    }

    public static void loadNPCS() {
        zServer zServer = DataHandler.loadServer();
        for (zNPC zNPC : zServer.zNPC) {
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) Bukkit.getWorld(zNPC.world)).getHandle();
            GameProfile gameProfile = new GameProfile(zNPC.uuid, zNPC.name);;
            EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
            npc.setLocation(zNPC.x, zNPC.y, zNPC.z, zNPC.yaw, zNPC.pitch);
            if(zNPC.skinName != null) {
                gameProfile.getProperties().put("textures", new Property("textures", zNPC.texture, zNPC.signature));
            }
            NPC.add(npc);
            DataHandler.saveServer(zServer);
        }
    }

}
