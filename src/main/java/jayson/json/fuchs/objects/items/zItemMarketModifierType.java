package jayson.json.fuchs.objects.items;

import jayson.json.fuchs.objects.items.interfaces.IMarketModifierType;

public enum zItemMarketModifierType implements IMarketModifierType {
	
    NONE(),
    ALL(),
    SCRAPYARD(),
    BANK();

    zItemMarketModifierType() {

    }

}
