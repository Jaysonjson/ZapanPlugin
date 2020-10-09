package jayson.json.zapan.skillclass;

public enum zClass {

    NONE("Keine Klasse"),
    FARMER("Farmer"),
    CRAFTER("Schmied"),
    TRAVELER("Wanderer"),
    POTIONER("Brauer"),
    FIGHTER("Kämpfer");


    String name;
    zClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
