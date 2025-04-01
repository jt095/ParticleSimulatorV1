package elements;
import CellularMatrix.CellularMatrix;
import java.awt.*;
import java.util.Vector;

public abstract class Element {
    private int matrixX;
    private int matrixY;
    protected final Color color;
    protected Vector<Double> velocity = new Vector<>(2);
    protected boolean hasBeenUpdated = false;
    protected ElementType elementType;
    protected static final double GRAVITY = 0.2;
    protected final boolean evenFrame;

    public Element(int x, int y, boolean evenFrame) {
        this.matrixX = x;
        this.matrixY = y;
        this.evenFrame = evenFrame;
        this.elementType = getEnumType();
        this.color = ColorConstants.getColorForElementType(elementType);
        velocity.addElement(0.0);
        velocity.addElement(0.0);
    }

    public abstract void step(CellularMatrix matrix);

    public boolean isHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

    public double getVelocityX() {
        return velocity.getFirst();
    }

    public double getVelocityY() {
        return velocity.getLast();
    }

    public void setVelocityX(double velocityX) {
        this.velocity.set(0, velocityX);
    }

    public void setVelocityY(double velocityY) {
        this.velocity.set(1, velocityY);
    }

    public void applyGravity() {
        this.setVelocityY(getVelocityY() + GRAVITY);
    }

    public Color getColor() {
        return color;
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
