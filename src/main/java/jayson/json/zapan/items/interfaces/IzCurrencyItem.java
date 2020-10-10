package jayson.json.zapan.items.interfaces;

public interface IzCurrencyItem {
    double getHacksilverAmount();
    double getZoryhaShardAmount();
    void setHacksilverAmount(double amount);
    void setZoryhaShardAmount(double amount);
    void increaseHacksilverAmount(double amount);
    void increaseZoryhaShardAmount(double amount);
    void decreaseHacksilverAmount(double amount);
    void decreaseZoryhaShardAmount(double amount);
}
