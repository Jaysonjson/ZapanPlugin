package jayson.json.fuchs.objects.items.interfaces;

import jayson.json.fuchs.objects.items.zItemMarketModifierType;

public interface IzMarketItem {

    double getMarketValue();
    
    @Deprecated
    zItemMarketModifierType MARKET_MODIFIER_TYPE();
    
    default IMarketModifierType MARKET_MODIFIER() {
    	return MARKET_MODIFIER_TYPE();
    };
}
