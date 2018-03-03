package Model;

public class UserScore {
    public String name;
    public int score;

    public UserScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // jackson needs an empty constructor
    public UserScore() {
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}
