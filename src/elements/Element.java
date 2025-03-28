package elements;
import CellularMatrix.CellularMatrix;
import java.awt.*;

public abstract class Element {
    private int matrixX;
    private int matrixY;
    protected Color color;
    protected int density;
    protected boolean hasBeenUpdated = false;
    protected ElementType elementType;

    public Element(int x, int y) {
        this.matrixX = x;
        this.matrixY = y;
        this.color = Color.BLACK;
        this.elementType = getEnumType();
    }

    public abstract void step(CellularMatrix matrix);

    public boolean isHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public int getMatrixX() {
        return matrixX;
    }

    public void setMatrixX(int x) {
        this.matrixX = x;
    }

    public int getMatrixY() {
        return matrixY;
    }

    public void setMatrixY(int y) {
        this.matrixY = y;
    }

    public void setCoordinatesByMatrix(int providedX, int providedY) {
        setMatrixX(providedX);
        setMatrixY(providedY);
    }

    public void swapPositions(CellularMatrix matrix, Element toSwap, int toSwapX, int toSwapY) {
        if (this.getMatrixX() == toSwapX && this.getMatrixY() == toSwapY) {
            return;
        }
        matrix.setElementAtIndex(this.getMatrixX(), this.getMatrixY(), toSwap);
        matrix.setElementAtIndex(toSwapX, toSwapY, this);
    }

    public ElementType getEnumType() {
        return ElementType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }
}
