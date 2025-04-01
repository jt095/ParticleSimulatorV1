package elements.liquid;

public class Water extends Liquid{
    public Water(int x, int y, boolean evenFrame) {
        super(x, y, evenFrame);
        spreadRate = 16;
    }
}
