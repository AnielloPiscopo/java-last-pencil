package lastpencil.pojo;

public class Player {
    private String name;
    private boolean isABot;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, boolean isABot) {
        this.name = name;
        this.isABot = isABot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isABot() {
        return isABot;
    }

    public void setABot(boolean ABot) {
        isABot = ABot;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", isABot=" + isABot +
                '}';
    }
}
