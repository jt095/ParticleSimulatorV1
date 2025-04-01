package elements.liquid;

import CellularMatrix.CellularMatrix;
import elements.Element;
import elements.EmptyCell;
import elements.solid.Solid;

import static java.lang.Math.round;

public abstract class Liquid extends Element {
    protected static int spreadRate = 1;
    public Liquid(int x, int y, boolean evenFrame) {
        super(x, y, evenFrame);
    }

    @Override
    public void step(CellularMatrix matrix) {
        // loop through each velocity step
        boolean emptyBelow = true;
        boolean stopSearching = false;
        int validPosX = getMatrixX();
        int validPosY = getMatrixY();

        for (int x = 0; x < (round(Math.abs(getVelocityX())) + 1); x++) {
            for (int y = 0; y < round(getVelocityY()) + 1; y++) {
                int nextPosX = getMatrixX();
                if (getVelocityX() < 0) {
                    nextPosX -= x;
                } else {
                    nextPosX += x;
                }

                int nextPosY = getMatrixY() + y;
                if (nextPosX <= 0|| nextPosX >= matrix.getWidth() - 1|| nextPosY >= matrix.getHeight() - 1) {
                    break;
                }

                Element belowCell = matrix.getElementAtCell(nextPosX, nextPosY + 1);
                Element leftCell = matrix.getElementAtCell(nextPosX - 1, nextPosY);
                Element rightCell = matrix.getElementAtCell(nextPosX + 1, nextPosY);

                if (belowCell instanceof EmptyCell) {
                    setVelocityX(0.0);
                    validPosX = nextPosX;
                    validPosY = nextPosY + 1;
                    emptyBelow = true;
                }

                // move left first on even frame
                else if (evenFrame) {
                    if (leftCell instanceof  EmptyCell) {
                        validPosX = nextPosX - 1;
                        validPosY = nextPosY;
                        emptyBelow = false;
                        stopSearching = false;
                    }
                    else if (rightCell instanceof  EmptyCell) {
                        validPosX = nextPosX + 1;
                        validPosY = nextPosY;
                        emptyBelow = false;
                        stopSearching = false;
                    }
                }

                else {
                    if (rightCell instanceof  EmptyCell) {
                        validPosX = nextPosX + 1;
                        validPosY = nextPosY;
                        emptyBelow = false;
                        stopSearching = false;
                    }
                    else if (leftCell instanceof  EmptyCell) {
                        validPosX = nextPosX - 1;
                        validPosY = nextPosY;
                        emptyBelow = false;
                        stopSearching = false;
                    }
                }

//                // check if about to run into a solid
//                if (getVelocityX() > 0 && rightCell instanceof Solid) {
//                    stopSearching = true;
//                }
//                if (getVelocityX() < 0 && leftCell instanceof Solid) {
//                    stopSearching = true;
//                }

                if (belowCell instanceof Solid) {
                    emptyBelow = false;
                    setVelocityY(spreadRate);
                }
            }
            if (stopSearching) {
                break;
            }
        }

        if (emptyBelow) {
            applyGravity();
        } else {
            if (validPosX <= getMatrixX()) {
                setVelocityX(-spreadRate);
            } else {
                setVelocityX(spreadRate);
            }
        }

        swapPositions(matrix, matrix.getElementAtCell(validPosX, validPosY), validPosX, validPosY);

    }
}
