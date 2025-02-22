

// Base class to handle common functionality
abstract class BaseCharacter implements Character {
    protected int lifePoints;
    protected final int bitePower;

    public BaseCharacter(int lifePoints, int bitePower) {
        this.lifePoints = lifePoints;
        this.bitePower = bitePower;
    }

    @Override
    public void reduceLifePoints(int points) {
        lifePoints = Math.max(0, lifePoints - points);
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }

    @Override
    public void bite(Character ch) {
        if (bitePower > 0) {
            ch.reduceLifePoints(bitePower);
            System.out.println(this.getClass().getSimpleName() + " bites! Target now has " + ch.getLifePoints() + " life points.");
        } else {
            System.out.println(this.getClass().getSimpleName() + " cannot bite!");
        }
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }
}

interface Character {
    void bite(Character ch);
    void reduceLifePoints(int points);
    boolean isAlive();
    int getLifePoints();
}

// Subclasses inheriting common logic
class Human extends BaseCharacter {
    public Human(int points) {
        super(points, 0); // Humans cannot bite
    }
}

class Baracuda extends BaseCharacter {
    public Baracuda(int points) {
        super(points, 4);
    }
}

class Shark extends BaseCharacter {
    public Shark(int points) {
        super(points, 7);
    }
}

public class SeaWorld {
    public static void main(String[] args) {
        Character human = new Human(10);
        Character shark = new Shark(14);
        Character baracuda = new Baracuda(6);

        System.out.println("Initial Human Life Points: " + human.getLifePoints());

        shark.bite(human);
        baracuda.bite(human);

        System.out.println(human.isAlive() ? "Human is still alive with " + human.getLifePoints() + " life points." : "Human is dead.");
    }
}
