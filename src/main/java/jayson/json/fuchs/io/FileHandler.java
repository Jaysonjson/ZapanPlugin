package jayson.json.fuchs.io;


import java.io.File;
import java.lang.reflect.Field;

public class FileHandler {
	
	@Directory
    public static String ROOT = "plugins/fuchs/";
    @Directory
	public static String PLAYER_DIR = ROOT + "/players/";
    @Directory
    public static String AREA_DIR = ROOT + "/areas/";
    @Directory
    public static String GUILD_DIR = ROOT + "/guilds/";
    @Directory
    public static String SERVER_DIR = ROOT;
    @Directory
    public static String BACKPACK_DIR = PLAYER_DIR + "/backpacks/";
    @Directory
    public static String MOBDROPS_DIR = ROOT + "/drops/mobs/";
    @Directory
    public static String BREWERY_DIR = ROOT + "/crafting/brewery/";
    
    public void createDirectories() throws IllegalAccessException {
        for (Field field : FileHandler.class.getFields()) {
            if(field.isAnnotationPresent(Directory.class)) {
                new File((String) field.get(this)).mkdirs();
            }
        }
    }
    
}
