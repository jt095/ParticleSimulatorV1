package elements;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ColorConstants {

    private static final Map<String, Color> colorCache = new ConcurrentHashMap<>();

    private static final Map<ElementType, List<Color>> elementColorMap = new HashMap<>();
    private static final Map<String, List<Color>> namedColorMap = new HashMap<>();
    private static final Map<String, List<Color>> effectsColorMap = new HashMap<>();
    private static final Random random = new Random();

    // Movable Solids
    private static final Color SAND_1 =  new Color(255, 237, 0);
    private static final Color SAND_2 =  new Color(255, 218, 0);
    private static final Color SAND_3 =  new Color(255, 192, 0);

    // Immovable Solids
    private static final Color GROUND = new Color(128, 128, 128);


    // Liquids
    private static final Color WATER = new Color(0, 123, 167);

    // Empty
    private static final Color EMPTY_CELL = Color.BLACK;


    static {
        Arrays.stream(ElementType.values()).forEach(type -> elementColorMap.put(type, new ArrayList<>()));
        elementColorMap.get(ElementType.SAND).add(SAND_1);
        elementColorMap.get(ElementType.SAND).add(SAND_2);
        elementColorMap.get(ElementType.SAND).add(SAND_3);

        elementColorMap.get(ElementType.GROUND).add(GROUND);
        elementColorMap.get(ElementType.WATER).add(WATER);

        elementColorMap.get(ElementType.EMPTYCELL).add(EMPTY_CELL);

    }

    public static Color getColorByName(String name) {
        return namedColorMap.get(name).get(random.nextInt(namedColorMap.get(name).size()));
    }

    public static Color getColorForElementType(ElementType elementType) {
        List<Color> colorList = elementColorMap.get(elementType);
        return elementColorMap.get(elementType).get(random.nextInt(colorList.size()));
    }

//    public static Color getColorForElementType(ElementType elementType, int x, int y) {
//        if (materialsMap.get(elementType.name()) != null) {
//            int rgb = materialsMap.get(elementType.name()).getRGB(x, y);
//            return colorCache.computeIfAbsent(String.valueOf(rgb), k-> {
//                Color color = new Color();
//                Color.rgba8888ToColor(color, rgb);
//                return color;
//            });
//        } else {
//            return getColorForElementType(elementType);
//        }
//    }


}