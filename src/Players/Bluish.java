package Players;

public class Bluish extends Ghost implements GhostInterface{

    public Bluish(int x, int y, int size, String imagePath) {
        super(x, y, size, imagePath);
    }

    @Override
    public void goOut() {
        setPoint(getX() + speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
        setPoint(getX() - speed, getY());
    }
}
