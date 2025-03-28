import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Particle {
    private String materialId;
    private long lifetime;
    private Color color;
    private int density;
    private boolean hasBeenUpdated = false;

    private final Color[] yellows = {
            new Color(255, 255, 0),   // Bright yellow
            new Color(255, 237, 0),   // Slightly softer yellow
            new Color(255, 218, 0),   // Yellow with a bit more depth
            new Color(255, 192, 0),   // Richer yellow, starting to lean towards gold
            new Color(255, 165, 0)};    // Deeper yellow, closer to golden yellow

    private final Color[] grays = {
            new Color(240, 240, 240),   // Very light gray
            new Color(192, 192, 192),   // Light gray
            new Color(128, 128, 128),   // Medium gray
            new Color(96, 96, 96),      // Darker gray
            new Color(64, 64, 64)};       // Very dark gray

    private final Color blue = new Color(0, 123, 167); // Ocean blue, deeper blue with a hint of green

    // Constructor
    public Particle(String materialId) {
        setMaterialId(materialId);
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
        switch (materialId) {
            case "sand" -> {
                setColor(generateRandomColor("yellow"));
                setDensity(2);
            }
            case "water" -> {
                setColor(generateRandomColor("blue"));
                setDensity(1);
            }
            case "ground" -> {
                setColor(generateRandomColor("gray"));
                setDensity(100);
            }
            case "empty"  -> {
                setColor(Color.BLACK);
                setDensity(0);
            }
            case null, default -> setColor(Color.BLACK);
        }

        setHasBeenUpdated(true);
    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getDensity() {
        return density;
    }

    public boolean getHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(boolean updated) {
        hasBeenUpdated = updated;
    }

    private Color generateRandomColor(String baseColor) {
        Random rand = new Random();
        int max_choice = 4;
        int choice = rand.nextInt(max_choice);
        return switch (baseColor) {
            case "yellow" -> yellows[choice];
            case "blue" -> blue;
            case "gray" -> grays[choice];
            case null, default -> Color.BLACK;
        };
    }
}
