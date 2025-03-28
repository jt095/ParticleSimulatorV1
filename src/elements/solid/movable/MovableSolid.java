package elements.solid.movable;

import CellularMatrix.CellularMatrix;
import elements.Element;
import elements.EmptyCell;
import elements.Solid;

public abstract class MovableSolid extends Solid {
    public MovableSolid(int x, int y) {
        super(x, y);
    }

    @Override
    public void step(CellularMatrix matrix) {
        Element belowCell = matrix.getElementAtCell(getMatrixX(), getMatrixY() + 1);
        Element belowLeftCell = matrix.getElementAtCell(getMatrixX() - 1, getMatrixY() + 1);
        Element belowRightCell = matrix.getElementAtCell(getMatrixX() + 1, getMatrixY() + 1);

        // TODO: add for liquid
        if (belowCell instanceof EmptyCell) {
            swapPositions(matrix, belowCell, getMatrixX(), getMatrixY() + 1);
        } else if (belowLeftCell instanceof  EmptyCell) {
            swapPositions(matrix, belowLeftCell, getMatrixX() - 1, getMatrixY() + 1);
        } else if (belowRightCell instanceof  EmptyCell) {
            swapPositions(matrix, belowRightCell, getMatrixX() + 1, getMatrixY() + 1);
        }
    }

}
