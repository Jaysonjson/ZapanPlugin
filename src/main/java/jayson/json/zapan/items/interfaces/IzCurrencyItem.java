package jayson.json.zapan.items.interfaces;

public interface IzCurrencyItem {
    double getHacksilverAmount();
    double getEmeraldAmount();
    void setHacksilverAmount(double amount);
    void setEmeraldAmount(double amount);
    void increaseHacksilverAmount(double amount);
    void increaseEmeraldAmount(double amount);
    void decreaseHacksilverAmount(double amount);
    void decreaseEmeraldAmount(double amount);
}
