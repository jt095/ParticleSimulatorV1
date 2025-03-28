import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CellularAutomaton extends JPanel {

    private static final int WIDTH = 400;  // Screen width (in pixels)
    private static final int HEIGHT = 200; // Screen height (in pixels)
    private static final int CELL_SIZE = 4; // Each cell (pixel) is now 10x10 pixels
    private static final int START_X = WIDTH/2;
    private static final int START_Y = HEIGHT/2;
    private Particle[][] screen = new Particle[WIDTH][HEIGHT];

    // mouse inputs
    private boolean mousePressed = false;
    private int mouseX = -1;
    private int mouseY = -1;

    // keyboard inputs
    private String lastKeyPressed = "";

    private static int updateCount = 0;

    // Constructor initializes the grid
    public CellularAutomaton() {
        // Bulk -> empty
        for (int i = 1; i < WIDTH-1; i++) {
            for (int j = 0; j < HEIGHT-1; j++) {
                Particle emptyParticle = new Particle("empty");
                screen[i][j] = emptyParticle;
            }
            // Bottom border -> ground
            Particle groundParticle = new Particle("ground");
            screen[i][HEIGHT-1] = groundParticle;
        }

        // Left and Right borders -> ground
        for (int k = 0; k < HEIGHT; k++) {
            screen[0][k] = new Particle("ground");
            screen[WIDTH-1][k] = new Particle("ground");
        }

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
        // Change the color of the clicked pixel to a random color
        if (mousePressed) {
            String material = "sand";
            if (Objects.equals(lastKeyPressed, "1")) {
                material = "sand";
            } else if (Objects.equals(lastKeyPressed, "2")) {
                material = "water";
            }
            screen[mouseX][mouseY] = new Particle(material);
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                screen[i][j].setHasBeenUpdated(false);
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (screen[i][j].getHasBeenUpdated() ||
                        Objects.equals(screen[i][j].getMaterialId(), "empty") ||
                        Objects.equals(screen[i][j].getMaterialId(), "ground")) {
                    // particle has been updated or is empty
                    continue;
                }

                if (Objects.equals(screen[i][j].getMaterialId(), "sand")) {
                    if (isEmptyBelow(i, j)) {
                        // update current to empty, update below to sand
                        screen[i][j].setMaterialId("empty");
                        screen[i][j + 1].setMaterialId("sand");
                    }
                    else if (isEmptyBottomLeft(i, j)) {
                        screen[i][j].setMaterialId("empty");
                        screen[i - 1][j + 1].setMaterialId("sand");
                    }
                    else if (isEmptyBottomRight(i, j)) {
                        screen[i][j].setMaterialId("empty");
                        screen[i + 1][j + 1].setMaterialId("sand");
                    }
                }

                else if (Objects.equals(screen[i][j].getMaterialId(), "water")) {
                    if (isEmptyBelow(i, j)) {
                        // update current to empty, update below to water
                        screen[i][j].setMaterialId("empty");
                        screen[i][j + 1].setMaterialId("water");
                    }
                    else if (isEmptyLeft(i, j)) {
                        screen[i][j].setMaterialId("empty");
                        screen[i - 1][j].setMaterialId("water");
                    }
                    else if (isEmptyRight(i, j)) {
                        screen[i][j].setMaterialId("empty");
                        screen[i + 1][j].setMaterialId("water");
                    }
                }
            }
        }
    }

    private Particle getBelow(int x, int y) {
        return screen[x][y + 1];
    }

    private Particle getBottomLeft(int x, int y) {
        return screen[x - 1][y + 1];
    }

    private Particle getBottomRight(int x, int y) {
        return screen[x + 1][y + 1];
    }

    private boolean isEmptyBelow(int x, int y) {
        return (Objects.equals(screen[x][y + 1].getMaterialId(), "empty"));
    }

    private boolean isEmptyBottomLeft(int x, int y) {
        return (Objects.equals(screen[x - 1][y + 1].getMaterialId(), "empty"));
    }

    private boolean isEmptyBottomRight(int x, int y) {
        return (Objects.equals(screen[x + 1][y + 1].getMaterialId(), "empty"));
    }

    private boolean isEmptyLeft(int x, int y) {
        return (Objects.equals(screen[x - 1][y].getMaterialId(), "empty"));
    }

    private boolean isEmptyRight(int x, int y) {
        return (Objects.equals(screen[x + 1][y].getMaterialId(), "empty"));
    }

    // Overriding paintComponent to draw the updated screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                g.setColor(screen[i][j].getColor());
                g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE); // Draw each zoomed-in pixel
            }
        }
    }

    // Main method to run the simulation
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cellular Automaton");
        CellularAutomaton panel = new CellularAutomaton();

        // Set up a timer to update and repaint the screen every 100 milliseconds
        Timer timer = new Timer(1, e -> {
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
