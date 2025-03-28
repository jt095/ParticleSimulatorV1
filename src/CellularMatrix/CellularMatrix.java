package CellularMatrix;

import elements.Element;
import elements.ElementType;

public class CellularMatrix {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private final Element[][] matrix;

    public CellularMatrix() {
        matrix = new Element[WIDTH][HEIGHT];
        fillMatrix();
    }

    public void fillMatrix() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight() - 1; y++) {
                setElementAtIndex(x, y, ElementType.EMPTYCELL.createElementByMatrix(x, y));
            }
            setElementAtIndex(x, getHeight()-1, ElementType.GROUND.createElementByMatrix(x, getHeight()-1));
        }
    }

    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }

    public Element getElementAtCell(int x, int y) {
        return matrix[x][y];
    }

    public void setElementAtIndex(int x, int y, Element element) {
        matrix[x][y] = element;
        element.setCoordinatesByMatrix(x, y);
        element.setHasBeenUpdated(true);
    }
}
