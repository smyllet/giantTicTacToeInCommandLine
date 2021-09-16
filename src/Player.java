public class Player {
    private final int number;
    private final char token;
    private int point;

    Player(int number, char token) {
        this.number = number;
        this.token = token;
        this.point = 0;
    }

    public int getNumber() {
        return number;
    }

    public char getToken() {
        return token;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void removePoint(int point) {
        this.point -= point;
    }
}
