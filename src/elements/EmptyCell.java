package elements;

import CellularMatrix.CellularMatrix;

import java.awt.*;

public class EmptyCell extends Element{
    private static Element element;

    private EmptyCell(int x, int y){
        super(x, y);
    }

    public static Element getInstance() {
        if (element == null) {
            element = new EmptyCell(-1, -1);
        }
        return element;
    }

    @Override
    public void step(CellularMatrix matrix) {

    }
}
