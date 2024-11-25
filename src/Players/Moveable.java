package Players;

import Objects.GeneralElement;

public interface Moveable {
    public boolean canMoveUp(GeneralElement[][] myMap);
    public boolean canMoveDown(GeneralElement[][] myMap);
    public boolean canMoveRight(GeneralElement[][] myMap);
    public boolean canMoveLeft(GeneralElement[][] myMap);
    public int getX();
    public int getY();
}
