package game.com.anish.world;

import game.asciiPanel.AsciiPanel;

public class Bomb extends Creature implements Runnable {

    private int xPos;
    private int yPos;
    // private boolean immediateExplode;

    public Bomb(World world, int xPos, int yPos) {
        super(AsciiPanel.red, (char)15, world);
        this.xPos = xPos;
        this.yPos = yPos;
        // this.immediateExplode = false;
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public void explode() {
        world.remove(xPos, yPos);
    }

    @Override
    public void run() {
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            explode();
            return;
        }
        explode();
    }
    
}
