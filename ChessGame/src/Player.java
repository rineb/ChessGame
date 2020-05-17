/**
 * This class models the player
 */
public class Player {
    private String name;
    private int number;

    /**
     * Constructor of class Player
     * @param name The name of the player
     * @param number Indicates whether the player moves up or down
     */
    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
