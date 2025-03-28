package elements;

import java.util.*;
import java.util.stream.Collectors;
import elements.solid.movable.*;
import elements.solid.immovable.*;

public enum ElementType {
    EMPTYCELL(EmptyCell.class, ClassType.EMPTYCELL) {
        @Override
        public Element createElementByMatrix(int x, int y) {
            return EmptyCell.getInstance();
        }
    },
    GROUND(Ground.class, ClassType.IMMOVABLESOLID) {
        @Override
        public Element createElementByMatrix(int x, int y) {
            return new Ground(x, y);
        }
    },
    SAND(Sand.class, ClassType.MOVABLESOLID) {
        @Override
        public Element createElementByMatrix(int x, int y) {
            return new Sand(x, y);
        }
    };



    public final Class<? extends Element> clazz;
    public final ClassType classType;
    public static List<ElementType> IMMOVABLE_SOLIDS;
    public static List<ElementType> MOVABLE_SOLIDS;
    public static List<ElementType> SOLIDS;
    public static List<ElementType> LIQUIDS;
    public static List<ElementType> GASSES;

    ElementType(Class<? extends Element> clazz, ClassType classType) {
        this.clazz = clazz;
        this.classType = classType;
    }

    public abstract Element createElementByMatrix(int x, int y);

    private static List<ElementType> initializeList(ClassType classType) {
        return Arrays.stream(ElementType.values()).filter(elementType -> elementType.classType.equals(classType)).collect(Collectors.toList());
    }

    public static List<ElementType> getMovableSolids() {
        if (MOVABLE_SOLIDS == null) {
            MOVABLE_SOLIDS = initializeList(ClassType.MOVABLESOLID);
            MOVABLE_SOLIDS.sort(Comparator.comparing(Enum::toString));
        }
        return Collections.unmodifiableList(MOVABLE_SOLIDS);
    }

    public enum ClassType {
        MOVABLESOLID,
        IMMOVABLESOLID,
        LIQUID,
        GAS,
        PARTICLE,
        EMPTYCELL,
        PLAYER;

    }
}
