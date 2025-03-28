package elements.solid.moveable;

import CellularMatrix.CellularMatrix;
import elements.Element;
import elements.ElementType;
import elements.EmptyCell;
import elements.Solid;

public abstract class MoveableSolid extends Solid {
    public MoveableSolid(int x, int y) {
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
        }

//        else if (belowLeftCell instanceof EmptyCell) {
//            matrix.setElementAtIndex(getMatrixX(), getMatrixY(), ElementType.EMPTYCELL.createElementByMatrix(getMatrixX(), getMatrixY()));
//            matrix.getElementAtCell(getMatrixX(), getMatrixY()).setHasBeenUpdated(true);
//            matrix.setElementAtIndex(getMatrixX() - 1, getMatrixY() + 1, this.elementType.createElementByMatrix(getMatrixX() - 1, getMatrixY() + 1));
//            matrix.getElementAtCell(getMatrixX() - 1, getMatrixY() + 1).setHasBeenUpdated(true);
//        }
//
//        else if (belowRightCell instanceof EmptyCell) {
//            matrix.setElementAtIndex(getMatrixX(), getMatrixY(), ElementType.EMPTYCELL.createElementByMatrix(getMatrixX(), getMatrixY()));
//            matrix.getElementAtCell(getMatrixX(), getMatrixY()).setHasBeenUpdated(true);
//            matrix.setElementAtIndex(getMatrixX() + 1, getMatrixY() + 1, this.elementType.createElementByMatrix(getMatrixX() + 1, getMatrixY() + 1));
//            matrix.getElementAtCell(getMatrixX() + 1, getMatrixY() + 1).setHasBeenUpdated(true);
//        }
    }

}
