package jayson.json.fuchs.objects.gas;

import jayson.json.fuchs.objects.gas.interfaces.IzGasRegistry;
import jayson.json.fuchs.objects.gas.obj.*;
public enum zGas implements IzGasRegistry {
	
	NONE(new NoneGas()),
	OXYGEN(new OxygenGas());
	
	AbstractGas abstractGas;
	zGas(AbstractGas abstractGas) {
		this.abstractGas = abstractGas;
	}
	
	@Override
	public AbstractGas getGas() {
		return abstractGas;
	}

}
