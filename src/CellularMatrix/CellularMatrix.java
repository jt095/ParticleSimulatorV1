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
        for (int x = 1; x < getWidth()-1; x++) {
            for (int y = 0; y < getHeight() - 1; y++) {
                setElementAtIndex(x, y, ElementType.EMPTYCELL.createElementByMatrix(x, y));
            }
            setElementAtIndex(x, getHeight()-1, ElementType.GROUND.createElementByMatrix(x, getHeight()-1));
        }

        // Left and Right borders -> ground
        for (int k = 0; k < getHeight(); k++) {
            setElementAtIndex(0, k, ElementType.GROUND.createElementByMatrix(0, k));
            setElementAtIndex(getWidth()-1, k, ElementType.GROUND.createElementByMatrix(getWidth()-1, k));
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
