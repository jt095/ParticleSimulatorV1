package elements.solid.movable;

import CellularMatrix.CellularMatrix;
import elements.Element;
import elements.EmptyCell;
import elements.liquid.Liquid;
import elements.solid.Solid;

import static java.lang.Math.round;

public abstract class MovableSolid extends Solid {
    public MovableSolid(int x, int y, boolean evenFrame) {
        super(x, y, evenFrame);
    }

    @Override
    public void step(CellularMatrix matrix) {
        // loop through each velocity step
        boolean emptyBelow = false;
        int validPosX = getMatrixX();
        int validPosY = getMatrixY();
        for (int x = 0; x < round(getVelocityX()) + 1; x++) {
            for (int y = 0; y < round(getVelocityY()) + 1; y++) {

                int nextPosX = getMatrixX() + x;
                int nextPosY = getMatrixY() + y;
                if (nextPosX <= 0 || nextPosX >= matrix.getWidth() || nextPosY >= matrix.getHeight() - 1) {
                    break;
                }
                Element belowCell = matrix.getElementAtCell(nextPosX, nextPosY + 1);
                Element belowLeftCell = matrix.getElementAtCell(nextPosX - 1, nextPosY + 1);
                Element belowRightCell = matrix.getElementAtCell(nextPosX + 1, nextPosY + 1);


                if (belowCell instanceof EmptyCell || belowCell instanceof Liquid) {
                    validPosX = nextPosX;
                    validPosY = nextPosY + 1;
                    emptyBelow = true;
                }
                else if (evenFrame) {
                    if (belowLeftCell instanceof EmptyCell || belowLeftCell instanceof Liquid) {
                        validPosX = nextPosX - 1;
                        validPosY = nextPosY + 1;
                    } else if (belowRightCell instanceof EmptyCell || belowRightCell instanceof Liquid) {
                        validPosX = nextPosX + 1;
                        validPosY = nextPosY + 1;
                    }
                }
                else {
                    if (belowRightCell instanceof EmptyCell || belowRightCell instanceof Liquid) {
                        validPosX = nextPosX + 1;
                        validPosY = nextPosY + 1;
                    }
                    else if (belowLeftCell instanceof EmptyCell || belowLeftCell instanceof Liquid) {
                        validPosX = nextPosX - 1;
                        validPosY = nextPosY + 1;
                    }
                }

            }
        }

        swapPositions(matrix, matrix.getElementAtCell(validPosX, validPosY), validPosX, validPosY);

        if (emptyBelow) {
            applyGravity();
        } else {
            setVelocityY(0.0);
        }



    }

}
