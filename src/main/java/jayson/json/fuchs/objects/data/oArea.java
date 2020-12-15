package jayson.json.fuchs.objects.data;

import java.util.UUID;

import jayson.json.fuchs.data.area.data.zArea;
import jayson.json.fuchs.data.io.DataHandler;
import jayson.json.fuchs.handler.DataIHandler;
import jayson.json.fuchs.handler.UUIDHandler;

public class oArea {

	private zArea area;
	public static oArea create() {
		oArea oArea = new oArea();
		oArea.area = new zArea();
		oArea.area.setUuid(UUIDHandler.generateUUID(DataIHandler.AREA_UUID));
		return oArea;
	}
	
	public static oArea create(UUID uuid) {
		oArea oArea = new oArea();
		oArea.area = new zArea();
		oArea.area.setUuid(uuid);
		return oArea;
	}

	public static oArea get(UUID uuid) {
		oArea oArea = new oArea();
		oArea.setArea(DataHandler.loadArea(uuid));
		return oArea;
	}

	public zArea getArea() {
		return area;
	}

	public void setArea(zArea area) {
		this.area = area;
	}

}
