package jayson.json.fuchs.objects.items.interfaces;

public interface IzTextureItem extends IzItem {
	
    int getDamageValue();
    int getCustomModelData();
    default boolean hasCustomModelData() {
        return getCustomModelData() != 0;
    }

}
