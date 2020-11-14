package jayson.json.fuchs.objects.data;

import java.util.UUID;

import jayson.json.fuchs.data.area.data.zArea;

public class oArea {

	private zArea area;
	public static oArea create() {
		oArea oArea = new oArea();
		oArea.area = new zArea();
		return oArea;
	}
	
	public static oArea create(UUID uuid) {
		oArea oArea = new oArea();
		oArea.area = new zArea();
		oArea.area.setUuid(uuid);
		return oArea;
	}
}
