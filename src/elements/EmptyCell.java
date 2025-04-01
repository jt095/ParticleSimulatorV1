package elements;

import CellularMatrix.CellularMatrix;

import java.awt.*;

public class EmptyCell extends Element{

    public EmptyCell(int x, int y, boolean evenFrame) {
        super(x, y, evenFrame);
    }

    @Override
    public void step(CellularMatrix matrix) {

    }
}
