package game.com.anish.world;

import java.awt.Color;
// import game.asciiPanel.AsciiPanel;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int pxPos, int pyPos, int xPos, int yPos) {
        this.world.remove(pxPos, pyPos);
        // if(pxPos == xPos){
        //     if(pyPos + 1== yPos){
        //         this.world.put(new Thing(AsciiPanel.brightRed, (char)25, this.world), pxPos, pyPos);
        //     }else if(pyPos - 1== yPos){
        //         this.world.put(new Thing(AsciiPanel.brightRed, (char)24, this.world), pxPos, pyPos);
        //     }
        // }else if(pyPos == yPos){
        //     if(pxPos + 1== xPos){
        //         this.world.put(new Thing(AsciiPanel.brightRed, (char)26, this.world), pxPos, pyPos);
        //     }else if(pxPos - 1== xPos){
        //         this.world.put(new Thing(AsciiPanel.brightRed, (char)27, this.world), pxPos, pyPos);
        //     }
        // }
        this.world.put(this, xPos, yPos);
    }

}
