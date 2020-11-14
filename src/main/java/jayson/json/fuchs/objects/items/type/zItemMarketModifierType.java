package jayson.json.fuchs.objects.items.type;

import jayson.json.fuchs.objects.items.interfaces.IMarketModifierType;

public enum zItemMarketModifierType implements IMarketModifierType {
	
    NONE(),
    ALL(),
    SCRAPYARD(),
    BANK();

    zItemMarketModifierType() {

    }

}
