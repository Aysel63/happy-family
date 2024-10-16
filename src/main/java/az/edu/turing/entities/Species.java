package az.edu.turing.entities;


public enum Species {

    DOG(false, 4, true),
    CAT(false, 4, true),
    FISH(false, 0, false),
    BIRD(true, 2, false),
    HAMSTER(false, 4, true),
    RABBIT(false, 4, true),
    UNKNOWN(false, 0, false);

    private final boolean canFly;
    private final int numberOfLegs;
    private final boolean hasFur;

    Species(boolean canFly, int numberOfLegs, boolean hasFur) {
        this.canFly = canFly;
        this.numberOfLegs = numberOfLegs;
        this.hasFur = hasFur;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    @Override
    public String toString() {
        return name() +
                "{canFly=" + canFly +
                ", numberOfLegs=" + numberOfLegs +
                ", hasFur=" + hasFur +
                '}';
    }
}
