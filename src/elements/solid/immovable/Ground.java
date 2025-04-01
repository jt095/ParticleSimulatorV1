package elements.solid.immovable;

import CellularMatrix.CellularMatrix;

public class Ground extends ImmovableSolid {
    public Ground(int x, int y, boolean evenFrame) {
        super(x, y, evenFrame);
    }

    @Override
    public void step(CellularMatrix matrix) {

    }
}
