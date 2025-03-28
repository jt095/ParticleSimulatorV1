package elements.solid.immoveable;

import CellularMatrix.CellularMatrix;

import java.awt.*;

public class Ground extends ImmoveableSolid {
    public Ground(int x, int y) {
        super(x, y);
        setColor(Color.GRAY);
    }

    @Override
    public void step(CellularMatrix matrix) {

    }
}
