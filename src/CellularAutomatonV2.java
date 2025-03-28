import CellularMatrix.CellularMatrix;
import elements.Element;
import elements.ElementType;
import elements.solid.moveable.Sand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CellularAutomatonV2 extends JPanel {

    private static int WIDTH;  // Screen width (in pixels)
    private static int HEIGHT; // Screen height (in pixels)
    private static final int CELL_SIZE = 4; // Each cell (pixel) is now 10x10 pixels
    private final CellularMatrix matrix;

    // mouse inputs
    private boolean mousePressed = false;
    private int mouseX = -1;
    private int mouseY = -1;

    // keyboard inputs
    private String lastKeyPressed = "";

    private static int updateCount = 0;

    // Constructor initializes the grid
    public CellularAutomatonV2() {
        matrix = new CellularMatrix();
        WIDTH = matrix.getWidth();
        HEIGHT = matrix.getHeight();

        // Add a KeyListener to capture keyboard input
        this.setFocusable(true);  // Make sure the panel can receive focus
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Store the last key pressed (e.g., "1", "2", "A", etc.)
                lastKeyPressed = String.valueOf(e.getKeyChar());
                System.out.println(lastKeyPressed);
            }
        });


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Get the x and y coordinates of the click
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
                // Check if the clicked coordinates are within bounds
                if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                    mousePressed = true;
                    mouseX = x;
                    mouseY = y;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }
        });

        // Add a MouseMotionListener to track mouse movement while dragging
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Get the x and y coordinates of the click
                if (mousePressed) {
                    int x = e.getX() / CELL_SIZE;
                    int y = e.getY() / CELL_SIZE;
                    // Check if the clicked coordinates are within bounds
                    if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                        mouseX = x;
                        mouseY = y;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // No need to update during movement if the mouse is not pressed
            }
        });
    }

    // Method to update the screen based on neighboring pixels
    private void updateScreen() {
        // Spawn elements on lcick
        if (mousePressed) {
            if (Objects.equals(lastKeyPressed, "1")) {
                matrix.setElementAtIndex(mouseX, mouseY, ElementType.SAND.createElementByMatrix(mouseX, mouseY));
            }
        }

        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                matrix.getElementAtCell(x, y).setHasBeenUpdated(false);
            }
        }


        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                // don't step if already updated
                if (matrix.getElementAtCell(x, y).isHasBeenUpdated()) continue;
                matrix.getElementAtCell(x, y).step(matrix);
            }
        }
    }


    // Overriding paintComponent to draw the updated screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                g.setColor(matrix.getElementAtCell(i, j).getColor());
                g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE); // Draw each zoomed-in pixel
            }
        }
    }

    // Main method to run the simulation
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cellular Automaton");
        CellularAutomatonV2 panel = new CellularAutomatonV2();

        // Set up a timer to update and repaint the screen every 100 milliseconds
        Timer timer = new Timer(100, e -> {
            panel.updateScreen();  // Update the screen
            panel.repaint();       // Repaint the panel with the new state
            updateCount += 1;
        });
        timer.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH * CELL_SIZE + (CELL_SIZE * 10), HEIGHT * CELL_SIZE + (CELL_SIZE * 10)); // Adjust window size for zoomed-in pixels
        frame.add(panel);  // Add the custom panel to the frame
        frame.setVisible(true); // Make the frame visible
    }
}
