package game.com.anish.world;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int pxPos, int pyPos, int xPos, int yPos) {
        this.world.remove(pxPos, pyPos);
        this.world.put(this, xPos, yPos);
    }

}
