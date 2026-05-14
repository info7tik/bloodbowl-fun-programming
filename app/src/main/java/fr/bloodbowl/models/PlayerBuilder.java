package fr.bloodbowl.models;

public class PlayerBuilder {
    private String identifier;
    private int movement;

    public PlayerBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public PlayerBuilder withMovement(int movement) {
        this.movement = movement;
        return this;
    }

    public Player build() {
        return new Player(identifier, movement);
    }
}